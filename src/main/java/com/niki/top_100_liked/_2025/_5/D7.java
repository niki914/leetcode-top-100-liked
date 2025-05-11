package com.niki.top_100_liked._2025._5;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D7 {

    @QuestionInfo(
            name = "最大子数组和",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int maxSubArray(int[] nums) {
        // 感觉巧妙地避开了许多问题
        // [-2,1,-3,4,-1,2,1,-5,4] -> [4,-1,2,1] -> 6

        // 当前子数组的最大和
        int maxSum = nums[0];
        // 当前子数组的和
        int currentSum = nums[0];

        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            // 当前元素要么自成子数组, 要么加入前面的子数组
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // 更新全局最大和
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    @QuestionInfo(
            name = "合并区间",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int[][] merge(int[][] intervals) {
        // 按起始时间(子数组的首元素)排序
        // a[0] - b[0] 的返回值决定了排序顺序
        // 如果 < 0, 则 a 排在 b 前面(a[0] < b[0])
        // 如果 == 0, 则顺序不变
        // 如果 > 0, 则 b 排在 a 前面(a[0] > b[0])
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];

        // 遍历区间
        for (int i = 1; i < intervals.length; i++) {
            // 比较 a[][1] 与 b[][0], 条件是原数组已经排序, a[][0] 必小于 b[][0]:
            if (currentInterval[1] < intervals[i][0]) { // 不重叠, 将当前区间加入结果
                result.add(currentInterval);
                currentInterval = intervals[i];
            } else { // 重叠, 合并两区间(只是改变当前数组的[1]位置的值, `加入结果`的操作留给后面的循环)
                currentInterval[1] = Math.max(currentInterval[1], intervals[i][1]);
            }
        }

        // 加入最后一个区间
        result.add(currentInterval);

        // 转换为数组返回
        return result.toArray(new int[result.size()][]);
    }

    @QuestionInfo(
            name = "轮转数组",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public static class Rotate {
        public void rotate(int[] nums, int k) {
            // 处理 k 可能大于数组长度的情况
            k = k % nums.length;
            if (k == 0) return;

            /*
             * 非常面向结果地实现目的
             * [1,2,3,4,5,6], k = 2
             *
             * 反转整个数组:    [6,5,   4,3,2,1]
             * 反转前 k 个元素: [5,6, | 4,3,2,1]
             * 反转剩余元素:    [5,6, | 1,2,3,4]
             */

            // 1. 反转整个数组
            reverse(nums, 0, nums.length - 1);
            // 2. 反转前 k 个元素
            reverse(nums, 0, k - 1);
            // 3. 反转剩余元素
            reverse(nums, k, nums.length - 1);
        }

        // 辅助方法: 反转数组指定区间 [start, end]
        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }
}
