package com.niki914.top_100_liked._2025._7;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;
import com.niki914.top_100_liked.util.annotation.SuspendQuestion;

import java.util.Stack;

@Link(
        last = D22.class,
        next = D30.class
)
public class D29 implements Day {

    @QuestionInfo(
            name = "寻找两个正序数组的中位数",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/median-of-two-sorted-arrays/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 6
    )
    @SuspendQuestion(
            name = "寻找两个正序数组的中位数",
            reason = "题目看起来不难，但是题解看不懂"
    )
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 确保nums1是较短数组，减少二分查找的范围
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;

        int n = nums2.length;
        int left = 0, right = m;

        while (left <= right) {
            // 二分查找nums1的分割点
            int partitionX = (left + right) / 2;
            // nums2的分割点由总长度的一半决定
            int partitionY = (m + n + 1) / 2 - partitionX;

            // 获取左右两边的值，处理边界情况
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            // 判断是否找到正确的分割
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // 如果总长度为奇数，中位数是左边最大值
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftX, maxLeftY);
                }
                // 如果总长度为偶数，中位数是左右平均值
                return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
            } else if (maxLeftX > minRightY) {
                // nums1左边太大，缩小nums1分割点
                right = partitionX - 1;
            } else {
                // nums1左边太小，增大nums1分割点
                left = partitionX + 1;
            }
        }

        // 如果输入数组不合法，抛出异常
        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }

    @QuestionInfo(
            name = "有效的括号",
            type = QuestionType.Stack,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/valid-parentheses/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 1
    )
    public boolean isValid(String s) {
        // 使用栈存储左括号
        Stack<Character> stack = new Stack<>();

        // 遍历字符串的每个字符
        for (char c : s.toCharArray()) {
            // 遇到左括号，压入栈
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else { // 遇到右括号，检查栈顶是否匹配
                // 栈为空但遇到右括号，直接返回false
                if (stack.isEmpty()) {
                    return false;
                }

                // 弹出栈顶的左括号
                char top = stack.pop();

                // 检查是否匹配 (没有优先级)
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        // 栈为空表示所有括号匹配
        return stack.isEmpty();
    }
}