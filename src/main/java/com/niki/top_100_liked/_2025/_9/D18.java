package com.niki.top_100_liked._2025._9;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

public class D18 {

    @QuestionInfo(
            name = "买卖股票的最佳时机",
            type = QuestionType.Greedy,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0; // 数组为空或长度不足2，无法交易

        int minPrice = prices[0]; // 记录最低买入价格
        int maxProfit = 0; // 记录最大利润

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i]; // 更新最低买入价格
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice); // 更新最大利润
            }
        }

        return maxProfit;
    }

    @QuestionInfo(
            name = "跳跃游戏",
            type = QuestionType.Greedy,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/jump-game/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public boolean canJump(int[] nums) {
        // 不必猜忌后面的结果，只要计算最大的到达位置即可
        int maxReach = 0; // 记录能到达的最远下标
        for (int i = 0; i < nums.length; i++) {
            // 当前下标无法到达: 其实相当于 continue,
            // 因为是此时的 i 索引是没有意义的, 根本跳不到这里
            if (i > maxReach) return false;

            maxReach = Math.max(maxReach, i + nums[i]); // 更新最远可达下标
            if (maxReach >= nums.length - 1) return true; // 能到达最后一个下标
        }
        return true; // 遍历完成，说明可达
    }
}
