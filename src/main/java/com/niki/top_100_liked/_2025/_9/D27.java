package com.niki.top_100_liked._2025._9;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

import java.util.*;

public class D27 {

    @QuestionInfo(
            name = "单词拆分",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/word-break/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public boolean wordBreak(String s, List<String> wordDict) {
        // 将 wordDict 转为 Set 加速查找
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        // dp[i] 表示 s[0:i) 能否被拼接
        boolean[] dp = new boolean[n + 1];
        // 空字符串可以拼接
        /*
            比方说: 'leetcode', [leet, code], i = 4, j = 0, subStr = 'leet'
            此时需要 dp[0] == true 才能将 dp[4] 设为真
         */
        dp[0] = true;

        // 遍历字符串长度
        for (int i = 1; i <= n; i++) {
            // 尝试所有可能的子串起点 j
            for (int j = 0; j < i; j++) {
                // 如果 dp[j] 为 true（前 j 个字符可拼接）且 s[j:i) 在 wordDict 中
                /*
                    i = 7, j = 4, subStr = 'code', dp[4] = true
                    => dp[7] = true
                 */
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 找到一种拼接方式即可
                }
            }
        }

        return dp[n];
    }

    @QuestionInfo(
            name = "最长递增子序列",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

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