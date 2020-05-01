package com.simple.manage.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description token
 * Author chen
 * CreateTime 2020-04-19 11:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String key;

    private String value;

    private long time;  //剩余有效时间
}