package com.simple.manage.domain;

import lombok.Data;

/**
 * Description token
 * Author chen
 * CreateTime 2020-04-19 11:51
 **/
@Data
public class Token {
    private String key;

    private String value;

    private long time;  //剩余有效时间

    public Token() {
    }

    public Token(String key, String value, long time) {
        this.key = key;
        this.value = value;
        this.time = time;
    }
}