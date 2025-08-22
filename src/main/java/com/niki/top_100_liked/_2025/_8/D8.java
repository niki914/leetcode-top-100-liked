package com.niki.top_100_liked._2025._8;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

import java.util.Stack;

public class D8 {

    @QuestionInfo(
            name = "字符串解码",
            type = QuestionType.Stack,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/decode-string/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    // TODO 真鸡巴窒息
    public String decodeString(String s) {
        /*
        3[a2[c]] -> accaccacc"
         */
        Stack<Integer> numStack = new Stack<>(); // 存储重复次数
        Stack<StringBuilder> strStack = new Stack<>(); // 存储中间字符串
        StringBuilder currentStr = new StringBuilder(); // 当前处理的字符串
        int num = 0; // 当前累积的数字

        for (char c : s.toCharArray()) {
            // 可能的情况: 数字、[、]、字母
            if (Character.isDigit(c)) {
                // 累积多位数字，例如 "123[..." = 1*100 + 2*10 + 3
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                // 遇到 '['，完成数字的构建，将当前数字和字符串入栈，清空当前状态
                numStack.push(num);
                strStack.push(currentStr);
                num = 0;
                currentStr = new StringBuilder();
            } else if (c == ']') {
                // 遇到 ']'，弹出数字和字符串，重复当前字符串并拼接
                int repeat = numStack.pop();
                StringBuilder prevStr = strStack.pop();
                StringBuilder repeated = new StringBuilder();
                for (int i = 0; i < repeat; i++) {
                    repeated.append(currentStr);
                }
                currentStr = prevStr.append(repeated);
            } else {
                // 遇到字母，直接追加到当前字符串
                currentStr.append(c);
            }
        }

        return currentStr.toString();
    }
}
