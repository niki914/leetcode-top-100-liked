package com.niki914.top_100_liked.util.kotlin

import org.objectweb.asm.ClassReader
import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LineNumberNode
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import java.lang.reflect.Method

abstract class AnnotationFinder<T : Annotation>(targetClass: Class<T>) {
    // 使用 reified 避免在构造函数中传 Class<T>
    protected val annotationClass: Class<T> = targetClass
    protected val algoPackage = "com.niki914.top_100_liked._2025"

    /**
     * 核心方法：只返回数据，不返回格式化后的字符串
     */
    fun scan(filter: (T) -> Boolean = { true }): List<AnnotationData<T>> {
        val reflections =
            Reflections(algoPackage, Scanners.TypesAnnotated, Scanners.MethodsAnnotated)
        val results = mutableListOf<AnnotationData<T>>()

        // 1. 扫描类
        reflections.getTypesAnnotatedWith(annotationClass)
            .forEach { clazz ->
                val annotation = clazz.getAnnotation(annotationClass)
                if (filter(annotation)) {
                    results.add(
                        AnnotationData(
                            annotation = annotation,
                            className = clazz.name,
                            simpleClassName = clazz.simpleName,
                            fileName = clazz.declaringClass?.simpleName ?: clazz.simpleName,
                            methodName = null,
                            lineNumber = 1 // 类通常无法精确定位行号，除非解析整个文件
                        )
                    )
                }
            }

        // 2. 扫描方法
        reflections.getMethodsAnnotatedWith(annotationClass)
            .forEach { method ->
                val annotation = method.getAnnotation(annotationClass)
                if (filter(annotation)) {
                    results.add(
                        AnnotationData(
                            annotation = annotation,
                            className = method.declaringClass.name,
                            simpleClassName = method.declaringClass.simpleName,
                            fileName = method.declaringClass.simpleName,
                            methodName = method.name,
                            lineNumber = getMethodLineNumber(method) - 1
                        )
                    )
                }
            }

        return results
    }

    /**
     * ASM 逻辑保持不变，语法转为 Kotlin
     */
    private fun getMethodLineNumber(method: Method): Int {
        return try {
            val clazz = method.declaringClass
            val classReader = ClassReader(clazz.name)
            val classNode = ClassNode()
            classReader.accept(classNode, 0)

            val methodDesc = Type.getMethodDescriptor(method)

            // 查找匹配的方法节点
            val methodNode = classNode.methods.firstOrNull {
                it.name == method.name && it.desc == methodDesc
            }

            // 查找第一个行号节点
            methodNode?.instructions?.iterator()?.asSequence()
                ?.filterIsInstance<LineNumberNode>()
                ?.firstOrNull()?.line ?: 1

        } catch (e: Exception) {
            1
        }
    }
}