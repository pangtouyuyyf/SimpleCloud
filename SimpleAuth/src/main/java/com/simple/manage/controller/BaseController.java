package com.simple.manage.controller;

import com.simple.manage.aspect.RequestLoginContextHolder;
import com.simple.manage.domain.LoginInfo;
import com.simple.manage.domain.Result;
import com.simple.manage.util.ResultUtil;

/**
 * Description 基础controller
 * Author chen
 * CreateTime 2020-04-08 17:12
 **/

public class BaseController {
    /**
     * 返回成功
     *
     * @return result
     */
    public Result<?> success() {
        return ResultUtil.success();
    }

    /**
     * 返回成功(含参)
     *
     * @param obj obj
     * @return result
     */
    public <T> Result<?> success(T obj) {
        return ResultUtil.success(obj);
    }

    /**
     * 返回成功(含参,只针对登录使用)
     *
     * @param token token
     * @param obj   obj
     * @return result
     */
    public <T> Result<?> success(String token, T obj) {
        return ResultUtil.success(token, obj);
    }

    /**
     * 返回失败
     *
     * @return result
     */
    public Result<?> fail() {
        return ResultUtil.error();
    }

    /**
     * 返回失败(含参)
     *
     * @param msg msg
     * @return result
     */
    public Result<?> fail(String msg) {
        return ResultUtil.error(msg);
    }


    /**
     * 返回失败(含参)
     *
     * @param obj obj
     * @return result
     */
    public <T> Result<?> fail(T obj) {
        return ResultUtil.error(obj);
    }

    /**
     * 返回失败(含参)
     *
     * @param msg msg
     * @param obj obj
     * @return result
     */
    public <T> Result<?> fail(String msg, T obj) {
        return ResultUtil.error(msg, obj);
    }


    /**
     * 自定义返回(code尽量不要与系统code重复,参照SysExpEnum)
     *
     * @param code code
     * @param msg  msg
     * @param data data
     * @return result
     */
    public <T> Result<?> message(int code, String msg, T data) {
        return ResultUtil.message(code, msg, data);
    }

    /**
     * 获取登录用户信息
     *
     * @return result
     */
    public LoginInfo getLoginInfo() {
        return RequestLoginContextHolder.getRequestLoginInfo();
    }
}