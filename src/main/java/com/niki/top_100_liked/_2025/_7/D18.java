package com.niki.top_100_liked._2025._7;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D18 {

    @QuestionInfo(
            name = "分割回文串",
            type = QuestionType.Backtracking,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/palindrome-partitioning/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.isEmpty()) return result;

        List<String> current = new ArrayList<>();
        backtrack(s, 0, current, result);
        return result;
    }

    // 回溯：从索引 start 开始分割字符串 s
    private void backtrack(String s, int start, List<String> current, List<List<String>> result) {
        // 终止条件：分割到字符串末尾，记录当前方案
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        /*
            "door"
            d, o, o, r -> [d, o, o, r]
            d, o, o
            d, o
            d, oo, r -> [d, oo, r]
            d, oo
            d
         */

        // 尝试从 start 开始的所有可能回文子串
        for (int i = start + 1; i <= s.length(); i++) {
            String substring = s.substring(start, i);
            // 如果当前子串是回文串
            if (isPalindrome(s, start, i - 1)) {
                // 选择：加入当前回文子串
                current.add(substring);
                // 递归：处理剩余部分
                backtrack(s, i, current, result);
                // 回溯：移除当前子串，尝试其他分割
                current.remove(current.size() - 1);
            }
        }
    }

    // 判断 s[ start : end ] 是否为回文串
    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
