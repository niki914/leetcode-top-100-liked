package com.niki914.top_100_liked._2025._5;

import com.niki914.top_100_liked.beans.TreeNode;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class D28 {

    @QuestionInfo(
            name = "二叉树的直径",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/diameter-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public static class DiameterOfBinaryTree {
        // 全局变量记录最大直径
        private int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            // 调用递归函数计算高度, 同时更新 maxDiameter
            maxDepth(root);
            return maxDiameter;
        }

        // 递归计算节点的高度, 并更新最大直径
        private int maxDepth(TreeNode node) {
            if (node == null) {
                return 0;
            }
            // 递归获取左右子树高度
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);
            // 更新最大直径: 左子树高度 + 右子树高度
            maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
            // 返回当前节点的**高度**, 比方说两个孩子为叶的节点, 他的高度就是 2
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    @QuestionInfo(
            name = "二叉树的层序遍历",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/binary-tree-level-order-traversal/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 使用队列进行层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        /*
        逐层处理并在结果添加该层的 val 数组
        1. 检查队列个数来判断上一层压入的子节点个数
        2. 根据个数进行若干次 val 值获取和子节点压入, 最后会把当前层的所有子节点压入
        3. 添加当前层 val 数组到结果, 继续循环
         */

        while (!queue.isEmpty()) {
            // 当前层的节点数
            int levelSize = queue.size();
            // 存储当前层的节点值
            List<Integer> currentLevel = new ArrayList<>();

            // 处理当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                currentLevel.add(node.val);
                // 将左右子节点加入队列
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // 将当前层结果加入总结果
            result.add(currentLevel);
        }
        return result;
    }

    @QuestionInfo(
            name = "将有序数组转换为二叉搜索树",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        // 调用递归函数, 处理整个数组
        return buildBST(nums, 0, nums.length - 1);
    }

    // 递归构造平衡 BST, left 和 right 为数组索引范围
    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 选择中间元素作为根节点
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 递归构造左子树(左半部分)和右子树(右半部分)
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);
        return root;
    }
}
