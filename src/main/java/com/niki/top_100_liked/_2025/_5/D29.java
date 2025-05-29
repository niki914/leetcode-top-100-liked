package com.niki.top_100_liked._2025._5;

import com.niki.top_100_liked.beans.TreeNode;
import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;
import com.niki.top_100_liked.util.annotation.SuspendQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D29 {
    @QuestionInfo(
            name = "验证二叉搜索树",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long min, long max) {
        // 空节点是有效的BST
        if (node == null) return true;

        // 检查当前节点值是否在有效范围内
        if (node.val <= min || node.val >= max) return false;

        // 递归检查左子树(上限为当前节点值)和右子树(下限为当前节点值)
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    @QuestionInfo(
            name = "二叉搜索树中第K小的元素",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public static class KthSmallest {
        private int count = 0; // 记录当前遍历的节点序号
        private int result = 0; // 存储第k小的元素值

        public int kthSmallest(TreeNode root, int k) {
            /*
            对二叉搜索树中进行中序遍历, 就是升序访问
            所以只要中序访问 k 个数就访问了第 k 小的
             */
            inorder(root, k);
            return result;
        }

        private void inorder(TreeNode node, int k) {
            if (node == null) {
                return;
            }
            // 中序遍历: 先左子树
            inorder(node.left, k);
            // 访问当前节点, 计数加1
            count++;
            // 如果当前计数等于k, 记录结果
            if (count == k) {
                result = node.val;
                return;
            }
            // 继续遍历右子树
            inorder(node.right, k);
        }
    }

    @QuestionInfo(
            name = "二叉树的右视图",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode node, int depth, List<Integer> result) {
        // 空节点直接返回
        if (node == null) return;

        // 如果当前层级等于结果列表大小, 说明是该层第一个访问的节点(右侧优先)
        if (depth == result.size())
            result.add(node.val);

        // 优先递归右子树, 确保右视图优先取右子树节点
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }

    @QuestionInfo(
            name = "二叉树展开为链表",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public void flatten(TreeNode root) {
        if (root == null) return;

        // 递归展平左子树和右子树
        flatten(root.left);
        flatten(root.right);

        // 保存右子树
        TreeNode rightSubtree = root.right;
        // 将左子树接到右指针, 左指针置空
        root.right = root.left;
        root.left = null;

        // 找到当前右链表(被左枝接入后的)的末尾
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        // 将原右子树接到末尾
        current.right = rightSubtree;
    }

    @QuestionInfo(
            name = "从前序与中序遍历序列构造二叉树",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    @SuspendQuestion(
            name = "从前序与中序遍历序列构造二叉树",
            reason = "难, 理解了两个数组相互印证、分块、推导出原树, 但不会写代码"
    )
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 存储中序遍历的元素到索引映射
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, 0, inorderMap);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, Map<Integer, Integer> inorderMap) {
        // 基线条件: 子树为空
        if (preStart > preEnd) {
            return null;
        }
        // 前序遍历第一个元素是根节点
        TreeNode root = new TreeNode(preorder[preStart]);
        // 在中序遍历中找到根节点位置
        int rootIndex = inorderMap.get(root.val);
        // 左子树的大小
        int leftSize = rootIndex - inStart;

        // 递归构造左子树和右子树
        root.left = buildTree(preorder, preStart + 1, preStart + leftSize, inStart, inorderMap);
        root.right = buildTree(preorder, preStart + leftSize + 1, preEnd, rootIndex + 1, inorderMap);

        return root;
    }
}
