package com.niki914.top_100_liked.util.kotlin

import com.niki914.top_100_liked.util.annotation.QuestionInfo
import com.niki914.top_100_liked.util.annotation.SuspendQuestion

class QuestionFinder : AnnotationFinder<QuestionInfo>(QuestionInfo::class.java) {
    // 如果有特定的过滤逻辑可以写在这里，或者直接使用父类的 scan
}

class SuspendQuestionFinder : AnnotationFinder<SuspendQuestion>(SuspendQuestion::class.java) {
    // 如果有特定的过滤逻辑可以写在这里，或者直接使用父类的 scan
}