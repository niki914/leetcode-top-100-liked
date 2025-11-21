package com.niki914.top_100_liked._2025._8;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked._2025._7.D30;
import com.niki914.top_100_liked._2025._9.D10;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;
import com.niki914.top_100_liked.util.annotation.SuspendQuestion;

import java.util.Stack;

@Link(
        last = D30.class,
        next = D10.class
)
public class D8 implements Day {

    @QuestionInfo(
            name = "字符串解码",
            type = QuestionType.Stack,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/decode-string/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 3
    )
    @SuspendQuestion()
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
                /*
                    在遇到第一个 ']' 开始，以 '3[a2[c]]' 为例，状态为：
                    num = 0, currStr = "c", numStack = [3, 2], strStack = ["", "a"]
                    ---
                    repeat = 2
                    prevStr = "a"
                    repeated = "c" * 2

                    currStr = "a" + "c" * 2
                    ---
                    repeat = 3
                    prevStr = ""
                    repeated = "acc" * 3

                    currStr = "" + "acc" * 3
                    ---
                    得到: "accaccacc"
                */

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
