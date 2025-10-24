package com.niki914.top_100_liked;

import com.niki914.top_100_liked.util.finder.QuestionFinder;
import com.niki914.top_100_liked.util.finder.SuspendQuestionFinder;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> list1 = new QuestionFinder().find(
//                info -> info.name().contains("移动") && "双指针".equals(info.type())
//                info -> info.name().contains("合并")
                info -> true
        );

        List<String> list2 = new SuspendQuestionFinder().find(
//                info -> info.name().contains("移动"g)
                info -> true
        );

        list1.forEach(System.out::println);
        System.out.println("已记录: 共 " + list1.size() + " 题");
        list2.forEach(System.out::println);
        System.out.println("未完成: 共 " + list2.size() + " 题");
    }
}
