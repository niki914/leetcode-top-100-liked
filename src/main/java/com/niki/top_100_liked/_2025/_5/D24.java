package com.niki.top_100_liked._2025._5;

import com.niki.top_100_liked.beans.Node;
import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

public class D24 {
    @QuestionInfo(
            name = "随机链表的复制",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Step 1: 在每个原节点后插入复制节点
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: 设置复制节点的random指针
        curr = head;
        while (curr != null) {
            Node copy = curr.next;
            if (curr.random != null) {
                copy.random = curr.random.next; // 原节点的random的next是对应的复制节点, 要指向复制节点
            }
            curr = copy.next;
        }

        // Step 3: 分离原链表和复制链表
        Node dummy = new Node(0);
        Node copyCurr = dummy;
        curr = head;
        while (curr != null) {
            // 提取复制节点, 把他们添加到哑节点的链表
            copyCurr.next = curr.next;
            copyCurr = copyCurr.next;

            // 恢复原链表, 删除插入的复制节点
            curr.next = copyCurr.next;
            curr = curr.next;
        }

        return dummy.next;
    }
}
