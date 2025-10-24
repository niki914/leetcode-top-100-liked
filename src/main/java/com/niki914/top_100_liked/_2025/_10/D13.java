package com.niki914.top_100_liked._2025._10;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

public class D13 {

    @QuestionInfo(
            name = "最长回文子串",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/longest-palindromic-substring/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";

        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // dp[i][j] 表示 s[i..j] 是否为回文
        int start = 0, maxLen = 1; // 记录最长回文子串的起始位置和长度

        // 初始化：单个字符是回文
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 初始化：两个字符的情况
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }

        // 动态规划：检查长度为3及以上的子串
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {

                int j = i + len - 1; // 子串结束位置
                boolean subStringIsPalindrome = (len == 3 /* 例外情况：长度为 3 */ || dp[i + 1][j - 1] /* 其他情况使用 dp */);
                if (s.charAt(i) == s.charAt(j) && subStringIsPalindrome) {
                    dp[i][j] = true;

                    // 更新现有最大长度
                    if (len > maxLen) {
                        start = i;
                        maxLen = len;
                    }
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    @QuestionInfo(
            name = "最长公共子序列",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/longest-common-subsequence/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int longestCommonSubsequence(String text1, String text2) {
        int text1len = text1.length(), text2len = text2.length();
        int[][] dp = new int[text1len + 1][text2len + 1];
        // dp[i][j] 表示 text1[0..i-1] 和 text2[0..j-1] 的 LCS(Longest Common Subsequence) 长度

        // 动态规划填充 dp 表
        for (int i = 1; i <= text1len; i++) {
            for (int j = 1; j <= text2len; j++) {

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 当前字符相同，LCS 长度加1
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 取去掉任一字符后的最大值
                }
            }
        }

        return dp[text1len][text2len];
    }
}