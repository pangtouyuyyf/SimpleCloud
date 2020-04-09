package com.simple.manage.controller;

import com.simple.manage.client.auth.LoginClient;
import com.simple.manage.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description 登录controller
 * Author chen
 * CreateTime 2020-04-08 17:11
 **/
@RestController
@RequestMapping(value = "/sys")
public class LoginController extends WebController {
    @Resource
    private LoginClient loginClient;

    @GetMapping("/login")
    public Result<?> login(@RequestParam("name") String loginName,
                           @RequestParam("pwd") String password,
                           @RequestParam("channel") String channel) {
        return loginClient.login(loginName, password, channel);
    }
}