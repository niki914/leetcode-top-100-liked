package com.niki.top_100_liked._2025._7;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

public class D16 {

    @QuestionInfo(
            name = "单词搜索",
            type = QuestionType.Backtracking,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/word-search/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public boolean exist(char[][] board, String word) {
        // 参数校验
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }

        int rows = board.length, cols = board[0].length;

        // 遍历网格每个位置作为起点，尝试寻找单词
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (dfs(board, word, 0, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    // DFS回溯，k表示当前匹配的word字符索引
    private boolean dfs(char[][] board, String word, int k, int row, int col) {
        char curr = word.charAt(k);

        // 越界或字符不匹配
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        if (board[row][col] != curr) {
            return false;
        }

        // 只要递归到最后一个字符并且匹配，就找到了
        if (k == word.length() - 1) {
            return true;
        }

        // 标记当前格子为已访问（仅由大小写英文字母组成，随便设置一个非字母字符即可），重要！！
        char temp = board[row][col];
        board[row][col] = '#';

        // 四个方向递归搜索
        boolean found = dfs(board, word, k + 1, row + 1, col) || // 下
                dfs(board, word, k + 1, row - 1, col) || // 上
                dfs(board, word, k + 1, row, col + 1) || // 右
                dfs(board, word, k + 1, row, col - 1); // 左

        // 恢复格子状态，这一次可能没通过这个点找到，不代表下一次不能，所以要恢复，让其他人试试。
        // 其实尤其重要的是要发觉回溯算法的回溯点！可以发现，每个回溯算法都有状态恢复，就像象棋推演一样
        board[row][col] = temp;

        return found; // 如果在周围四个方向找到，就可以继续递归，递归到判定找到单词或者找不到的时候就返回上层
    }
}
