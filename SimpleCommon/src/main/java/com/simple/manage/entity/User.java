package com.simple.manage.entity;

import lombok.Data;

/**
 * Description 用户信息
 * Author chen
 * CreateTime 2020-04-20 22:12
 **/
@Data
public class User {
    private int id;

    private String name;

    private String loginName;

    private String password;

    private String phone;

    private String email;
}