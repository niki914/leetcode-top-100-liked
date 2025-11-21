package com.niki914.top_100_liked._2025._9;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

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
        // dp[i] 表示 sub string [0, i) 能否被拼接
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
}