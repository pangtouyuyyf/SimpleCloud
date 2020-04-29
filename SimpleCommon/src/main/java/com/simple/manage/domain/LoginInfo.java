package com.simple.manage.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description 登录信息
 * Author chen
 * CreateTime 2020-04-08 17:16
 **/
@Data
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 6900813419624163289L;

    private int currId;  //当前用户id

    private String channel;  //登录渠道(web/app)

    private List<Integer> roleIds;  //当前用户角色列表
}