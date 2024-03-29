package com.simple.auth.controller;

import com.simple.auth.client.BaseClient;
import com.simple.auth.entity.User;
import com.simple.auth.service.UserService;
import com.simple.common.annotation.TokenAnnotation;
import com.simple.common.config.SysParams;
import com.simple.common.controller.BaseController;
import com.simple.common.domain.LoginInfo;
import com.simple.common.domain.Result;
import com.simple.common.util.JwtUtil;
import com.simple.common.util.LogUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Description 登录controller
 * Author chen
 * CreateTime 2020-04-09 20:40
 **/
@RestController
@RequestMapping(value = "/sys")
public class LoginController extends BaseController {
    @Resource
    private BaseClient baseClient;

    @Resource
    private UserService userService;

    @GetMapping("/login")
    public Result login(@RequestParam("name") String loginName,
                        @RequestParam("pwd") String password,
                        @RequestParam("channel") String channel) {
        if (!SysParams.Sys.CHANNEL_WEB.equals(channel) && !SysParams.Sys.CHANNEL_APP.equals(channel)) {
            LogUtil.error(LoginController.class, LocalDateTime.now() + " 登录参数有误");
            return this.fail("登录参数有误");
        }

        //查询用户
        User user = userService.queryLoginUser(loginName, password);

        if (user == null) {
            LogUtil.error(LoginController.class, LocalDateTime.now() + " 用户查询失败");
            return this.fail("用户名密码错误");
        }

        return loginOperate(user, channel);
    }

    /**
     * 注销
     *
     * @param channel 客户端渠道(app/web)
     * @return
     */
    @TokenAnnotation
    @GetMapping(value = "/logout")
    public Result logout(@RequestParam("channel") String channel) throws Exception {
        if (!SysParams.Sys.CHANNEL_WEB.equals(channel) && !SysParams.Sys.CHANNEL_APP.equals(channel)) {
            LogUtil.error(LoginController.class, LocalDateTime.now() + " 注销参数有误");
            return this.fail();
        }

        List<String> tokenKeyParts = Arrays.asList(SysParams.Redis.TOKEN_PREFIX,
                Long.toString(getLoginInfo().getCurrId()), channel);

        List<String> loginInfoKeyParts = Arrays.asList(SysParams.Redis.LOGIN_INFO_PREFIX,
                Long.toString(getLoginInfo().getCurrId()), channel);

        Result r;
        if (SysParams.Sys.IS_CLEAN_LOGIN_INFO) {
            r = this.baseClient.delToken(String.join(SysParams.Common.UNDERLINE, tokenKeyParts), String.join(SysParams.Common.UNDERLINE, loginInfoKeyParts),
                    SysParams.Common.YES);
        } else {
            r = this.baseClient.delToken(String.join(SysParams.Common.UNDERLINE, tokenKeyParts), String.join(SysParams.Common.UNDERLINE, loginInfoKeyParts),
                    SysParams.Common.NO);
        }

        if (!r.done()) {
            return this.fail();
        }

        return success();
    }

    /**
     * 登录通用操作
     *
     * @param user    用户信息
     * @param channel 客户端渠道(app/web)
     * @return
     */
    private Result loginOperate(User user, String channel) {
        //生成令牌
        String token = JwtUtil.createJWT(Long.toString(user.getUserId()), channel);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setCurrId(user.getUserId());
        loginInfo.setChannel(channel);

        //生成令牌缓存主键
        List<String> tokenKeyParts = Arrays.asList(
                SysParams.Redis.TOKEN_PREFIX, Long.toString(user.getUserId()), channel);
        String tokenRedisKey = String.join(SysParams.Common.UNDERLINE, tokenKeyParts);

        //生成个人信息缓存主键
        List<String> loginInfoKeyParts = Arrays.asList(
                SysParams.Redis.LOGIN_INFO_PREFIX, Long.toString(user.getUserId()), channel);
        String loginInfoKey = String.join(SysParams.Common.UNDERLINE, loginInfoKeyParts);

        Result r;
        //保存登录信息缓存(令牌和个人信息)
        if (SysParams.Sys.CHANNEL_WEB.equals(channel)) {
            r = baseClient.saveLoginCache(tokenRedisKey, token, SysParams.Jwt.WEB_LIFE_CYCLE, loginInfoKey, loginInfo,
                    SysParams.Jwt.LOGIN_INFO_LIFE_CYCLE);
        } else {
            r = baseClient.saveLoginCache(tokenRedisKey, token, SysParams.Jwt.APP_LIFE_CYCLE, loginInfoKey, loginInfo,
                    SysParams.Jwt.LOGIN_INFO_LIFE_CYCLE);
        }

        if (!r.done()) {
            return fail();
        }

        return success(token, null);
    }
}