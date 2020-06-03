package com.simple.access.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description 组织对象
 * Author chen
 * CreateTime 2020-05-29 17:03
 **/
@Getter
@Setter
public class Org implements Serializable {
    private int id;

    private String name;

    private int parentId;
}