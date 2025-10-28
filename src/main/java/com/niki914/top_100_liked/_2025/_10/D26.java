package com.niki914.top_100_liked._2025._10;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

public class D26 {

    @QuestionInfo(
            name = "只出现一次的数字",
            type = QuestionType.Skill,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/single-number/description/?envType=study-plan-v2&envId=top-100-liked" // LeetCode 136
    )
    public int singleNumber(int[] nums) {
        // 异或结果初始化为 0
        int result = 0;

        for (int num : nums) {
            // 核心操作：异或。
            // 1. 两个相同的数字异或结果为 0 (a ^ a = 0)
            // 2. 任何数字与 0 异或等于它本身 (a ^ 0 = a)

            // 根据题目要求可以假设这些异或的数字最后都被消除为 0
            // , 比如:
            //     9 ^ 8 ^ 6 ^ 9 ^ 8
            //   = 9 ^ 9 ^ 8 ^ 8 ^ 6
            //   = 0 ^ 0 ^ 6
            //   = 6
            result = result ^ num;
        }

        return result;
    }

    @QuestionInfo(
            name = "多数元素",
            type = QuestionType.Skill,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/majority-element/description/?envType=study-plan-v2&envId=top-100-liked" // LeetCode 169
    )
    public int majorityElement(int[] nums) {
        // > 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
        // > 即, 需要多于半数
        // > 摩尔投票算法 (Boyer-Moore Voting Algorithm):
        // > 如果在数组中有一个元素的数量超过总数的一半，那么该元素与其他所有元素进行“抵消”后，它必然是最后剩下的那个。

        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                // 1. 如果计数器归零，说明之前的候选人已被“抵消”完，
                //    将当前元素设为新的候选人。
                candidate = num;
                count = 1;
            } else { // 哎, 记住吧, 有点理解不了但是找不到反驳点
                if (num == candidate) { // 2. 如果当前元素与候选人相同，计数器加 1。
                    count++;
                } else { // 3. 如果不同，计数器减 1，表示互相抵消一次。
                    count--;
                }
            }
        }

        // 题目保证多数元素一定存在，因此 candidate 即为结果
        return candidate;
    }
}