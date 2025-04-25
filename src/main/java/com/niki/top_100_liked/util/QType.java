package com.niki.top_100_liked.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解作用于类
@Target({ElementType.METHOD, ElementType.TYPE})
// 注解保留到运行时，以便反射读取
@Retention(RetentionPolicy.RUNTIME)
public @interface QType {
    String name();

    String type();

    String difficulty();

    String link();
}