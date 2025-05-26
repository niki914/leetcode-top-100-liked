package com.niki.top_100_liked.util.annotation

enum class QuestionDifficulty(val string: String) {
    EASY("简单"),
    MEDIUM("中等"),
    HARD("困难")
}

enum class QuestionType(val string: String) {
    Hash("哈希"),
    Substring("子串"),
    TwoPointers("双指针"),
    SlidingWindow("滑动窗口"),
    Array("普通数组"),
    Matrix("矩阵"),
    LinkedList("链表"),
    Tree("二叉树")
}