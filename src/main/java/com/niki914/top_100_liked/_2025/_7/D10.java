package com.niki914.top_100_liked._2025._7;

import com.niki914.top_100_liked.beans.TreeNode;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.LinkedList;
import java.util.Queue;

public class D10 {

    @QuestionInfo(
            name = "二叉树的最近公共祖先",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 最近公共祖先的概念简直见名知意，不过这个祖先也可能是自己

        // 这个题手画一次能看出来是可以找到的
        // 基本情况：如果当前节点为空或等于p或q，直接返回当前节点
        if (root == null) { // 这样写好理解一点。此时一般是到了叶子的下一次递归，叶子没有子叶也就自然返回 null 了，然后叶子的 left 或者 right 参数就得到了 null
            return null;
        }

        if (root == p || root == q) { // 这里是找到了 p 或 q 的节点，直接返回
            return root;
        }

        // 递归查找左右子树
        // 情况1：左右均不为空，也就是说他们都在第二个判断里面返回了，也就是 p、q 分别在左右子树里面找到了，这时 root 就是要找的最近公共祖先
        // 情况2：左或右节点为空，说明他们走到了路径尽头，没找到，说明这个 root 并不全部包含，他只是一个不重要的中间点，这时候要做的就是返回我们找到的那个 q 或 p 到上一级
        // 用语言不够直观，画图的话，如果你一眼就看出来真正的公共祖先，你就懂我为什么那么说
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 情况1
        if (left != null && right != null) {
            return root;
        }

        // 情况2
        // 也可以说，左右节点其实是有且只有一个为空，因为是二叉树，这里返回那个非空的
        return left != null ? left : right;
    }

    @QuestionInfo(
            name = "二叉树中的最大路径和",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public static class MaxPathSum {
        private int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxGain(root);
            return maxSum;
        }

        // 返回以 node 为起点的最大单边路径和，同时更新全局最大路径和
        // 做的事情有：
        // 1. 提前递归计算子树的路径和
        // 2. 计算当前节点 + 左右子树构建的最大路径和并尝试更新 maxSum
        // 3. 给上层返回一条最大单边路径和
        // 4. 在递归完成后，当前的 maxSum 已经保存了最好的情况
        private int maxGain(TreeNode node) {
            if (node == null) return 0;

            // 递归计算左右子树的最大单边路径和，只考虑正贡献
            int leftGain = Math.max(maxGain(node.left), 0);
            int rightGain = Math.max(maxGain(node.right), 0);

            // 当前节点作为路径顶点的路径和：左子树 + 节点值 + 右子树
            int currentPathSum = node.val + leftGain + rightGain;

            // 更新全局最大路径和
            maxSum = Math.max(maxSum, currentPathSum);

            // 返回以当前节点为起点的最大单边路径和（只能选左或右）
            // 如果只有一个子树并且是和是负数，算法会选择使用 null 子树的和，也就是 0
            // 如果两个子树和都是负数，上一层也会抛弃而选择 0
            return node.val + Math.max(leftGain, rightGain);
        }
    }

    char LAND = '1';
    char WATER = '0';

    @QuestionInfo(
            name = "岛屿数量",
            type = QuestionType.Graph,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/number-of-islands/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;

        // 遍历网格
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == LAND) { // 发现未访问的陆地
                    islands++; // 岛屿数量加1
                    dfs(grid, row, col); // 通过DFS标记（抹除）所有相连的陆地
                }
            }
        }

        return islands;
    }

    // DFS：将与当前陆地相连的所有陆地标记为 WATER
    private void dfs(char[][] grid, int row, int col) {
        // 边界检查
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return;
        }

        // 遇到水/已访问的陆地，直接返回
        if (grid[row][col] != LAND) {
            return;
        }

        // 标记当前陆地为已访问
        grid[row][col] = WATER;

        // 递归探索四个方向（上、下、左、右）
        // 因为一个地图是很复杂的，不要想当然以为上面和左边的已经被全部标记
        dfs(grid, row - 1, col); // 上
        dfs(grid, row + 1, col); // 下
        dfs(grid, row, col - 1); // 左
        dfs(grid, row, col + 1); // 右
    }

    @QuestionInfo(
            name = "腐烂的橘子",
            type = QuestionType.Graph,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/rotting-oranges/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int orangesRotting(int[][] grid) {
        // 常量定义，提高可读性
        final int EMPTY = 0;
        final int FRESH = 1;
        final int ROTTEN = 2;

        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int freshCount = 0; // 新鲜橘子计数

        // 用一个队列来模拟时间推进
        Queue<int[]> rottenQueue = new LinkedList<>(); // 存储腐烂橘子的坐标

        // 1. 遍历网格，统计新鲜橘子和初始腐烂橘子的位置
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == FRESH) {
                    freshCount++; // 统计新鲜橘子
                } else if (grid[row][col] == ROTTEN) {
                    rottenQueue.offer(new int[]{row, col}); // 腐烂橘子入队
                }
            }
        }

        // 如果没有新鲜橘子，直接返回0
        if (freshCount == 0) return 0;

        // 2. BFS（breadth first search）模拟腐烂扩散
        int minutes = 0; // 记录经过的分钟数
        // 四个方向：上、右、下、左
        // 作用见下
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!rottenQueue.isEmpty()) {
            int size = rottenQueue.size(); // 当前层的腐烂橘子数量
            boolean hasNewRotten = false; // 是否有新的橘子腐烂

            // 处理当前层的所有腐烂橘子
            for (int i = 0; i < size; i++) {
                // 这里出队的意思大概是：
                // 处理完当前腐烂橘子的扩散后，新腐烂的橘子接替旧的橘子继续起扩散作用，因此旧的可以出队
                // 即使四周没有新鲜橘子，当前腐烂橘子也已完成任务，因为以后它也不能再扩散了，也可以出队
                int[] current = rottenQueue.poll(); // 出队
                int row = current[0];
                int col = current[1];

                // 检查四个方向的相邻格子
                // 其实相当于遍历 a[i-1][j], a[i+1][j], a[i][j-1], a[i][j+1]
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (
                            (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) && // 边界检查
                                    (grid[newRow][newCol] == FRESH) // 新鲜橘子检查
                    ) {
                        grid[newRow][newCol] = ROTTEN; // 标记为腐烂
                        rottenQueue.offer(new int[]{newRow, newCol}); // 新腐烂橘子入队
                        freshCount--; // 新鲜橘子减少
                        hasNewRotten = true; // 标记本轮有新腐烂
                    }
                }
            }

            // 如果本轮有新腐烂的橘子，分钟数加 1
            // 这里的一轮是，场上所有的腐烂橘子（不计扩散过程中新增的那些）全部扩散一次
            if (hasNewRotten) {
                minutes++;
            }
        }

        // 3. 检查是否还有新鲜橘子，如果有，返回 -1 因为腐烂橘子全部消费完了，因此剩余的新鲜橘子不会再被扩散
        return freshCount == 0 ? minutes : -1;
    }
}
