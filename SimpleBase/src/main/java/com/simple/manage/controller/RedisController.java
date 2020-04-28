package com.simple.manage.controller;

import com.simple.manage.component.RedisOperation;
import com.simple.manage.domain.LoginInfo;
import com.simple.manage.domain.Result;
import com.simple.manage.domain.Token;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description redis 服务controller
 * Author chen
 * CreateTime 2020-04-18 11:51
 **/
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Resource
    private RedisOperation redisOperation;

    /**
     * 获取token
     *
     * @param key key
     * @return token
     */
    @GetMapping("/getToken")
    public Result<?> getToken(@RequestParam("key") String key) {
        String value = redisOperation.getStr(key);
        long time = redisOperation.getStrExpire(key);
        Token token = new Token(key, value, time);
        return Result.success(token);
    }

    /**
     * 刷新token有效时间
     *
     * @param key  key
     * @param time time
     * @return result
     */
    @PostMapping("/renewToken")
    public Result<?> renewToken(@RequestParam("key") String key, @RequestParam("time") Integer time) {
        redisOperation.expireStr(key, time);
        return Result.success();
    }

    /**
     * 获取登录信息
     *
     * @param key key
     * @return result
     */
    @GetMapping("/getLoginInfo")
    public Result<LoginInfo> getLoginInfo(@RequestParam("key") String key) {
        LoginInfo info = (LoginInfo) redisOperation.getObj(key);
        return Result.success(info);
    }

    /**
     * 保存登录信息缓存(token/loginInfo)
     *
     * @param tKey      token key
     * @param tVal      token
     * @param tTime     token time
     * @param lKey      loginInfo key
     * @param loginInfo loginInfo
     * @param lTime     loginInfo time
     * @return result
     */
    @PostMapping("/saveLoginCache")
    public Result<?> saveLoginCache(@RequestParam("tKey") String tKey, @RequestParam("tVal") String tVal, @RequestParam("tTime") Integer tTime,
                                    @RequestParam("lKey") String lKey, @RequestBody LoginInfo loginInfo, @RequestParam("lTime") Integer lTime) {
        redisOperation.setStr(tKey, tVal, tTime);
        redisOperation.setObj(lKey, loginInfo, lTime);
        return Result.success();
    }
}