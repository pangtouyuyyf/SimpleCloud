package com.simple.manage.controller;

import com.simple.manage.component.RedisOperation;
import com.simple.manage.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getToken")
    public Result<?> getToken(@RequestParam("key") String key) {
        String token = redisOperation.getStr(key);
        return Result.success(token);
    }

    @GetMapping("/setStr")
    public Result<?> setStr(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") Long time) {
        redisOperation.setStr(key, value, time);
        return Result.success();
    }
}