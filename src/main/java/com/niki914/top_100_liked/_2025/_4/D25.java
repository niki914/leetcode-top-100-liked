package com.niki914.top_100_liked._2025._4;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.HashMap;
import java.util.Map;

public class D25 {
    @QuestionInfo(
            name = "接雨水",
            type = QuestionType.TwoPointers,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/trapping-rain-water/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int trap(int[] height) {
        if (height.length == 0) return 0;

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

            /*

            |💧 💧 💧
            |💧 💧 💧
            |💧|💧 💧
            |💧|💧|💧
            ----------

               |💧 💧
            |💧|💧|💧
            ----------

            每列雨水量由左右最大高度的较小值决定
            双指针更新最大高度，确保雨水量计算时 height[i] 不超过当前边界:
            a) 当两者相等, 该列水量为 0
            b) height 更小, 计算水量
             */
        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];

            // 更新最高的边界
            if (leftHeight > leftMax) leftMax = leftHeight;
            if (rightHeight > rightMax) rightMax = rightHeight;

            // 左右边界不等的情况下应该处理更短的一方
            // 计算该列的接水量并移动指针
            if (leftMax < rightMax) {
                water += leftMax - leftHeight;
                left++;
            } else {
                water += rightMax - rightHeight;
                right--;
            }
        }

        return water;
    }

    @QuestionInfo(
            name = "无重复字符的最长子串",
            type = QuestionType.SlidingWindow,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        // z x c >> [ a s d] >> ...
        for (int index = 0; index < s.length(); index++) {
            char currentChar = s.charAt(index);

            // 如果遇到窗口内已有的字符(pos >= winStart 说明该字符在窗口内)
            // 移动窗口起始位置, 也就是左边界(这将收缩窗口, 抛弃旧的字符)
            // [a, b, c] a --> a [b c] a
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= windowStart) {
                windowStart = charIndexMap.get(currentChar) + 1;
            } else { // 出现的是新字符, 更新最长子串长度
                int _maxLength = index - windowStart + 1;
                maxLength = Math.max(maxLength, _maxLength);
            }

            // 记录当前字符的最新索引
            charIndexMap.put(currentChar, index);
        }

        return maxLength;
    }
}
