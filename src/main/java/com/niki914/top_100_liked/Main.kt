package com.niki914.top_100_liked


fun main() {
    val query = QuestionQuery()
    val suspendQuery = SuspendQuery()

    val queryList = query.getAll()
//    val queryList = query.findByKeywords("数")

    val suspendList = suspendQuery.getAll()
//    val suspendList = suspendQuery.findByKeywords("难")

    queryList.forEach {
        println(it)
    }
    println("已记录: 共 " + queryList.size + " 题")

    suspendList.forEach {
        println(it)
    }
    println("未完成: 共 " + suspendList.size + " 题")
}