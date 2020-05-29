package com.simple.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description 树
 * Author chen
 * CreateTime 2020-04-04 23:44
 **/
@Getter
@Setter
public class Tree {
    private int key;

    private String title;

    private boolean isLeaf;

    private List<Tree> children;
}