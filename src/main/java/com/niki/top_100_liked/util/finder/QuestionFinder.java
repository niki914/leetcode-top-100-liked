package com.niki.top_100_liked.util.finder;

import com.niki.top_100_liked.util.annotation.QuestionInfo;

import java.util.List;
import java.util.function.Predicate;

/**
 * QuestionFinder继承自AnnotationFinder，专门用于扫描带有@QuestionInfo注解的类和方法，
 * 返回过滤后的问题列表，包含可点击链接。
 */
public class QuestionFinder extends AnnotationFinder<QuestionInfo> {

    /**
     * 构造函数，传递QuestionInfo.class给父类
     */
    public QuestionFinder() {
        super(QuestionInfo.class);
    }

    /**
     * 重写父类的格式化方法，定义特定于QuestionInfo的格式化逻辑
     *
     * @param data 注解数据
     * @return 格式化字符串，例如："name(type) [ClassName(FileName.java:line)]"
     */
    @Override
    protected String formatResult(AnnotationData<QuestionInfo> data) {
        return String.format("%s(%s) [%s(%s.java:%d)]",
                data.annotation().name(),
                data.annotation().type(),
                data.name(),
                data.fileName(),
                data.line());
    }

    /**
     * 便捷方法，查找带有@QuestionInfo注解的类和方法，并通过提供的谓词进行过滤。
     *
     * @param filter 用于过滤QuestionInfo注解的谓词
     * @return 格式化字符串列表
     */
    @Override
    public List<String> find(Predicate<QuestionInfo> filter) {
        QuestionFinder finder = new QuestionFinder();
        return finder.findAnnotatedElements(filter);
    }
}