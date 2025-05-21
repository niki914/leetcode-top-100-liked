package com.niki.top_100_liked._2025._5;

import com.niki.top_100_liked.beans.ListNode;
import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;
import com.niki.top_100_liked.util.annotation.SuspendQuestion;

public class D21 {

    @QuestionInfo(
            name = "删除链表的倒数第 N 个结点",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // 哑节点简化边界处理
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // 快指针先走 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 快慢指针同步移动, 直到快指针到达末尾
        // 然后慢指针的 next 就指向了要删除的节点
        // 这种差一个两个的东西自己试一试就行
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除慢指针的下一个节点
        slow.next = slow.next.next;

        return dummy.next; // 返回头节点
    }

    @QuestionInfo(
            name = "两两交换链表中的节点",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/swap-nodes-in-pairs/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public ListNode swapPairs(ListNode head) {
        // 空链表或单节点(没得换), 直接返回
        if (head == null || head.next == null) {
            return head;
        }

        /*
         对于当前一对节点 [a]->[b]->[c]:
         1. 保存 [b]
         2. 递归处理 [c] 后的子链表, 得到交换后的结果
         3. 交换 [a] 和 [b], 并将 [a] 连接到递归结果
         4. 返回 [b] 作为新头节点

         有图片
         */

        // 保存下一节点
        ListNode next = head.next;
        // 递归处理后续节点
        // 递归在某种程度上靠的是一种感觉, 你要相信经过这个操作后, 后面的数据是你想要的样子
        // 就像中序遍历二叉树一样, 先递归 lchild, 访问自己, 递归 rchild
        ListNode rest = swapPairs(next.next);

        // head 将被调到二号位, 它的后面要接的是已经递归排序好的剩余链节(以三号位为头节点, 这个我们不用管, 交给递归)
        head.next = rest;

        next.next = head;
        // next 作为一号位返回
        return next;
    }

    @QuestionInfo(
            name = "K 个一组翻转链表",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/reverse-nodes-in-k-group/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    @SuspendQuestion(
            name = "hoo",
            reason = "grok次数用完了, 学别的去了, 明天问一下有没有和上一题一样的递归解法")
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // 哑节点简化操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;

        // 计算链表长度
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // 每 k 个节点翻转
        for (int i = 0; i < length / k; i++) {
            ListNode prev = null;
            curr = prevGroup.next;
            // 翻转 k 个节点
            for (int j = 0; j < k; j++) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            // 连接翻转后的组
            ListNode nextGroup = curr;
            prevGroup.next.next = nextGroup;
            ListNode newGroupHead = prev;
            prevGroup.next = newGroupHead;
            prevGroup = prevGroup.next.next;
        }

        return dummy.next;
    }
}
