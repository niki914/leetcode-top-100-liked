package com.niki.top_100_liked._2025._7;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

public class D21 {

    @QuestionInfo(
            name = "搜索插入位置",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/search-insert-position/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int searchInsert(int[] nums, int target) {
        // 典型双指针 - 二分查找
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免整数溢出，避免直接把两个指针相加
            if (nums[mid] == target) {
                return mid; // 找到目标值，直接返回索引
            } else if (nums[mid] < target) {
                left = mid + 1; // 目标值在右半部分
            } else {
                right = mid - 1; // 目标值在左半部分
            }
        }

        return left; // left 是插入位置
    }

    @QuestionInfo(
            name = "搜索二维矩阵",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/search-in-a-2d-matrix/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            // 相当于把二维数组展开为一维了
            // 中指针的意义就是行数 + 列偏移量
            // 据此可以计算出当前的行索引和列索引
            int mid = left + (right - left) / 2; // 避免整数溢出
            int row = mid / cols; // 映射到行
            int col = mid % cols; // 映射到列

            if (matrix[row][col] == target) {
                return true; // 找到目标值
            } else if (matrix[row][col] < target) {
                left = mid + 1; // 目标值在右半部分
            } else {
                right = mid - 1; // 目标值在左半部分
            }
        }

        return false; // 未找到目标值
    }

    @QuestionInfo(
            name = "在排序数组中查找元素的第一个和最后一个位置",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;

        // 查找左边界
        int left = 0;
        int right = nums.length - 1;

        // 背下来算了，不能理解，运行一遍确实可以把 left 停在左边界
        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免整数溢出
            if (nums[mid] >= target) {
                right = mid - 1; // 收缩右边界，寻找更左的位置
            } else {
                left = mid + 1; // 目标值在右半部分
            }
        }

        // 左边界为 left，检查是否有效
        if (left < nums.length && nums[left] == target) {
            result[0] = left;
        } else {
            return result; // 目标值不存在
        }

        // 查找右边界
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免整数溢出
            if (nums[mid] <= target) {
                left = mid + 1; // 收缩左边界，寻找更右的位置
            } else {
                right = mid - 1; // 目标值在左半部分
            }
        }

        // 右边界为 right（left - 1），已知左边界存在，right 必有效
        result[1] = right;

        return result;
    }
}
