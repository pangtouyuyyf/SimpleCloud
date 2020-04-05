package com.simple.manage.domain;

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
}