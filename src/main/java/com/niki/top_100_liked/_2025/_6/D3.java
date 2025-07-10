package com.niki.top_100_liked._2025._6;

import com.niki.top_100_liked.beans.TreeNode;
import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

import java.util.HashMap;
import java.util.Map;

public class D3 {

    @QuestionInfo(
            name = "路径总和 III",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/path-sum-iii/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int pathSum(TreeNode root, int targetSum) {
        // 哈希表记录前缀和及其出现次数
        Map<Long, Integer> prefixSum = new HashMap<>();

        // 初始化: 前缀和为0的路径有1条(空路径)
        prefixSum.put(0L, 1);
        return dfs(root, 0, targetSum, prefixSum);
    }

    // depth-first search
    private int dfs(TreeNode node, long currSum, int targetSum, Map<Long, Integer> prefixSum) {
        if (node == null) return 0;

        // 当前路径的前缀和
        currSum += node.val;

        // 查找是否存在前缀和为 currSum - targetSum 的路径
        // 如果存在一个前缀和 prevSum，使得 currSum - prevSum = targetSum，那么从 prevSum 到 currSum 的子路径和就是 targetSum。
        // 因此，我们查找 currSum - targetSum 是否存在于哈希表中，表示之前是否出现过某个前缀和 prevSum。
        // 大概就是：比方说 10 + 5 + 3 = 18，count = 10，查 map 发现 map[10] = 1，说明 [10, 5, 3] 中间就有一条路径满足 8，可以正确找到
        // 同样地：如果第一个就是 8，count = 0，而 map[0] 初始化行为 1，也能在 [8] 中找到这条路经
        int count = prefixSum.getOrDefault(currSum - targetSum, 0);

        // 更新当前前缀和的计数
        prefixSum.put(currSum, prefixSum.getOrDefault(currSum, 0) + 1);

        // 递归处理左右子树
        // 这个递归的每一次递归都能保证前面的数据都在 map 中记录好了
        count += dfs(node.left, currSum, targetSum, prefixSum);
        count += dfs(node.right, currSum, targetSum, prefixSum);

        // 回溯: 移除当前前缀和, 防止影响其他路径
        // 在下一个分支发现新路径的时候，如果没有移除旧的前缀和，就会重复添加，因为该【未被移除的前缀和】已经被计入结果
        prefixSum.put(currSum, prefixSum.get(currSum) - 1);

        return count;
    }
}
