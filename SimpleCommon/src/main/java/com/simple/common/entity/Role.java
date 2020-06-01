package com.simple.common.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description 角色信息
 * Author chen
 * CreateTime 2018-09-17 10:38
 **/
@Getter
@Setter
public class Role implements Serializable {
    private int id;

    private String code;

    private String name;

    private int orgId;
}
