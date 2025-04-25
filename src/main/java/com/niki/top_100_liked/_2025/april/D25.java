package com.niki.top_100_liked._2025.april;

import com.niki.top_100_liked.util.QType;

import java.util.HashMap;
import java.util.Map;

public class D25 {
    @QType(
            name = "接雨水",
            type = "双指针",
            difficulty = "困难",
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

    @QType(
            name = "无重复字符的最长子串",
            type = "滑动窗口",
            difficulty = "中等",
            link = "https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int index = 0; index < s.length(); index++) {
            char currentChar = s.charAt(index);

            // 如果字符重复且在当前窗口内，移动窗口起始位置
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= windowStart) {
                windowStart = charIndexMap.get(currentChar) + 1;
            } else {
                // 更新最长子串长度
                maxLength = Math.max(maxLength, index - windowStart + 1);
            }

            // 记录当前字符的最新索引
            charIndexMap.put(currentChar, index);
        }

        return maxLength;
    }
}
