package com.niki.top_100_liked._2025._5;

import com.niki.top_100_liked.beans.TreeNode;
import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

import java.util.*;

public class D26 {
    @QuestionInfo(
            name = "LRU 缓存",
            type = QuestionType.LinkedList,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/lru-cache/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    static class LRUCache {
        /*
        要点:
        1. 用 hash map 维护所有双向节点
        2. 通过节点的 pre, next 指针维护访问顺序链表
        3. 移动、增删改查时注意所有有关节点的 pre, next 更新
        4. 注意容量超出时的处理
         */
        private final int capacity;
        private final HashMap<Integer, Node> cache;
        private final Node head, tail; // 伪头节点和伪尾节点, 便于操作

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            // 初始化双向链表, 头尾节点为空节点
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 将访问的节点移到链表头部(最近使用)
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node != null) {
                // 如果 key 存在, 更新值并移到头部
                node.value = value;
                moveToHead(node);
            } else {
                // 创建新节点
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addToHead(newNode); // 新节点添加到头部
                // 检查是否超出容量
                if (cache.size() > capacity) {
                    // 移除链表尾部节点(最久未使用)
                    Node last = removeTail();
                    cache.remove(last.key);
                }
            }
        }


        // 定义双向链表节点
        static class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        // 将节点移到链表头部
        private void moveToHead(Node node) {
            // 先从链表中移除
            removeNode(node);
            // 再添加到头部
            addToHead(node);
        }

        // 添加节点到链表头部
        private void addToHead(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        // 从链表中移除节点
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // 移除链表尾部节点(最久未使用)
        private Node removeTail() {
            Node last = tail.prev;
            removeNode(last);
            return last;
        }
    }

    @QuestionInfo(
            name = "二叉树的中序遍历",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/binary-tree-inorder-traversal/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<Integer> inorderTraversal(TreeNode root) {
        /*
        递归做法略

        比如在一个左叶子上, 我们弹出并访问完左叶子
        然后 while 继续访问右节点准备继续压入右枝的子叶, 但是它为空
        所以我们直接从栈里面拿下一个节点来重复这个访问操作
        要更深入理解的话, 自己画一个二叉树、栈和结果数组, 推演一下
         */

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        // 当curr不为空(有节点可处理)或栈不为空(有节点待访问)时继续
        while (curr != null || !stack.isEmpty()) {
            // 1. 一直向左走, 把所有左节点压入栈
            while (curr != null) {
                stack.push(curr);      // 当前节点入栈
                curr = curr.left;      // 移到左子节点
            }
            // 2. 左边走到底, 弹出栈顶节点
            curr = stack.pop();        // 取栈顶节点
            result.add(curr.val);      // 3. 访问当前节点(根)
            curr = curr.right;         // 4. 转向右子树
        }
        return result;
    }

    @QuestionInfo(
            name = "二叉树的最大深度",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int maxDepth(TreeNode root) {
        // 如果节点为空, 深度为0
        if (root == null) {
            return 0;
        }

        // 递归计算左子树深度
        int leftDepth = maxDepth(root.left);
        // 递归计算右子树深度
        int rightDepth = maxDepth(root.right);

        // 关键计算逻辑
        // 返回左右子树深度的最大值, 并加上当前节点, 也就是 1
        return Math.max(leftDepth, rightDepth) + 1;
    }

    @QuestionInfo(
            name = "翻转二叉树",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/invert-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public TreeNode invertTree(TreeNode root) {
        // 如果节点为空, 直接返回
        if (root == null) {
            return null;
        }

        // 交换当前节点的左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归翻转左子树
        invertTree(root.left);
        // 递归翻转右子树
        invertTree(root.right);
        // 返回当前节点
        return root;
    }

    @QuestionInfo(
            name = "对称二叉树",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/symmetric-tree/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public boolean isSymmetric(TreeNode root) {
        // 空树或单节点是对称的
        if (root == null) {
            return true;
        }
        // 检查左右子树是否镜像对称
        return isMirror(root.left, root.right);
    }

    // 辅助函数: 检查两棵子树是否镜像对称
    private boolean isMirror(TreeNode left, TreeNode right) {
        // 都为空, 对称
        if (left == null && right == null) {
            return true;
        }

        // 一边为空, 另一边不为空, 不对称
        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val)
            return false;

        /*
             a
            / \
           b   b
          /\   /\
         c  d d  c
         */
        return isMirror(left.left, right.right) && // 左子树的左子树 vs 右子树的右子树
                isMirror(left.right, right.left);   // 左子树的右子树 vs 右子树的左子树
    }

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