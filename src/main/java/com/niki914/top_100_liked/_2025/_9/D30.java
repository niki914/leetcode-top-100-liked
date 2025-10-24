package com.niki914.top_100_liked._2025._9;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;
import com.niki914.top_100_liked.util.annotation.SuspendQuestion;

public class D30 {

    @QuestionInfo(
            name = "最长有效括号",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/longest-valid-parentheses/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    @SuspendQuestion(
            name = "最长有效括号",
            reason = "难"
    )
    public int longestValidParentheses(String s) {
        int n = s.length();
        // dp[i] 表示以索引 i 结尾的子串中最长有效括号长度
        int[] dp = new int[n];
        int maxLen = 0; // 记录全局最长有效括号长度

        // 从索引 1 开始，dp[0] 无意义（单个字符无法形成有效括号）
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // Case 1: 当前是 "()", 直接匹配
                    // dp[i] = dp[i-2] + 2（前面的有效长度 + 当前 "()")
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // Case 2: 当前是 "))"，尝试匹配前面的 "("
                    // 找到 i-dp[i-1]-1 位置的字符，若为 '('，则形成有效括号
                    // dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
                // 更新全局最大长度
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }
}