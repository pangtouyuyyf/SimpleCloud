package com.simple.manage.controller;

import com.simple.manage.client.BaseClient;
import com.simple.manage.config.JwtConfig;
import com.simple.manage.domain.LoginInfo;
import com.simple.manage.domain.Result;
import com.simple.manage.entity.User;
import com.simple.manage.enums.SysExpEnum;
import com.simple.manage.service.UserService;
import com.simple.manage.util.CommonUtil;
import com.simple.manage.util.JwtUtil;
import com.simple.manage.util.LogUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (!CommonUtil.CHANNEL_WEB.equals(channel) && !CommonUtil.CHANNEL_APP.equals(channel)) {
            LogUtil.error(LoginController.class, LocalDateTime.now() + " 登录参数有误");
            return this.fail("登录参数有误");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("login_name", loginName);
        params.put("password", password);

        User user = userService.queryUserEntity(params);
        //查询用户
        if (user == null) {
            LogUtil.error(LoginController.class, LocalDateTime.now() + " 用户查询失败");
            return this.fail("用户名密码错误");
        }

        return loginOperate(user, channel);
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
        String token = JwtUtil.createJWT(Integer.toString(user.getId()), channel);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setCurrId(user.getId());
        loginInfo.setChannel(channel);

        //生成令牌缓存主键
        List<String> tokenKeyParts = Arrays.asList(
                CommonUtil.TOKEN_PREFIX, Integer.toString(user.getId()), channel);
        String tokenRedisKey = String.join(CommonUtil.UNDERLINE, tokenKeyParts);

        //生成个人信息缓存主键
        List<String> loginInfoKeyParts = Arrays.asList(
                CommonUtil.LOGIN_INFO_PREFIX, Integer.toString(user.getId()));
        String loginInfoKey = String.join(CommonUtil.UNDERLINE, loginInfoKeyParts);

        Result r = null;
        //保存登录信息缓存(令牌和个人信息)
        if (CommonUtil.CHANNEL_WEB.equals(channel)) {
            r = baseClient.saveLoginCache(tokenRedisKey, token, JwtConfig.WEB_LIFE_CYCLE, loginInfoKey, loginInfo,
                    JwtConfig.LOGIN_INFO_LIFE_CYCLE);
        } else {
            r = baseClient.saveLoginCache(tokenRedisKey, token, JwtConfig.APP_LIFE_CYCLE, loginInfoKey, loginInfo,
                    JwtConfig.LOGIN_INFO_LIFE_CYCLE);
        }

        if (!r.done()) {
            return fail();
        }

        return success(token, null);
    }
}