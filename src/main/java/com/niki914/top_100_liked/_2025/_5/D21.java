package com.niki914.top_100_liked._2025._5;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.beans.ListNode;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;
import com.niki914.top_100_liked.util.annotation.SuspendQuestion;

@Link(
        last = D20.class,
        next = D24.class
)
public class D21 implements Day {

    @QuestionInfo(
            name = "删除链表的倒数第 N 个结点",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 8
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
            link = "https://leetcode.cn/problems/swap-nodes-in-pairs/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 9
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
            link = "https://leetcode.cn/problems/reverse-nodes-in-k-group/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 10
    )
    @SuspendQuestion(
            name = "K 个一组翻转链表",
            reason = "为求效率掠过困难题"
    )
    public ListNode reverseKGroup(ListNode head, int k) {
        /*
        前瞻:
        1. 检查是否需要反转
        2. 反转
        3. 递归剩下的(k 以后的)
        此时有点乱了, 看看链表状态: 
        递归前: [prev] ... [head] [curr] ......
        递归后: [prev] ... [head]  ... rest ...
        至于为什么是 prev 为头, 这和反转逻辑有关, 忘了的话重新推导一次就行
        4. 因此, head 要接上 rest, 也就是递归后的头节点
        5. 最后返回头部, 也就是 prev
         */

        // 1. 数数是否足够 k 个节点
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        // 如果不足 k 个节点, 不倒转了, 直接返回 head
        if (count < k) {
            return head;
        }

        // 2. 翻转当前 k 个节点
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        for (int i = 0; i < k; i++) {
            next = current.next;        // 保存下一个节点
            current.next = prev;        // 反转当前节点的指针
            prev = current;             // 前进 prev
            current = next;             // 前进 current
        }
        // 此时 prev 是 k 组的头部, head 是尾部, current 是下一组的起始节点
        // 这个前面做过, 之前的题目中: 返回结果的时候返回 pre, 因为 current 总是 null
        // 现在这题情况差不多, 只不过 current 指向剩下的部分
        // for 循环不同点是用 k 限制反转个数

        // 3. 递归处理后续节点
        // 依旧抽象、依旧信任
        ListNode rest = reverseKGroup(current, k);

        // 4. 将当前 k 组的尾部(head)连接到递归结果
        head.next = rest;

        // 5. 返回当前 k 组的头部
        return prev;
    }
}
