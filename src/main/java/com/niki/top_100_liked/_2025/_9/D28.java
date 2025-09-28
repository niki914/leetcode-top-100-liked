package com.niki.top_100_liked._2025._9;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

import java.util.Arrays;

class D28 {

    @QuestionInfo(
        name = "最长递增子序列",
        type = QuestionType.DynamicProgramming,
        difficulty = QuestionDifficulty.MEDIUM,
        link = "https://leetcode.cn/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        /*
            nums = [10, 9, 2, 5, 3, 7, 101, 18]
            returns: 4
            最长递增子序列是 [2, 3, 7, 101], 此长度为 4
         */

        int n = nums.length;
        // dp[i] 表示以 nums[i] 结尾的最长递增子序列长度
        int[] dp = new int[n];
        // 初始化：每个元素至少可以单独构成长度为 1 的子序列
        Arrays.fill(dp, 1);
        int maxLen = 1; // 记录全局最大长度

        // 遍历每个位置 i
        for (int i = 1; i < n; i++) {
            // 检查所有 j < i，尝试扩展子序列
            for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                // 如果 nums[i] 可以接在 nums[j] 后面，尝试更新 dp[i]
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
            maxLen = Math.max(maxLen, dp[i]); // 更新全局最大长度
        }

        return maxLen;
    }
}