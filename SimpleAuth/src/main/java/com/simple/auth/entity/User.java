package com.simple.auth.entity;

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

    private static final long serialVersionUID = 2537148879976110497L;

    private Long userId;

    private String userName;

    private String loginName;

    private String password;

    private String phone;

    private String email;

    private String deleteFlag;
}