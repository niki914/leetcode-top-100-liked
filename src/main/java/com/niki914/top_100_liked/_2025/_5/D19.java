package com.niki914.top_100_liked._2025._5;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.beans.ListNode;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

@Link(
        last = D11.class,
        next = D20.class
)
public class D19 implements Day {

    @QuestionInfo(
            name = "回文链表",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/palindrome-linked-list/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 3
    )
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1. 找到中间节点(快慢指针)
        /*
        一种思想, 慢指针走一步, 快指针走两步, 快的到达末尾, 慢的恰好到中间

        ⚪ ⚪ 🔵 ⚪ 🔴
        ⚪ ⚪ 🔵 ⚪ 🔴 ⚪
        因为是从 head 开始进行比较, 奇偶数不影响后续的操作
        */
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转后半部分链表, 也就是从 slow 的后面
        ListNode secondHalf = new D11().reverseList(slow.next);

        // 3. 比较前半部分和反转后的后半部分
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    @QuestionInfo(
            name = "环形链表",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/linked-list-cycle/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 4
    )
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        /*
          假设有环, 那么快慢指针都会进入环
          跑步的时候有的人跑得快可以超过你好几圈, 这里也是这种情况, 快指针会追上慢指针
         */
        while (fast != null && fast.next != null) {
            slow = slow.next;          // 慢指针走一步
            fast = fast.next.next;     // 快指针走两步
            if (slow == fast) {        // 快慢指针相遇, 说明有环
                return true;
            }
        }

        return false; // 快指针到达末尾, 无环
    }

    @QuestionInfo(
            name = "环形链表 II",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/linked-list-cycle-ii/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 5
    )
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        // 快慢指针找相遇点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        // 无环
        if (fast == null || fast.next == null) {
            return null;
        }

        // 慢指针从头开始, 快指针从相遇点继续, 两者相遇即入环点
        // 知道就行, 不试图理解了
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
