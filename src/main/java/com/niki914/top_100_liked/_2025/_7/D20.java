package com.niki914.top_100_liked._2025._7;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D20 {

    @QuestionInfo(
            name = "N 皇后",
            type = QuestionType.Backtracking,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/n-queens/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        // 初始化棋盘，所有位置填充为'.'
        for (int row = 0; row < n; row++) {
            Arrays.fill(board[row], '.');
        }
        backtrack(solutions, board, 0, n);
        return solutions;
    }

    // 回溯函数：row表示当前处理的行，n是棋盘大小
    private void backtrack(List<List<String>> solutions, char[][] board, int row, int n) {
        // 基线条件：如果所有行都放置了皇后，记录当前解法
        if (row == n) {
            solutions.add(construct(board));
            return;
        }

        /*
            回溯的典型结构：
            1. 前置判断
            2. 遍历
                a. 判断新分支的合法性
                b. 建立新分支
                c. 递归
                d. 恢复上一级分支并回溯
            3. 返回结果
         */

        // 遍历当前行的每一列，尝试放置皇后
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) { // 如果合法，在这个点放置皇后，然后开始回溯
                // 放置皇后
                board[row][col] = 'Q';
                // 递归处理下一行
                backtrack(solutions, board, row + 1, n);
                // 回溯：撤销当前放置
                board[row][col] = '.';
            }
        }
    }

    // 检查在 (row, col) 放置皇后是否合法
    private boolean isValid(char[][] board, int row, int col, int n) {
        // 检查列：同一列的上方是否有皇后
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 检查左上对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 检查右上对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    // 将棋盘转换为字符串列表格式
    // fun Array<Array<Char>>.toList(): List<String>
    private List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }
}
