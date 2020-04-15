package com.simple.manage.util;

import com.simple.manage.domain.Result;
import com.simple.manage.enums.SysExpEnum;

/**
 * Description 数据结果统一处理工具类
 * Author chen
 * CreateTime 2020-04-05 11:18
 **/

public class ResultUtil {
    /**
     * 返回成功
     *
     * @return result
     */
    public static <T> Result<?> success() {
        return success(null);
    }

    /**
     * 返回成功(含参)
     *
     * @param obj obj
     * @return result
     */
    public static <T> Result<?> success(T obj) {
        Result<T> result = new Result<>();
        result.setCode(SysExpEnum.SUCCESS.getCode());
        result.setMessage(SysExpEnum.SUCCESS.getMessage());
        result.setData(obj);
        return result;
    }

    /**
     * 返回成功(含参,仅在登录操作下使用)
     *
     * @param token token
     * @param obj   obj
     * @return result
     */
    public static <T> Result<?> success(String token, T obj) {
        Result<?> result = success(obj);
        result.setToken(token);
        return result;
    }

    /**
     * 返回失败
     *
     * @return result
     */
    public static <T> Result<?> error() {
        Result<?> result = new Result<>();
        result.setCode(SysExpEnum.FAIL.getCode());
        result.setMessage(SysExpEnum.FAIL.getMessage());
        result.setData(null);
        return result;
    }

    /**
     * 返回失败(含参)
     *
     * @param msg msg
     * @return result
     */
    public static <T> Result<?> error(String msg) {
        Result<?> result = new Result<>();
        result.setCode(SysExpEnum.FAIL.getCode());
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    /**
     * 返回失败(含参)
     *
     * @param obj obj
     * @return result
     */
    public static <T> Result<?> error(T obj) {
        Result<T> result = new Result<>();
        result.setCode(SysExpEnum.FAIL.getCode());
        result.setMessage(SysExpEnum.FAIL.getMessage());
        result.setData(obj);
        return result;
    }

    /**
     * 返回失败(含参)
     *
     * @param msg msg
     * @param obj obj
     * @return result
     */
    public static <T> Result<?> error(String msg, T obj) {
        Result<T> result = new Result<>();
        result.setCode(SysExpEnum.FAIL.getCode());
        result.setMessage(msg);
        result.setData(obj);
        return result;
    }

    /**
     * 返回失败(含参)
     *
     * @param sysExpEnum enum
     * @return result
     */
    public static <T> Result<?> error(SysExpEnum sysExpEnum) {
        Result<?> result = new Result<>();
        result.setCode(sysExpEnum.getCode());
        result.setMessage(sysExpEnum.getMessage());
        result.setData(null);
        return result;
    }

    /**
     * 自定义提示返回结果(含参)
     *
     * @param code code
     * @param msg  msg
     * @param data data
     * @return result
     */
    public static <T> Result<?> message(int code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    /**
     * 判断是否是成功返回
     *
     * @param result result
     * @return boolean
     */
    public static boolean isSuccess(Result<?> result) {
        return SysExpEnum.SUCCESS.getCode() == result.getCode();
    }
}