package com.niki914.top_100_liked._2025._4;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

public class D29 {
    @QuestionInfo(
            name = "最小覆盖子串",
            type = QuestionType.Substring,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/minimum-window-substring/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 3
    )
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // t 中每个字符的出现次数和字符种类
        // 后续用于比对
        int[] tCount = new int[128]; // ASCII 字符集大小 128
        int required = 0; // 出现的字符种数

        // 统计
        for (char c : t.toCharArray()) {
            if (tCount[c] == 0) {
                required++; // 遇到新字符, required 增 1
            }
            tCount[c]++;
        }

        // 滑动窗口：记录窗口内字符计数
        int[] windowCount = new int[128];
        int formed = 0; // 窗口中满足 t 要求的字符数
        int left = 0, right = 0; // 窗口左右指针

        int minLen = Integer.MAX_VALUE; // 最小窗口长度, 用于计算最终的最小子串, 从一个很大的数开始
        int minLeft = 0; // 最小窗口的左边界

        // 扩展右指针, 直到遍历完 s
        while (right < s.length()) {
            // 将右指针字符加入窗口
            char c = s.charAt(right);
            windowCount[c]++;

            // 如果当前字符在 t 中, 且窗口计数达到 t 的要求
            if (tCount[c] > 0 && windowCount[c] == tCount[c]) {
                formed++;
            }

            // 收缩左指针, 尝试最小化窗口
            while (left <= right && formed == required) {
                // 更新最小窗口
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                // 移除左指针字符
                char leftChar = s.charAt(left);
                windowCount[leftChar]--;
                // 如果移除的字符是 t 要求的, 且窗口计数不足
                if (tCount[leftChar] > 0 && windowCount[leftChar] < tCount[leftChar]) {
                    formed--;
                }
                left++;
            }

            right++;
        }

        // 如果没有找到有效窗口, 返回空字符串
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}
