package com.niki.top_100_liked.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解作用于类
@Target({ElementType.METHOD, ElementType.TYPE})
// 注解保留到运行时，以便反射读取
@Retention(RetentionPolicy.RUNTIME)
public @interface QuestionInfo {
    String name();

    QuestionType type();

    QuestionDifficulty difficulty();

    String link();
}