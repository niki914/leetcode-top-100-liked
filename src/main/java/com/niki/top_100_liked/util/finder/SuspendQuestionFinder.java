package com.niki.top_100_liked.util.finder;

import com.niki.top_100_liked.util.annotation.SuspendQuestion;

import java.util.List;
import java.util.function.Predicate;

/**
 * SuspendQuestionFinder继承自AnnotationFinder，专门用于扫描带有@SuspendQuestion注解的类和方法，
 * 返回过滤后的暂停问题列表，包含可点击链接。
 */
public class SuspendQuestionFinder extends AnnotationFinder<SuspendQuestion> {

    /**
     * 构造函数，传递SuspendQuestion.class给父类
     */
    public SuspendQuestionFinder() {
        super(SuspendQuestion.class);
    }

    /**
     * 重写父类的格式化方法，定义特定于SuspendQuestion的格式化逻辑
     *
     * @param data 注解数据
     * @return 格式化字符串，例如："name(reason) [ClassName(FileName.java:line)]"
     */
    @Override
    protected String formatResult(AnnotationData<SuspendQuestion> data) {
        return String.format("%s(%s) [%s(%s.java:%d)]",
                data.annotation().name(),
                data.annotation().reason(),
                data.name(),
                data.fileName(),
                data.line());
    }

    /**
     * 便捷方法，查找带有@SuspendQuestion注解的类和方法，并通过提供的谓词进行过滤。
     *
     * @param filter 用于过滤SuspendQuestion注解的谓词
     * @return 格式化字符串列表
     */
    @Override
    public List<String> find(Predicate<SuspendQuestion> filter) {
        SuspendQuestionFinder finder = new SuspendQuestionFinder();
        return super.findAnnotatedElements(filter);
    }
}