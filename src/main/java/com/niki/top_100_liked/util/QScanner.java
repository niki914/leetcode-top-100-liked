package com.niki.top_100_liked.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodNode;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 扫描带有 @QType 注解的类和方法，返回符合过滤条件的题目列表，日志带可点击链接。
 */
public class QScanner {
    private static final String ALGO_PACKAGE = "com.niki.top_100_liked._2025";

    /**
     * 查找带有 @QType 注解的类和方法，返回题目信息列表，带可点击的文件链接。
     *
     * @param filter 过滤条件，基于 QType 注解的属性
     * @return 题目列表，格式为 ".x(FileName.java:line) name(type) [ClassName]" 或
     * ".x(FileName.java:line) name(type) [ClassName.MethodName]"
     */
    public static List<String> findQuestions(Predicate<QType> filter) {
        Reflections reflections = new Reflections(ALGO_PACKAGE, Scanners.TypesAnnotated, Scanners.MethodsAnnotated);
        List<String> questions = new ArrayList<>();

        // 扫描类上的 @QType 注解
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(QType.class);
        for (Class<?> clazz : annotatedClasses) {
            QType qType = clazz.getAnnotation(QType.class);
            if (filter.test(qType)) {
                String className = clazz.getDeclaringClass() != null
                        ? clazz.getDeclaringClass().getSimpleName() + "." + clazz.getSimpleName()
                        : clazz.getSimpleName();
                String fileName = clazz.getDeclaringClass() != null
                        ? clazz.getDeclaringClass().getSimpleName()
                        : clazz.getSimpleName();
                // 类行号暂用 1（无法精确获取）
                questions.add(formatQuestion(qType, className, fileName, 1));
            }
        }

        // 扫描方法上的 @QType 注解
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(QType.class);
        for (Method method : annotatedMethods) {
            QType qType = method.getAnnotation(QType.class);
            if (filter.test(qType)) {
                String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
                String fileName = method.getDeclaringClass().getSimpleName();
                // 获取方法起始行号
                int lineNumber = getMethodLineNumber(method) - 1;
                questions.add(formatQuestion(qType, methodName, fileName, lineNumber));
            }
        }

        return questions;
    }

    /**
     * 使用 ASM 获取方法的起始行号。
     *
     * @param method 目标方法
     * @return 方法的起始行号，若无法获取返回 1
     */
    private static int getMethodLineNumber(Method method) {
        try {
            Class<?> clazz = method.getDeclaringClass();
            ClassReader classReader = new ClassReader(clazz.getName());
            ClassNode classNode = new ClassNode();
            classReader.accept(classNode, 0);

            String methodName = method.getName();
            String methodDescriptor = org.objectweb.asm.Type.getMethodDescriptor(method);
            for (MethodNode methodNode : classNode.methods) {
                if (methodName.equals(methodNode.name) && methodDescriptor.equals(methodNode.desc)) {
                    for (AbstractInsnNode instruction : methodNode.instructions) {
                        if (instruction instanceof LineNumberNode lineNumberNode) {
                            return lineNumberNode.line;
                        }
                    }
                }
            }
        } catch (IOException e) {
//            System.err.println("Failed to get line number for method: " + method.getName());
        }
        return 1; // 默认行号
    }

    /**
     * 格式化题目信息，添加可点击文件链接。
     *
     * @param qType    QType 注解
     * @param name     类名或方法名
     * @param fileName 文件名
     * @param line     行号
     * @return 格式化字符串，如 ".x(FileName.java:line) name(type) [ClassName]"
     */
    private static String formatQuestion(QType qType, String name, String fileName, int line) {
        return String.format("%s(%s) [%s(%s.java:%d)]", qType.name(), qType.type(), name, fileName, line);
    }
}