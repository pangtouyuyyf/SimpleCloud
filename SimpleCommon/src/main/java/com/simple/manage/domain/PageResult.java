package com.simple.manage.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Description 分页数据
 * Author chen
 * CreateTime 2020-04-05 10:58
 **/
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 5923212339331709098L;

    private long total;  //数据总数

    private T list;  //列表：泛型
}