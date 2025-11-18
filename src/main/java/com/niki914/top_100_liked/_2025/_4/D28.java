package com.niki914.top_100_liked._2025._4;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayDeque;
import java.util.Deque;

@Link(
        last = D27.class,
        next = D29.class
)
public class D28 implements Day {

    @QuestionInfo(
            name = "滑动窗口最大值",
            type = QuestionType.Substring,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/sliding-window-maximum/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 2
    )
    public int[] findMaxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];

        int n = nums.length;
        int[] result = new int[n - k + 1]; // 结果数组大小
        Deque<Integer> deque = new ArrayDeque<>(); // 存储索引的单调队列

        /*
        手动跑一次 [1, 4, 2, 3, 0, 9], k = 3

        i = 0:
        deque = [0(1)]

        i = 1:
        deque = [1(4)]

        i = 2:
        deque = [1(4), 2(2)]
        result = [4]

        i = 3:
        deque = [1(4), 3(3)]
        result = [4, 4]

        i = 4:
        deque = [3(3), 4(0)]
        result = [4, 4, 3]

        i = 5:
        deque = [5(9)]
        result = [4, 4, 3, 9]

         */

        // 窗口范围: [i - k + 1, i]
        for (int i = 0; i < n; i++) {
            // 移除窗口外的元素
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst(); // 从队头移除
            }
            // 确保下面均为窗口内的数

            int num = nums[i];

            // 如果当前数比队尾的大则移除
            // 目的是保持队列的单调性(递减), 这样我们获取队头即为窗口内的最大值
            while (!deque.isEmpty() && nums[deque.peekLast()] < num) {
                deque.pollLast(); // 从队尾移除
            }

            deque.offerLast(i); // 从队尾插入当前索引

            if (i < k - 1)
                continue;

            // 维护完成, 获取队头的数字就是当前窗口的最大值
            // 若窗口大小是 k(窗口已被正确初始化), 存入结果数组
            result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }
}
