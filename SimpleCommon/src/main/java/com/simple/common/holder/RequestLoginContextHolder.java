package com.simple.common.holder;

import com.simple.common.domain.LoginInfo;

/**
 * Description 请求缓存数据ThreadLocal
 * Author chen
 * CreateTime 2020-04-08 17:31
 **/

public abstract class RequestLoginContextHolder {
    private static final ThreadLocal<LoginInfo> inheritableUserHolder = new InheritableThreadLocal<>();

    public static void destroy() {
        inheritableUserHolder.remove();
    }

    public static LoginInfo getRequestLoginInfo() {
        return inheritableUserHolder.get();
    }

    public static void setRequestLoginInfo(LoginInfo loginInfo) {
        inheritableUserHolder.set(loginInfo);
    }
}