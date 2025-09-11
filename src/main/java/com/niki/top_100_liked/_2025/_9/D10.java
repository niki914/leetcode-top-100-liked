package com.niki.top_100_liked._2025._9;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;
import com.niki.top_100_liked.util.annotation.SuspendQuestion;

import java.util.Stack;

public class D10 {
    @QuestionInfo(
            name = "每日温度",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/daily-temperatures/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n]; // 结果数组，存储每个温度对应的天数，全部初始化为零
        Stack<Integer> stack = new Stack<>(); // 单调栈，存储索引

        /*
            temperatures = [73,74,75,71,69,72,76,73]
            answer = [1,1,4,2,1,1,0,0]
         */
        for (int i = 0; i < n; i++) {
            // 当当前温度高于栈顶索引对应的温度时，处理栈顶
            // while: 想象温度 [74, 73, 72, 80] 的情况就明白了
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop(); // 弹出栈顶索引
                answer[prevIndex] = i - prevIndex; // 计算天数差
            }
            stack.push(i); // 当前索引入栈
        }

        // 栈中剩余的索引对应的温度没有更高的后续温度，结果为 0（默认值）
        return answer;
    }

    @QuestionInfo(
            name = "柱状图中最大的矩形",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/largest-rectangle-in-histogram/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    @SuspendQuestion(
            name = "柱状图中最大的矩形",
            reason = "好难。。。看不懂"
    )
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // 创建新数组，两端添加高度 0，简化边界处理
        int[] newHeights = new int[n + 2];
        newHeights[0] = 0;
        newHeights[n + 1] = 0;
        System.arraycopy(heights, 0, newHeights, 1, n);

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>(); // 单调递增栈，存储索引

        for (int i = 0; i < newHeights.length; i++) {
            // 当当前柱子高度小于栈顶柱子高度时，计算栈顶柱子的面积
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int height = newHeights[stack.pop()]; // 弹出栈顶柱子高度
                int width = i - stack.peek() - 1; // 宽度 = 右边界(i) - 左边界(栈顶前一个) - 1
                maxArea = Math.max(maxArea, height * width); // 更新最大面积
            }
            stack.push(i); // 当前索引入栈
        }

        return maxArea;
    }
}
