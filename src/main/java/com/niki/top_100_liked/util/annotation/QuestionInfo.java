package com.niki.top_100_liked.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解作用于类

/**
 * 可以通过注解反射并按条件查询算法题
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuestionInfo {
    String name();

    QuestionType type();

    QuestionDifficulty difficulty();

    String link();
}