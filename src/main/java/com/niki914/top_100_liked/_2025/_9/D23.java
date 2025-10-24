package com.niki914.top_100_liked._2025._9;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.Arrays;

public class D23 {

    @QuestionInfo(
            name = "完全平方数",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/perfect-squares/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int numSquares(int n) {
        // dp[i]表示和为i的完全平方数的最少数量
        int[] dp = new int[n + 1];
        // 初始化：最差情况是用i个1，设为i+1
        for (int i = 0; i <= n; i++) {
            dp[i] = i + 1;
        }
        dp[0] = 0; // 和为0不需要完全平方数

        // 遍历每个和i
        for (int i = 1; i <= n; i++) {
            // 枚举可能的完全平方数j*j
            for (int j = 1; j * j <= i; j++) {
                // 状态转移：dp[i] = min(dp[i - j*j] + 1)
                // 例如: 17 = (4)^2 + 1
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

    @QuestionInfo(
            name = "零钱兑换",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/coin-change/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int coinChange(int[] coins, int amount) {
        // dp[i]表示凑成金额i所需的最少硬币个数
        int[] dp = new int[amount + 1];
        // 初始化：设为一个大值，表示不可达
        Arrays.fill(dp, amount + 1);
        dp[0] = 0; // 金额0需要0个硬币

        // 遍历每个金额i
        for (int i = 1; i <= amount; i++) {
            // 枚举每种硬币面额
            for (int coin : coins) {
                if (coin <= i) {
                    // 状态转移：dp[i] = min(dp[i], dp[i - coin] + 1)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 如果dp[amount]仍为初始值，说明无法凑成
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}