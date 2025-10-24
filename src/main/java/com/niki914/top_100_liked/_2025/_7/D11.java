package com.niki914.top_100_liked._2025._7;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class D11 {

    @QuestionInfo(
            name = "课程表",
            type = QuestionType.Graph,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/course-schedule/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建邻接表和入度数组
        List<Integer>[] graph = new List[numCourses]; // 一个 list 数组
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // 填充图和入度
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];
            graph[prerequisite].add(course); // prerequisite -> course
            inDegree[course]++;
        }

        // 用队列存储入度为 0 的课程
        // 这个队列维护每一轮抹除的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 记录已学习的课程数
        int count = 0;

        /*
          完整的拓扑排序操作
          当队列清空时步出循环，此时可能是学完全部课程，也可能是成环了
          https://www.bilibili.com/video/BV1XV411X7T7/?vd_source=d1bc8ca6314de4d362247f172f7d13ca
         */
        while (!queue.isEmpty()) {
            int curr = queue.poll(); // 出队
            count++; // 拓扑排序 -- 移除入度为零的节点，然后计入已学习的课程

            // 遍历当前课程的后续课程，减少入度
            // 具体来说，curr 是一个入度为零的课程，可以直接学
            // 而学了之后我们就能学所有依赖它的课程了
            // 因而我们循环处理【记录了依赖了它的课程】来减少入度
            for (int next : graph[curr]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next); // 更新入度为零的课程
                }
            }
        }

        // 如果所有课程都学习完，返回true
        return count == numCourses;
    }

    @QuestionInfo(
            name = "实现前缀树",
            type = QuestionType.Tree,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/implement-trie-prefix-tree/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    static class Trie {
        // 内部节点类，表示Trie中的每个节点
        // 用分支是否从在来表示有没有这个字母
        // 用常规的 char - 'a' 来计算基于 a 对应 0 的索引
        private class TrieNode {
            public TrieNode[] children; // 子节点数组，存储26个小写字母
            public boolean isEnd;      // 标记是否为单词的结尾

            public TrieNode() {
                children = new TrieNode[26]; // 假设只处理小写字母
                isEnd = false;
            }
        }

        private final TrieNode root; // 根节点

        // 初始化前缀树
        public Trie() {
            root = new TrieNode();
        }

        // 插入字符串
        public void insert(String word) {
            TrieNode node = root;
            // 总体来说就是用字母计算出相应索引，然后一直循环进入子节点，如果有新的字母就新增分支
            for (char c : word.toCharArray()) {
                int index = c - 'a'; // 计算字符相对'a'的索引
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode(); // 创建新节点
                }
                node = node.children[index]; // 移动到子节点
            }
            node.isEnd = true; // 标记单词结尾
        }

        // 查找字符串
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd; // 存在且是单词结尾
        }

        // 查找前缀
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null; // 仅需确认前缀存在
        }


        // 辅助方法：查找前缀对应的节点
        private TrieNode searchPrefix(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    return null; // 前缀不存在
                }
                node = node.children[index];
            }
            return node; // 返回前缀的最后一个节点，比如匹配 apple，node 最后停留在 e，但是仅仅保证这一段是存在的而不保证有这个单词（被标记为 end）
        }
    }

    @QuestionInfo(
            name = "全排列",
            type = QuestionType.Backtracking,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/permutations/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, List<List<Integer>> result) {
        /*
         nums    所有可用的数字
         current 当前在处理的数组 - 其实永远都是那个对象，只不过一直在增删而已
         result list<list<int>>
         */
        // 终止条件：当前排列长度等于数组长度，加入结果集
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        /*
        [1, 2, 3, 4]

        1
        1 2
        1 2 3
        1 2 3 4
              <- 移除 4
            <- 移除 3
        1 2 4
        1 2 4 3
              <- 移除 3
            <- 移除 4
          <- 移除 2
        1 3
        1 3 2
        1 3 2 4
        ...
         */
        // 遍历数组，尝试每个未使用的数字
        for (int num : nums) {
            // 跳过已使用的数字
            if (current.contains(num)) {
                continue;
            }
            // 选择当前数字
            current.add(num);
            // 递归生成后续排列
            backtrack(nums, current, result);
            // 回溯：撤销选择
            current.remove(current.size() - 1);
        }
    }

    @QuestionInfo(
            name = "子集",
            type = QuestionType.Backtracking,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/subsets/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(nums, 0, path, result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        // 每到达一个节点，当前path就是一个合法子集，直接加入结果
        // 哎，这个唯手熟尔，跟递归一样，用的多就行
        result.add(new ArrayList<>(path));

        // 从start开始遍历，保证子集元素顺序一致
        for (int i = start; i < nums.length; i++) {
            // 选择当前元素
            path.add(nums[i]);
            // 递归处理后续元素
            backtrack(nums, i + 1, path, result);
            // 回溯：移除当前元素，尝试不选的情况
            path.remove(path.size() - 1);
        }
    }
}
