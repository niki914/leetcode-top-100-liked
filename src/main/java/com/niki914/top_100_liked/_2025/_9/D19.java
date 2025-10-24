package com.niki914.top_100_liked._2025._9;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

public class D19 {

    @QuestionInfo(
            name = "跳跃游戏 II",
            type = QuestionType.Greedy,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int jump(int[] nums) {
        // 求最小跳跃次数

        /*
            输入: nums = [2,3,1,1,4]
            输出: 2
         */

        int n = nums.length;
        int jumps = 0; // 记录跳跃次数
        int currEnd = 0; // 当前跳跃能覆盖的范围的右边界
        int nextMaxReach = 0; // 下一步能到达的最远距离

        // 遍历到倒数第二个元素，因为到达最后一个元素就结束
        for (int i = 0; i < n - 1; i++) {
            // 更新下一步能到达的最远距离
            nextMaxReach = Math.max(nextMaxReach, i + nums[i]);

            // 到达当前跳跃的边界，需进行下一次跳跃
            // 在到达当前边界之后，我们已经推断并用 nextMaxReach 记录了这一跳最远可达的点，所以准备下一跳
            if (i == currEnd) {
                jumps++; // 增加跳跃次数
                currEnd = nextMaxReach; // 更新当前跳跃范围的右边界

                if (currEnd >= n - 1) { // 其实并没有实际作用，在满足这个条件时其实： i = currEnd >= n - 1
                    return jumps;
                }
            }
        }

        return jumps;
    }
}