package com.niki914.top_100_liked._2025._7;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked._2025._8.D8;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.Stack;

@Link(
        last = D29.class,
        next = D8.class
)
public class D30 implements Day {

    @QuestionInfo(
            name = "最小栈",
            type = QuestionType.Stack,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/min-stack/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 2
    )
    static class MinStack {
        // 数据栈，存储所有元素
        private Stack<Integer> dataStack;
        // 最小栈（单调），存储当前栈的最小值 - 如果只用一个 Integer 存的话，POP 操作可能把它弹掉，这时候要重新扫描
        private Stack<Integer> minStack;

        // 初始化栈
        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        // 压入元素
        public void push(int val) {
            dataStack.push(val);
            // 如果最小栈为空 或 新元素是新的最小值，压入最小栈
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        // 弹出栈顶元素
        public void pop() {
            // 如果数据栈栈顶等于最小栈栈顶，同时弹出最小栈
            if (!dataStack.isEmpty() && dataStack.peek().equals(minStack.peek())) {
                minStack.pop(); // 除去这个元素之后的新元素就直接成为 peek
            }
            dataStack.pop();
        }

        // 获取栈顶元素
        public int top() {
            return dataStack.peek();
        }

        // 获取栈中最小元素
        public int getMin() {
            return minStack.peek();
        }
    }
}
