package com.niki914.top_100_liked._2025._10;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

public class D28 {

    @QuestionInfo(
            name = "颜色分类",
            type = QuestionType.Skill,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/sort-colors/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public void sortColors(int[] nums) {
        // 更高级的解法参照荷兰国旗算法(三指针)

        int red = 0, white = 0, blue = 0; // 计数

        for (int num : nums) {
            if (num == 0) {
                red++;
            } else if (num == 1) {
                white++;
            } else if (num == 2) {
                blue++;
            }
        }

        int index = 0; // 重填充索引

        // 重填充各颜色
        for (int i = index; i < red; i++) {
            nums[i] = 0;
        }

        index += red;

        for (int i = index; i < index + white; i++) {
            nums[i] = 1;
        }

        index += white;

        for (int i = index; i < index + blue; i++) {
            nums[i] = 2;
        }
    }

    @QuestionInfo(
            name = "下一个排列",
            type = QuestionType.Skill,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/next-permutation/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public void nextPermutation(int[] nums) {
        // 字典序: [1,2,3] < [1,3,2] < [2,1,3] < [2,3,1] < [3,1,2] < [3,2,1]
        // 用直觉来找很容易, 但是要找到变成用的规律有点复杂, 记住就行了

        int n = nums.length;

        /*
            [1, 3, 2]

            i = 1 X
            i = 0.

            i = 0, j = 2.

            swap nums[0 & 2] --> [2, 3, 1]

            reverse nums[1 .. 2] --> [2, 1, 3]
         */

        // 步骤1：从右往左找第一个 nums[i] < nums[i+1]
        int i = n - 2;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }

        if (i >= 0) {
            // 步骤2：从右往左找第一个 nums[j] > nums[i]
            int j = n - 1;
            for (; j > i; j--) {
                if (nums[j] > nums[i]) {
                    break;
                }
            }
            // 步骤3：交换
            swap(nums, i, j);
        }

        // 步骤4：反转 i+1 到末尾
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }
}