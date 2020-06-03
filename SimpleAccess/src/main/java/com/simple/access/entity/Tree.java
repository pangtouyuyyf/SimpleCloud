package com.simple.access.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Description 树
 * Author chen
 * CreateTime 2020-04-04 23:44
 **/
@Getter
@Setter
public class Tree implements Serializable {
    private int key;

    private String title;

    private boolean leaf;

    private List<Tree> children;
}