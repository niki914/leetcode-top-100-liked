package com.niki914.top_100_liked

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty
import com.niki914.top_100_liked.util.annotation.QuestionType
import com.niki914.top_100_liked.util.kotlin.QuestionFinder

class QuestionQuery {
    private val finder = QuestionFinder()

    fun getAll() = finder.scan { true }

    fun findByKeywords(keyword: String) =
        finder.scan {
            val set = mutableSetOf<String>(
                it.name,
                it.type.toString(),
                it.difficulty.toString(),
                it.link
            )

            val num = it.numberInType
            if (num != -1) {
                set.add(num.toString())
            }

            set.any { str -> str.contains(keyword) }
        }

    fun findByName(name: String) = finder.scan {
        it.name == name || it.name.contains(name)
    }

    fun findByNum(num: Int) = finder.scan {
        it.numberInType != -1 && it.numberInType == num
    }

    fun findByType(type: QuestionType) = finder.scan {
        it.type == type
    }

    fun findByDifficulty(difficulty: QuestionDifficulty) = finder.scan {
        it.difficulty == difficulty
    }
}