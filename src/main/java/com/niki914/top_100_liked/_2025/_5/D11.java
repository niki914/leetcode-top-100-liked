package com.niki914.top_100_liked._2025._5;

import com.niki914.top_100_liked.beans.ListNode;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

public class D11 {

    @QuestionInfo(
            name = "搜索二维矩阵 II",
            type = QuestionType.Matrix,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/search-a-2d-matrix-ii/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length, cols = matrix[0].length;

        /*
        列从 cols - 1 开始递减的原因: 先找到所在行, 然后从该行最大值开始降序遍历寻找目标值(也可以反过来先寻找所在列)
        如果从 0 开始递增, 每次访问的 value 就是该行的最小值, 不好判断 target 是否在本行
         */
        int row = 0, col = cols - 1;

        while (row < rows && col >= 0) {
            int value = matrix[row][col];

            if (value == target)
                return true;

            if (value > target) {
                col--; // 目标值比当前值小, 排除当前列
            } else {
                row++; // 目标值比当前值大, 排除当前行
            }
        }

        return false;
    }

    @QuestionInfo(
            name = "相交链表",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/intersection-of-two-linked-lists/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        /*
        假设链表 A 和 B 的非相交部分长度分别为 a 和 b, 相交部分长度为 c
        则 pA 遍历路径为 a+c+b, pB 为 b+c+a, 两者总长度相等, 必然在相交节点或 null 处相遇

        太牛了, 给我一辈子都想不到
         */
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;
    }

    @QuestionInfo(
            name = "反转链表",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/reverse-linked-list/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public ListNode reverseList(ListNode head) {
        // https://www.bilibili.com/video/BV1Lg411K7py?spm_id_from=333.788.recommend_more_video.2&vd_source=d1bc8ca6314de4d362247f172f7d13ca
        ListNode prev = null;
        ListNode curr = head;

        /*
         大致流程:
         head -> [1] -> [2] -> [3] -> null

         head    [1] -> [2] -> [3] -> null
         head <- [1]    [2] -> [3] -> null
         head <- [1] <- [2]    [3] -> null
         head <- [1] <- [2] <- [3] <- null

         每一个我们要反转其 next 指针的项就是 curr, 为了不丢失被断开的那些节点我们需要用 next 和 prev 来持有他们然后进行反转:
         prev    curr -> next
         prev <- curr    next
         在反转完成后我们移动 prev 和 curr 来处理后面的节点
         */
        while (curr != null) {
            ListNode next = curr.next; // 保存下一个节点
            curr.next = prev;          // 反转当前节点的指针
            prev = curr;               // 前移 prev
            curr = next;               // 前移 curr
        }

        return prev; // prev 是新链表的头节点
    }
}