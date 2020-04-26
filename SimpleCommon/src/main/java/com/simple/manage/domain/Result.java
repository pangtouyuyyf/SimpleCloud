package com.simple.manage.domain;

import com.simple.manage.enums.SysExpEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Description 统一返回数据结果
 * Author chen
 * CreateTime 2020-04-05 10:51
 **/
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 3160023996606292502L;

    private Integer code;  //返回码

    private String message;  //提示信息

    private String token;  //令牌

    private T data;  //具体内容：泛型

    /**
     * 返回成功
     *
     * @return result
     */
    public static Result<?> success() {
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
    public static Result<?> error() {
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
    public static Result<?> error(String msg) {
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
    public static Result<?> error(SysExpEnum sysExpEnum) {
        Result<?> result = new Result<>();
        result.setCode(sysExpEnum.getCode());
        result.setMessage(sysExpEnum.getMessage());
        result.setData(null);
        return result;
    }

    /**
     * 返回失败(含参)
     *
     * @param sysExpEnum enum
     * @return result
     */
    public static <T> Result<?> error(SysExpEnum sysExpEnum, T obj) {
        Result<T> result = new Result<>();
        result.setCode(sysExpEnum.getCode());
        result.setMessage(sysExpEnum.getMessage());
        result.setData(obj);
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
     * @return boolean
     */
    public boolean done() {
        return code == SysExpEnum.SUCCESS.getCode();
    }
}