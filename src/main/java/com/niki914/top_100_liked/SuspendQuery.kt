package com.niki914.top_100_liked

import com.niki914.top_100_liked.util.kotlin.SuspendQuestionFinder

class SuspendQuery {
    private val finder = SuspendQuestionFinder()

    fun getAll() = finder.scan { true }

    fun findByKeywords(keyword: String) =
        finder.scan {
            val set = setOf<String>(
                it.name,
                it.reason
            )

            set.any { str -> str.contains(keyword) }
        }

    fun findByName(name: String) = finder.scan {
        it.name == name
    }

    fun findByReason(reason: String) = finder.scan {
        it.reason.contains(reason)
    }
}