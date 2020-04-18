package com.simple.manage.controller;

import com.simple.manage.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/login")
    public Result<?> login(@RequestParam("name") String loginName,
                           @RequestParam("pwd") String password,
                           @RequestParam("channel") String channel) {
        System.out.println(loginName + ">>" + password + ">>" + channel);
        List<String> list = Arrays.asList(loginName, password, channel);
        return success(list);
    }
}