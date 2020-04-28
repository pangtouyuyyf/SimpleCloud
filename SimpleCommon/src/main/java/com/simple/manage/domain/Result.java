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
    public static <T> Result success(T obj) {
        return message(SysExpEnum.SUCCESS.getCode(), SysExpEnum.SUCCESS.getMessage(), null, obj);
    }

    /**
     * 返回成功(含参,仅在登录操作下使用)
     *
     * @param token token
     * @param obj   obj
     * @return result
     */
    public static <T> Result success(String token, T obj) {
        return message(SysExpEnum.SUCCESS.getCode(), SysExpEnum.SUCCESS.getMessage(), token, obj);
    }

    /**
     * 返回失败
     *
     * @return result
     */
    public static Result<?> error() {
        return message(SysExpEnum.FAIL.getCode(), SysExpEnum.FAIL.getMessage(), null, null);
    }

    /**
     * 返回失败(含参)
     *
     * @param msg msg
     * @return result
     */
    public static Result<?> error(String msg) {
        return message(SysExpEnum.FAIL.getCode(), msg, null, null);
    }

    /**
     * 返回失败(含参)
     *
     * @param obj obj
     * @return result
     */
    public static <T> Result error(T obj) {
        return message(SysExpEnum.FAIL.getCode(), SysExpEnum.FAIL.getMessage(), null, obj);
    }

    /**
     * 返回失败(含参)
     *
     * @param msg msg
     * @param obj obj
     * @return result
     */
    public static <T> Result error(String msg, T obj) {
        return message(SysExpEnum.FAIL.getCode(), msg, null, obj);
    }

    /**
     * 返回失败(含参)
     *
     * @param sysExpEnum enum
     * @return result
     */
    public static Result<?> error(SysExpEnum sysExpEnum) {
        return message(sysExpEnum.getCode(), sysExpEnum.getMessage(), null, null);
    }

    /**
     * 返回失败(含参)
     *
     * @param sysExpEnum enum
     * @return result
     */
    public static <T> Result error(SysExpEnum sysExpEnum, T obj) {
        return message(sysExpEnum.getCode(), sysExpEnum.getMessage(), null, obj);
    }

    /**
     * 自定义提示返回结果(含参)
     *
     * @param code code
     * @param msg  msg
     * @param data data
     * @return result
     */
    public static <T> Result message(int code, String msg, String token, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(msg);
        result.setToken(token);
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