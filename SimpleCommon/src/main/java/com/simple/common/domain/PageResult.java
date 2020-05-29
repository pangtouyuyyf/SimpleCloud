package com.simple.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description 分页数据
 * Author chen
 * CreateTime 2020-04-05 10:58
 **/
@Getter
@Setter
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 5923212339331709098L;

    private long total;  //数据总数

    private T list;  //列表：泛型
}