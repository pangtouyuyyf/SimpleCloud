package com.simple.manage.controller;

import com.simple.manage.component.RedisOperation;
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
     * @param key
     * @return
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
     * @param key
     * @param time
     * @return
     */
    @PostMapping("/renewToken")
    public Result<?> renewToken(@RequestParam("key") String key, @RequestParam("time") Integer time) {
        redisOperation.expireStr(key, time);
        return Result.success();
    }

    /**
     * 保存token
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    @PostMapping("/saveToken")
    public Result<?> saveToken(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") Integer time) {
        redisOperation.setStr(key, value, time);
        return Result.success();
    }
}