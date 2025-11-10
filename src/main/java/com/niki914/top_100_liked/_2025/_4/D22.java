package com.niki914.top_100_liked._2025._4;

import static java.lang.Math.min;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D22 {
    @QuestionInfo(
            name = "乘最多水的容器",
            type = QuestionType.TwoPointers,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 2
    )
    public int maxArea(int[] heights) {

        /*
        2025.11.10 review 解出
        public int maxArea(int[] height){
            int l = 0, r = height.length - 1;
            int max = 0;
            while (l < r) {
                int width = r - l;
                int min, minHeight;

                if (height[r] > height[l]) {
                    min = l;
                    l++;
                } else {
                    min = r;
                    r--;
                }

                minHeight = height[min];

                int v = width * minHeight;

                if (v > max) {
                    max = v;
                }
            }
            return max;
        }
         */

        int leftP = 0;
        int rightP = heights.length - 1;

        int max = 0;

        // 贪心策略: 在每一步选择局部最优解, 最终得到全局最优解
        // lcy 聊过的海盗分金币 - 策略体现在: 给[刚好够票数的低资历海盗]最小金币, 自己拿最多
        while (leftP < rightP) {
            // 用短边和距离来计算水量
            int _max = min(heights[leftP], heights[rightP]) * (rightP - leftP);
            // 更新最大值
            if (max < _max)
                max = _max;

            // 本题的贪心策略体现在放弃短边
            if (heights[leftP] < heights[rightP]) {
                leftP++;
            } else {
                rightP--;
            }
        }

        return max;
    }

    @QuestionInfo(
            name = "三数之和",
            type = QuestionType.TwoPointers,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/3sum/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 3
    )
    public List<List<Integer>> threeSum(int[] nums) {
        int size = nums.length;
        if (size < 3) return new ArrayList<>(); // 长度小于 3, 无解
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int first = 0; first < size - 2; first++) { // 防止越界
            // 在顺序排列的情况下, 后面的数都是正数
            // 因而后续的[三数之和]都大于 0, 循环没有继续的必要了
            if (nums[first] > 0)
                break;

            // 跳过重复项同时避免越界
            if (first > 0 && nums[first] == nums[first - 1])
                continue;

            // 初始化双指针
            int l = first + 1;
            int r = size - 1;

            while (l < r) {
                int sum = nums[first] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[first], nums[l], nums[r]));

                    // 对重复的数字进行跳过, 因为我们不需要重复的解
                    while (l < r && nums[l] == nums[l + 1])
                        l++; // 跳过重复项
                    while (l < r && nums[r] == nums[r - 1])
                        r--; // 跳过重复项

                    // 此时我们依然落在相同的解上, 不移动或只移动一个(由于进行了查重)都不会再得到新的解, 因此我们移动两个指针
                    l++;
                    r--;
                } else if (sum > 0) { // 接下来要让[三数之和]更小
                    r--;
                } else { // 接下来要让[三数之和]更大
                    l++;
                }
            }
        }
        return result;
    }
}