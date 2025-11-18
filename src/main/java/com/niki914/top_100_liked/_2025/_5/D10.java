package com.niki914.top_100_liked._2025._5;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.List;

@Link(
        last = D9.class,
        next = D11.class
)
public class D10 implements Day {

    @QuestionInfo(
            name = "螺旋矩阵",
            type = QuestionType.Matrix,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/spiral-matrix/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 2
    )
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length;

        // 边界: 在从左到右、从上到下、从右到左、从下到上添加的时候用于对遍历的范围进行约束
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {
            // 向右遍历
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            // 向下遍历
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            if (top <= bottom) {
                // 向左遍历
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            if (left <= right) {
                // 向上遍历
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }

        return result;
    }

    @QuestionInfo(
            name = "旋转图像",
            type = QuestionType.Matrix,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/rotate-image/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 3
    )
    public void rotate(int[][] matrix) {
        // 拿张纸试一下就知道了, 按算法两步后刚好旋转了 90 度
        // 方阵
        int length = matrix.length;

        // 1. 沿主对角线翻转 (左上到右下)
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                // 遍历 i, j 然后调转相应的元素
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2. 每行左右翻转
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length / 2; j++) {
                int oppJ = length - 1 - j;
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][oppJ];
                matrix[i][oppJ] = temp;
            }
        }
    }
}
