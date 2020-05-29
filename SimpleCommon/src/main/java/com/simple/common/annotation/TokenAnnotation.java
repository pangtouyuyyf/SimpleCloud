package com.simple.common.annotation;

import java.lang.annotation.*;

/**
 * Description 令牌校验自定义注解
 * Author chen
 * CreateTime 2020-04-27 20:33
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenAnnotation {
}
