package com.simple.common.domain;


import lombok.*;

/**
 * Description token
 * Author chen
 * CreateTime 2020-04-19 11:51
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String key;

    private String value;

    private long time;  //剩余有效时间
}