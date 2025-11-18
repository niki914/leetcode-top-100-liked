package com.niki914.top_100_liked.util.annotation

import com.niki914.top_100_liked.Day
import kotlin.reflect.KClass

/**
 * 指示当前 Day 的下一个 Day 类。
 *
 * @param next 下一个 Day 的 KClass 引用。
 */
@Retention(AnnotationRetention.SOURCE) // 或者 RUNTIME，取决于你的解析方式
@Target(AnnotationTarget.CLASS)
annotation class Link(
//    val clazz: KClass<out Day>
    val last: KClass<out Day>,
    val next: KClass<*>
)