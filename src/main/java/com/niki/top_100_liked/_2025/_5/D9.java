package com.niki.top_100_liked._2025._5;

import com.niki.top_100_liked.util.annotation.Diff;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.Type;

public class D9 {

    @QuestionInfo(
            name = "除自身以外数组的乘积",
            type = Type.Array,
            difficulty = Diff.MEDIUM,
            link = "https://leetcode.cn/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // 批量而不是逐个构建结果数组

        // 第一步: 计算每个元素左侧的乘积
        // >>>>>>
        answer[0] = 1; // 处理首元素
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // 第二步: 结合右侧乘积更新结果
        // <<<<<<
        int rightProduct = 1; // 右侧乘积初始化为 1
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= rightProduct; // 乘以右侧乘积
            rightProduct *= nums[i];   // 更新右侧乘积
        }

        return answer;
    }

    @QuestionInfo(
            name = "缺失的第一个正数",
            type = Type.Array,
            difficulty = Diff.HARD,
            link = "https://leetcode.cn/problems/first-missing-positive/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 第一步: 将所有非正数和大于 n 的数替换为 1
        // 在本题的背景下最好的情况也是 [1,2,3,4,...,n], 所有我们永远都不需要大于 n 的数
        boolean containsOne = false;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                containsOne = true;
            } else if (num <= 0 || num > n) {
                nums[i] = 1;
            }
        }

        // 如果没有 1, 直接返回 1
        if (!containsOne) {
            return 1;
        }

        // 第一步之后, 数组可能看起来像是: [1,1,1,3,6,4,1,5]
        // 第二步: 用数组本身标记存在数字
        for (int i = 0; i < n; i++) {
            // 不能假设 nums[i] 为正数因为可能已经被取反
            int index = Math.abs(nums[i]) - 1; // 将值 x 映射到索引 x-1
            if (nums[index] > 0) { // 避免重复标记
                nums[index] = -nums[index]; // 标记索引 index 处的值为负, 表示数字 index+1 存在
            }
        }

        // 第二部之后, 数组元素的值的正负表示索引所表示的数是否存在
        // 比如: i = 3, nums[3] < 0 说明数字`4`被标记了, 是出现了的
        // 第三步: 找到第一个正数值的索引
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1; // 索引 i 处为正, 说明 i+1 缺失
            }
        }

        // 如果在第三步后没有返回, 说明 1~n 的所有数都被标记, 直接返回 n+1
        return n + 1;
    }

    @QuestionInfo(
            name = "矩阵置零",
            type = Type.Matrix,
            difficulty = Diff.MEDIUM,
            link = "https://leetcode.cn/problems/set-matrix-zeroes/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public void setZeroes(int[][] matrix) {
        // 看起来有点抽象, 画个图好理解一点
        int rowLen = matrix.length, colLen = matrix[0].length;

        // 用于标记的位置最终一定会被置零, 所以可以随便动
        // 为了根据原始情况判断第 1 行/列是否置零，需要记录他们的原始 0 状态
        boolean shouldFirstRowPutZero = false, shouldfirstColPutZero = false;

        // STEP1: 检查第 1 行/列是否有零
        // 检查第一行是否有 0
        for (int col = 0; col < colLen; col++) {
            if (matrix[0][col] == 0) {
                shouldFirstRowPutZero = true;
                break;
            }
        }

        // 检查第一列是否有 0
        for (int row = 0; row < rowLen; row++) {
            if (matrix[row][0] == 0) {
                shouldfirstColPutZero = true;
                break;
            }
        }

        // STEP2: 将元素的 0 情况映射到第一行/列
        // 比如 matrix[2][4] == 0 说明第三行、第五列需要全部置零
        // 此时我们分别标记到 [2][0]、[0][4] 上
        // 用第一行和第一列记录其他行列是否需要置 0
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        // STEP3: 根据上一步标记的结果对各个行列进行置零
        // 根据第一列置 0
        for (int row = 1; row < rowLen; row++) {
            if (matrix[row][0] == 0) {
                for (int col = 1; col < colLen; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

        // 根据第一行置 0
        for (int col = 1; col < colLen; col++) {
            if (matrix[0][col] == 0) {
                for (int row = 1; row < rowLen; row++) {
                    matrix[row][col] = 0;
                }
            }
        }

        // STEP4: 根据一开始标记的结果来处理第 1 行/列是否置零
        // 处理第一行
        if (shouldFirstRowPutZero) {
            for (int col = 0; col < colLen; col++) {
                matrix[0][col] = 0;
            }
        }

        // 处理第一列
        if (shouldfirstColPutZero) {
            for (int row = 0; row < rowLen; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}
