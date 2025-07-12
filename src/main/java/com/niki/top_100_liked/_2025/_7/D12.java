package com.niki.top_100_liked._2025._7;

import com.niki.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class D12 {

    @QuestionInfo(
            name = "电话号码的字母组合",
            type = QuestionType.Backtracking,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<String> letterCombinations(String digits) {
        // 如果输入为空，返回空列表
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        // 定义数字到字母的映射
        String[] mapping = {
                "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        List<String> result = new ArrayList<>();
        backtrack(result, digits, 0, new StringBuilder(), mapping);
        return result;
    }

    // 回溯函数
    // result: 存储结果
    // digits: 输入的数字字符串
    // index: 当前处理的数字索引
    // current: 当前构建的组合
    // mapping: 数字到字母的映射
    private void backtrack(List<String> result, String digits, int index, StringBuilder current, String[] mapping) {
        // 如果当前组合长度等于输入长度，加入结果
        // 一样地，画出来自己走一遍就懂了
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 获取当前数字对应的字母
        String letters = mapping[digits.charAt(index) - '0'];
        // 遍历当前数字的每个字母
        for (char c : letters.toCharArray()) {
            // 添加当前字母
            current.append(c);
            // 递归处理下一个数字
            backtrack(result, digits, index + 1, current, mapping);
            // 回溯：移除最后一个字母
            current.deleteCharAt(current.length() - 1);
        }
    }

    @QuestionInfo(
            name = "组合总和",
            type = QuestionType.Backtracking,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/combination-sum/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        // 如果目标值为0，说明当前组合有效，加入结果集
        // 同样画一遍
        if (target == 0) {
            result.add(new ArrayList<>(current)); // 注意不要直接传 current 进去
            return;
        }

        // 从start开始遍历，避免重复组合
        for (int i = start; i < candidates.length; i++) {
            // 如果当前数字大于目标值，跳过
            if (candidates[i] > target) {
                continue;
            }
            // 选择当前数字
            current.add(candidates[i]);
            // 递归调用，允许重复选择当前数字，所以 i 不变
            backtrack(candidates, target - candidates[i], i, current, result); // start = i 以允许服用同一个数
            // 回溯，移除当前数字
            current.remove(current.size() - 1);
        }
    }

    @QuestionInfo(
            name = "括号生成",
            type = QuestionType.Backtracking,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/generate-parentheses/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(n, n, new StringBuilder(), result);
        return result;
    }

    private void backtrack(int left, int right, StringBuilder current, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(current.toString());
            return;
        }

        if (left > 0) {
            current.append("(");
            backtrack(left - 1, right, current, result);
            current.deleteCharAt(current.length() - 1); // 回溯就像往前推演棋盘，最后一定要复原才能推演其他的走向
        }

        if (right > left) { // 只要让场上的 ')' 不多于 '(' 即可保证没有顺序问题
            current.append(")");
            backtrack(left, right - 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
