package com.niki.top_100_liked._2025._5;

import com.niki.top_100_liked.beans.ListNode;
import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;
import com.niki.top_100_liked.util.annotation.SuspendQuestion;

public class D20 {
    @QuestionInfo(
            name = "合并两个有序链表",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/merge-two-sorted-lists/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode l = new ListNode(0); // 哑节点简化合并(方便操作头节点)
        ListNode curr = l; // 当前指针

        while (list1 != null && list2 != null) {
            // 比较, 添加更小的一节并后移响应的指针
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next; // 后移
        }

        // 连接剩余节点(此时有一个链表已经到达末尾, 要把另一个链表的剩余数据全部添加)
        if (list1 != null) curr.next = list1;
        if (list2 != null) curr.next = list2;

        return l.next; // 返回合并后的头节点
    }

    @QuestionInfo(
            name = "两数相加",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/add-two-numbers/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    @SuspendQuestion(
            name = "两数相加",
            reason = "lazy"
    )
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 哑节点简化头节点处理
        ListNode curr = dummy;
        int carry = 0; // 进位

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0; // 获取 l1 的值，无节点则为 0
            int y = (l2 != null) ? l2.val : 0; // 获取 l2 的值，无节点则为 0
            int sum = x + y + carry; // 计算当前位和

            carry = sum / 10; // 更新进位
            curr.next = new ListNode(sum % 10); // 创建新节点存储当前位
            curr = curr.next;

            // 前移指针
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next; // 返回结果链表的头节点
    }
}
