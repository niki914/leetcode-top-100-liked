package com.niki914.top_100_liked.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解作用于类和方法
@Target({ElementType.METHOD, ElementType.TYPE})
// 注解保留到运行时，以便反射读取
@Retention(RetentionPolicy.RUNTIME)
public @interface SuspendQuestion {
    String name() default "未命名"; // 题目名称

    String reason() default "跳过"; // 未完成的原因
}