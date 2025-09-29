package com.niki.top_100_liked._2025._9;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;
import com.niki.top_100_liked.util.annotation.SuspendQuestion;

import java.util.Arrays;

class D29 {

    @QuestionInfo(
            name = "最长递增子序列",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        /*
            nums = [10, 9, 2, 5, 3, 7, 101, 18]
            returns: 4
            最长递增子序列是 [2, 3, 7, 101], 此长度为 4
         */

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
                if (nums[i] > nums[j]) { // 只能说贪心真的简单又粗暴
                    // 如果 nums[i] 可以接在 nums[j] 后面，尝试更新 dp[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]); // 更新全局最大长度
        }

        return maxLen;
    }

    @QuestionInfo(
            name = "乘积最大子数组",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/maximum-product-subarray/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int maxProduct(int[] nums) {
        // 初始化全局最大乘积为数组第一个元素
        int maxProduct = nums[0];
        // maxSoFar记录以当前元素结尾的最大乘积
        // minSoFar记录以当前元素结尾的最小乘积, 最小的也可能再成一个负数变成最大的
        // 本题所有 min 的c参数都几乎只是记录作用, 不参与 dp 的更新
        int maxSoFar = nums[0], minSoFar = nums[0];

        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            // 记录上一次的最大和最小乘积，用于计算
            int tempMax = maxSoFar;
            int tempMin = minSoFar;

            int temp1 = tempMax * nums[i];
            int temp2 = tempMin * nums[i];

            // 当前最大乘积：取当前元素、当前元素*前最大、当前元素*前最小三者中的最大值
            // > 这样可能效率低一点，提前计算乘积会好一点
            maxSoFar = Math.max(nums[i], Math.max(temp1, temp2));
            // 当前最小乘积：取当前元素、当前元素*前最大、当前元素*前最小三者中的最小值
            minSoFar = Math.min(nums[i], Math.min(temp1, temp2));

            // 更新全局最大乘积
            maxProduct = Math.max(maxProduct, maxSoFar);
        }

        return maxProduct;
    }

    @QuestionInfo(
            name = "分割等和子集",
            type = QuestionType.DynamicProgramming,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/partition-equal-subset-sum/description/"
    )
    @SuspendQuestion(
            name = "分割等和子集",
            reason = "难"
    )
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num; // 计算数组总和
        }

        // 如果总和为奇数，无法分割成两个相等子集
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2; // 目标是找到和为sum/2的子集
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // 和为0总是可行的（空子集）

        // 遍历每个数字
        for (int num : nums) {
            // 从大到小遍历，避免覆盖已更新的状态
            for (int i = target; i >= num; i--) {
                // i = target downTo num
                if (dp[i - num]) {
                    dp[i] = true;
                }
            }
        }

        return dp[target]; // 返回是否能达到目标和
    }
}