package com.simple.auth.controller;

import com.simple.common.controller.BaseController;
import com.simple.common.domain.LoginInfo;
import com.simple.common.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 用户信息操作controller
 * Author chen
 * CreateTime 2020-04-14 22:03
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    /**
     * 检查当前登录用户是否为系统中用户
     *
     * @return
     */
    @GetMapping("/checkOne")
    public Result<?> checkOne() {
        LoginInfo info = this.getLoginInfo();
        info.getCurrId();
        return success();
    }
}