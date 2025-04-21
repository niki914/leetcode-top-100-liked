package com.niki.top_100_liked.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解作用于类
@Target(ElementType.TYPE)
// 注解保留到运行时，以便反射读取
@Retention(RetentionPolicy.RUNTIME)
public @interface QType {
    // 题目类型，例如 "双指针"
    String value();
}