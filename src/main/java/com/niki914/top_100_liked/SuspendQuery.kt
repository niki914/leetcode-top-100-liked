package com.niki914.top_100_liked

import com.niki914.top_100_liked.util.finder.SuspendQuestionFinder

class SuspendQuery {
    private val finder = SuspendQuestionFinder()

    fun getAll() = finder.find { true }

    fun findByKeywords(keyword: String) =
        finder.find {
            val set = setOf<String>(
                it.name,
                it.reason
            )

            set.any { str -> str.contains(keyword) }
        }

    fun findByName(name: String) = finder.find {
        it.name == name
    }

    fun findByReason(reason: String) = finder.find {
        it.reason.contains(reason)
    }
}