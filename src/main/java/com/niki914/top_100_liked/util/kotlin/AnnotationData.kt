package com.niki914.top_100_liked.util.kotlin

// 定义数据类，承载原始信息
data class AnnotationData<T : Annotation>(
    val annotation: T,
    val className: String,
    val simpleClassName: String,
    val fileName: String,
    val methodName: String?, // 如果是类注解，则为 null
    val lineNumber: Int
)