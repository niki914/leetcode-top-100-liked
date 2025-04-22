package com.niki.top_100_liked.util;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 给题目标记题型注解后可以用这个类查找
 */
public class QScanner {
    private final static String ALGO_PKG_NAME = "com.niki.top_100_liked._2025";

    public static List<String> findQuestions(
//            String packageName, // 可拓展的包名参数
            Predicate<QType> filter
    ) {
        Reflections reflections = new Reflections(ALGO_PKG_NAME, Scanners.TypesAnnotated, Scanners.SubTypes);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(QType.class);

        List<String> result = new ArrayList<>();

        for (Class<?> clazz : annotatedClasses) {
            QType qType = clazz.getAnnotation(QType.class);
            if (filter.test(qType)) {
                // 提取 D20.Solution1 格式
                String simpleName = clazz.getDeclaringClass() != null
                        ? clazz.getDeclaringClass().getSimpleName() + "." + clazz.getSimpleName()
                        : clazz.getSimpleName();

                result.add(toString(qType) + " [" + simpleName + "]");
            }
        }

        return result;
    }

    private static String toString(QType t) {
        return t.name() + "(" + t.type() + ")";
    }
}