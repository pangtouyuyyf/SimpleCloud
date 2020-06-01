package com.simple.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description 用户信息
 * Author chen
 * CreateTime 2020-04-20 22:12
 **/
@Getter
@Setter
public class User implements Serializable {
    private int id;

    private String name;

    private String loginName;

    private String password;

    private String phone;

    private String email;
}