package com.niki.top_100_liked;

import com.niki.top_100_liked.util.QScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        List<String> list = QScanner.findQuestions(
//                qType -> qType.name().contains("移动") && "双指针".equals(qType.type())
                qType -> true
        );

        list.forEach(System.out::println);
        System.out.println("共 " + list.size() + " 题");
//        throw new Exception("asd");
    }
}
