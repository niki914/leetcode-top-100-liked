package com.niki914.top_100_liked._2025._10;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

public class D25 {

    @QuestionInfo(
            name = "编辑距离",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/edit-distance/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int minDistance(String word1, String word2) {
        // "app" -> "sap"
        // app -> spp -> sap

        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // dp[i][j] 表示 word1[0..i-1] 转换为 word2[0..j-1] 的最小操作数
        // 比如 dp[1][3] 就是 'a' -> 'sap' 的步骤数

        // 初始化：空字符串转换
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // 删除 i 个字符
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // 插入 j 个字符
        }

        // 动态规划填充 dp 表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 字符相同，无需操作
                } else {
                    // 比如:
                    // 1. 插入
                    //    god -> good: god -> goo(替换) -> good(插入)
                    // 2. 替换
                    //    car -> cat: car -> cat(替换)
                    dp[i][j] = Math.min(
                            Math.min(
                                    dp[i][j - 1], // 插入: send -> candy, 只需要退化问题, 在 computeStepsOf('send -> cand') 的基础 ( 2 步 ) 上加 1 步 (插入)
                                    dp[i - 1][j] // 删除: cart -> car, 只需要退化问题, 在 computeStepsOf('car -> car') 的基础 ( 0 步 ) 上加 1 步 (删除)
                            ),
                            dp[i - 1][j - 1] // 替换: bar -> cat, 只需要退化问题, 在 computeStepsOf('ba -> ca') 的基础 ( 1 步 ) 上加 1 步 (替换)
                    ) + 1;
                }
            }
        }

        return dp[m][n];
    }
}