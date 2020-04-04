package com.simple.manage.entity;

import lombok.Data;

import java.util.List;

/**
 * Description 树
 * Author chen
 * CreateTime 2020-04-04 23:44
 **/
@Data
public class Tree {
    private int key;

    private String title;

    private boolean isLeaf;

    private List<Tree> children;
}