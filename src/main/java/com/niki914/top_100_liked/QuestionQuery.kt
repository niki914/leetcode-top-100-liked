package com.niki914.top_100_liked

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty
import com.niki914.top_100_liked.util.annotation.QuestionType
import com.niki914.top_100_liked.util.finder.QuestionFinder

class QuestionQuery {
    private val finder = QuestionFinder()

    fun getAll() = finder.find { true }

    fun findByKeywords(keyword: String) =
        finder.find {
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

    fun findByName(name: String) = finder.find {
        it.name == name || it.name.contains(name)
    }

    fun findByNum(num: Int) = finder.find {
        it.numberInType != -1 && it.numberInType == num
    }

    fun findByType(type: QuestionType) = finder.find {
        it.type == type
    }

    fun findByDifficulty(difficulty: QuestionDifficulty) = finder.find {
        it.difficulty == difficulty
    }
}