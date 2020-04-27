package com.simple.manage.annotation;

import java.lang.annotation.*;

/**
 * Description 权限校验自定义注解
 * Author chen
 * CreateTime 2020-04-27 20:36
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAnnotation {
}
