package com.niki914.top_100_liked._2025._5;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.beans.ListNode;
import com.niki914.top_100_liked.beans.Node;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;
import com.niki914.top_100_liked.util.annotation.SuspendQuestion;

@Link(
        last = D21.class,
        next = D26.class
)
public class D24 implements Day {

    @QuestionInfo(
            name = "随机链表的复制",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 11
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

    @QuestionInfo(
            name = "排序链表",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/sort-list/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 12
    )
    public ListNode sortList(ListNode head) {
        /*
        这个解法的核心是归并排序(Merge Sort), 一种分治法

        分: [4,2,1,3] -> [4,2], [1,3] -> [4], [2], [1], [3]
        治: 递归地对每个子链表进行排序, 直到子链表长度为1(无需排序), 然后: [2,4], [1,3]
        合并: 合并有序链表 [2,4], [1,3] -> [1,2,3,4]
         */

        // 1. 前置判断, 空链表或单节点无需排序
        if (head == null || head.next == null) {
            return head;
        }

        // 2. 分: 用快慢指针找中点, 分割链表
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 分割成两部分, 右半部分从slow.next开始
        ListNode right = slow.next;
        slow.next = null; // 断开左半部分

        // 3. 治: 对左右两部分分别递归排序
        ListNode left = sortList(head);
        right = sortList(right);

        // 4. 合并两个有序链表并返回
        // 从最小单元开始分析, 假设 left, right 长度均为 1, 这时就会向上级返回一个长度为 2 的升序链表
        // 然后一级一级往上看, 不难发现确实把链表排好了
        return new D20().mergeTwoLists(left, right);
    }

    @QuestionInfo(
            name = "合并K个升序链表",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/merge-k-sorted-lists/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 13
    )
    @SuspendQuestion(
            name = "合并K个升序链表",
            reason = "为求效率掠过困难题"
    )
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        // 分治合并
        // mergeKListsHelper 是主逻辑
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    /**
     * 分治法其实很像先是构造一个二叉树, 然后从叶子开始, 两个两个合并(一个接到另一个上)
     * 最终二叉树变成单链表, 排序就完成了
     */
    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        // 只有一个链表, 直接返回
        if (start == end) {
            return lists[start];
        }
        // 两个链表, 调用merge合并
        if (start + 1 == end) {
            return new D20().mergeTwoLists(lists[start], lists[end]);
        }

        // 多链表: 分治: 将链表数组分为两部分
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);

        // 合并左右两部分
        return new D20().mergeTwoLists(left, right);
    }
}
