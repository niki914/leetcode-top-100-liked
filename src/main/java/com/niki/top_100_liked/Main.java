package com.niki.top_100_liked;

import com.niki.top_100_liked.util.QScanner;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = QScanner.findQuestions(
                qType -> qType.name().contains("移动") && "双指针".equals(qType.type())
        );

        list.forEach(System.out::println);
    }
}
