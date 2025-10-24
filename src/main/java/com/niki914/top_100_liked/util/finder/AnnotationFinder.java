package com.niki914.top_100_liked.util.finder;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodNode;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 通用的类和方法注解扫描器抽象基类，用于查找指定注解的元素。
 * 子类需要实现格式化方法以生成带有可点击文件链接的格式化信息。
 */
public abstract class AnnotationFinder<T extends Annotation> {
    protected static final String ALGO_PACKAGE = "com.niki914.top_100_liked._2025";
    protected final Class<T> annotationClass;

    /**
     * 构造函数，初始化注解类型
     *
     * @param annotationClass 要扫描的注解类
     */
    protected AnnotationFinder(Class<T> annotationClass) {
        this.annotationClass = annotationClass;
    }

    /**
     * 查找带有指定注解的类和方法，应用过滤器并格式化输出。
     *
     * @param filter 用于过滤注解的谓词
     * @return 包含格式化字符串的列表，表示带注解的类和方法
     */
    public List<String> findAnnotatedElements(Predicate<T> filter) {
        Reflections reflections = new Reflections(ALGO_PACKAGE, Scanners.TypesAnnotated, Scanners.MethodsAnnotated);
        List<String> results = new ArrayList<>();

        // 扫描类
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(annotationClass);
        for (Class<?> clazz : annotatedClasses) {
            T annotation = clazz.getAnnotation(annotationClass);
            if (filter.test(annotation)) {
                String className = clazz.getDeclaringClass() != null
                        ? clazz.getDeclaringClass().getSimpleName() + "." + clazz.getSimpleName()
                        : clazz.getSimpleName();
                String fileName = clazz.getDeclaringClass() != null
                        ? clazz.getDeclaringClass().getSimpleName()
                        : clazz.getSimpleName();
                // 类行号默认设为1（无法获取确切行号）
                results.add(formatResult(new AnnotationData<>(annotation, className, fileName, 1)));
            }
        }

        // 扫描方法
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(annotationClass);
        for (Method method : annotatedMethods) {
            T annotation = method.getAnnotation(annotationClass);
            if (filter.test(annotation)) {
                String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
                String fileName = method.getDeclaringClass().getSimpleName();
                int lineNumber = getMethodLineNumber(method) - 1;
                results.add(formatResult(new AnnotationData<>(annotation, methodName, fileName, lineNumber)));
            }
        }

        return results;
    }

    /**
     * 格式化注解数据为字符串结果
     * 子类需要实现此方法以定义特定的格式化逻辑
     *
     * @param data 注解数据
     * @return 格式化后的字符串
     */
    protected abstract String formatResult(AnnotationData<T> data);

    abstract List<String> find(Predicate<T> filter);

    /**
     * 使用ASM获取方法的起始行号。
     *
     * @param method 目标方法
     * @return 方法的起始行号，如果不可用则返回1
     */
    protected int getMethodLineNumber(Method method) {
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
        } catch (Exception ignored) {
        }
        return 1; // 默认行号
    }

    /**
     * 用于存储注解扫描结果的数据持有者。
     */
    public record AnnotationData<T extends Annotation>(T annotation, String name, String fileName,
                                                       int line) {
    }
}