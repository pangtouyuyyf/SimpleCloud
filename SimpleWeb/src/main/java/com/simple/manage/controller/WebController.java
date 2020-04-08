package com.simple.manage.controller;

import com.simple.manage.aspect.RequestLoginContextHolder;
import com.simple.manage.domain.LoginInfo;

/**
 * Description web服务基础controller
 * Author chen
 * CreateTime 2020-04-08 20:37
 **/

public class WebController {
    /**
     * 获取登录用户信息
     *
     * @return result
     */
    public LoginInfo getLoginInfo() {
        return RequestLoginContextHolder.getRequestLoginInfo();
    }
}