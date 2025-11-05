package com.niki914.top_100_liked

// counter
fun main1() {
    val query = QuestionQuery()
    val suspendQuery = SuspendQuery()

    val queryList = query.getAll()
//    val queryList = query.findByNum(1)
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

fun main() { // query questions at runtime
    val query = QuestionQuery()

    print("搜索: ")
    val input = readln()
    println("搜索: '$input'")

    val queryList = input.let {
        query.findByKeywords(it)
    }

    queryList.forEach {
        println(it)
    }
    println("共 " + queryList.size + " 题")
}

fun main3() { // query suspended questions at runtime
    val suspendQuery = SuspendQuery()

    print("搜索: ")
    val input = readln()
    println("搜索: '$input'")

    val queryList = input.let {
        suspendQuery.findByKeywords(it)
    }

    println("共 " + queryList.size + " 题")
}