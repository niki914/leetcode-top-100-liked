package com.niki.top_100_liked

class Test {
//    private class Node(val key: Int, val value: Int) {
//        var pre: Node? = null
//        var next: Node? = null
//    }
//
//    private val map = hashMapOf<Int, Node>()
//    private val head = Node(0, 0)
//    private val tail = Node(0, 0)
//
//    init {
//        head.next = tail
//        tail.pre = head
//    }
//
//    fun get(key: Int): Int {
//        if (!map.containsKey(key))
//            return -1
//
//        val node = map[key]!!
//        moveToHead(node)
//
//        return node.value
//    }
//
//    fun put(key: Int, value: Int) {
//        if (!map.containsKey(key)) {
//            val node = Node(key, value)
//            addToHead(node)
//            map[key] = node
//        } else {
//            moveToHead(map[key]!!)
//        }
//    }
//
//    private fun moveToHead(node: Node) {
//        remove(node)
//        addToHead(node)
//    }
//
//    private fun remove(node: Node) {
//        node.pre?.next = node.next
//        node.next?.pre = node.pre
//    }
//
//    private fun addToHead(node: Node) {
//        val ori = head.next
//
//        head.next = node
//        node.pre = head
//
//        ori?.pre = node
//        node.next = ori
//    }
}