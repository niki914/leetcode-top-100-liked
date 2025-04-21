package com.niki.top_100_liked.util;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.Set;

/**
 * 给题目标记题型注解后可以用这个类查找
 */
class QScanner {
    public static void findClassesByQType(String packageName, String type) {
        Reflections reflections = new Reflections(packageName, Scanners.TypesAnnotated, Scanners.SubTypes);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(QType.class);

        for (Class<?> clazz : annotatedClasses) {
            QType annotation = clazz.getAnnotation(QType.class);
            if (annotation.value().equals(type)) {
                // 获取类名，处理嵌套类（例如 D20.Solution1）
                String className = clazz.getName();
                // 提取 D20.Solution1 格式
                String simpleName = clazz.getDeclaringClass() != null
                        ? clazz.getDeclaringClass().getSimpleName() + "." + clazz.getSimpleName()
                        : clazz.getSimpleName();
                System.out.println("从 " + simpleName + " 中找到: " + type);
            }
        }
    }

    // 调用方法示例
    public static void main(String[] args) {
//        扫描 com.niki.top_100_liked._2025 包及其子包，查找双指针题目
        findClassesByQType("com.niki.top_100_liked._2025", "哈希");
    }
}