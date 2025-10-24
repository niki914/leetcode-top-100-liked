package com.niki914.top_100_liked._2025._9;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class D20 {

    @QuestionInfo(
            name = "划分字母区间",
            type = QuestionType.Greedy,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/partition-labels/description/"
    )
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();

        // 记录每个字母的最后出现位置
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        // 遍历字符串, 维护片段的start和end
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 更新当前片段的最远结束位置
            int lastPosOfSameCharAsI = last[s.charAt(i) - 'a'];
            end = Math.max(end, lastPosOfSameCharAsI);

            // 如果当前索引到达end, 说明找到一个片段
            // i 能够到达 end 说明 i 迭代了若干个字符并且取 max 没有使得 end 跳到更后面
            // 这说明 start ~ end 里面包含了最后出现的字母, 所以认为找到了片段
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1; // 下一片段的开始
            }
        }

        return result;
    }

    @QuestionInfo(
            name = "爬楼梯",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/climbing-stairs/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int climbStairs(int n) {
        if (n <= 2) return n; // 1阶:1种, 2阶:2种

        // 动态规划的状态转移方程
        // dp[i] = dp[i-1] + dp[i-2]: 第 i 步的方法数 = (i - 1) + (i - 2), 指的是复用子问题的答案
        // 根据这条状态方程, 前两步的方法数是已知的, 然后你有了一二步就能推断第三步
        // 然后又可以根据二三步推断第四步, 以此类推即可计算第 n 步

        // 使用滚动变量优化空间
        int prev1 = 1; // dp[i-1]
        int prev2 = 2; // dp[i-2]
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2; // dp[i] = dp[i-1] + dp[i-2]
            prev1 = prev2; // 更新前两步, 这有点像移动指针, 不赘述
            prev2 = curr;
        }
        return prev2;
    }

    @QuestionInfo(
            name = "杨辉三角",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/pascals-triangle/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) return result;

        // 第0行: [1]
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        result.add(firstRow);

        // 从第1行开始, 基于上一行生成
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = result.get(i - 1);

            // 每行首元素为1
            row.add(1);

            // 中间元素 (index = 1 ~ i-1): dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
            // 最后一个中间元素的索引恰好为 i - 1, 因为元素个数 = 行数
            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            // 每行末元素为1
            row.add(1);

            result.add(row);
        }

        return result;
    }
}