package com.niki.top_100_liked._2025._9;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

public class D22 {

    @QuestionInfo(
            name = "打家劫舍",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/house-robber/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // dp[i] 表示偷窃到第 i 间房屋能得到的最大金额
        int[] dp = new int[nums.length];
        dp[0] = nums[0]; // 第一间房屋
        dp[1] = Math.max(nums[0], nums[1]); // 前两间房屋取最大值

        // 从第 3 间房屋开始，决定是否偷当前房屋
        for (int i = 2; i < nums.length; i++) {
            // 偷当前房屋（加上前 i-2 房屋的最大值）或不偷（取前 i-1 房屋的最大值）
            // 本题关键就是这个状态转移方程
            // dp 的一个元素代表在前 i-1 个房间行窃能得到最多的钱
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }
}