package com.niki.top_100_liked._2025._7;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

public class D22 {

    @QuestionInfo(
            name = "搜索旋转排序数组",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/search-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判断mid所在的部分是否有序
            // 感觉有点只可意会不可言传 '为什么'
            // 1. 检查左 ~ 中的区间是否完全有序，如果是，中指针指向一个较大数字，否则指向一个排在最大数字后面的数字
            // 2. 根据 1 的情况（总是选取一个有序的部分来执行查找）：
            //     a. 左半有序：检查并移动指针
            //     b. 右半有序：检查并移动指针
            if (nums[left] <= nums[mid]) { // 左半部分有序
                // 检查target是否在左半部分的有序区间
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // 在左半部分搜索
                } else {
                    left = mid + 1; // 在右半部分搜索
                }
            } else { // 右半部分有序
                // 检查target是否在右半部分的有序区间
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // 在右半部分搜索
                } else {
                    right = mid - 1; // 在左半部分搜索
                }
            }
        }

        return -1; // 未找到
    }

    @QuestionInfo(
            name = "寻找旋转排序数组中的最小值",
            type = QuestionType.Array,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        // 总是收缩到极限，也就是左右重合的情况
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果 mid 元素大于右端元素，说明最小值在右半部分
            if (nums[mid] > nums[right]) {
                left = mid + 1; // 在最后的夹逼过程中这个操作会直接让 left == right 导致退出循环
            } else {
                // 否则，最小值在左半部分或 mid 本身
                right = mid;
            }
        }

        return nums[left];
    }
}
