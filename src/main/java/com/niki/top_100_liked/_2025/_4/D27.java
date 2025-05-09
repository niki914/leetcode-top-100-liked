package com.niki.top_100_liked._2025._4;

import com.niki.top_100_liked.util.annotation.Diff;
import com.niki.top_100_liked.util.annotation.QuestionInfo;
import com.niki.top_100_liked.util.annotation.Type;

import java.util.*;

public class D27 {

    @QuestionInfo(
            name = "找到字符串中所有字母异位词",
            type = Type.SlidingWindow,
            difficulty = Diff.MEDIUM,
            link = "https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> startIndices = new ArrayList<>();

        if (s.length() < p.length()) {
            return startIndices;
        }

        // 本题的窗口用一个抽象的数组 windowCount 表示, 实际上我们只是用数组统计了窗口内的字母状态
        // 本体通过判断这两个数组的异同来判断是否为异位词
        int[] charCountP = new int[26];
        int[] windowCount = new int[26];

        // 统计 p 中每个字符的出现次数
        for (char c : p.toCharArray()) {
            charCountP[c - 'a']++;
        }

        // 初始化窗口
        for (int i = 0; i < p.length(); i++) {
            windowCount[s.charAt(i) - 'a']++;
        }

        // 检查初始窗口
        if (Arrays.equals(charCountP, windowCount)) {
            startIndices.add(0);
        }

        // 滑动窗口
        for (int i = p.length(); i < s.length(); i++) {
            windowCount[s.charAt(i - p.length()) - 'a']--; // 窗口左端收缩, 抛弃最左边的字母
            windowCount[s.charAt(i) - 'a']++; // 窗口向右扩张, 添加新的字母

            // 此时我们完成了窗口的移动, 现在检查窗口内是否是异位词
            if (Arrays.equals(charCountP, windowCount)) {
                startIndices.add(i - p.length() + 1); // 添加窗口的起始索引
            }
        }

        return startIndices;
    }

    @QuestionInfo(
            name = "和为 K 的子数组",
            type = Type.Substring,
            difficulty = Diff.MEDIUM,
            link = "https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int subarraySum(int[] nums, int k) {
        int prefixSum = 0; // 前缀和
        int subarrayCount = 0; // 子串组数

        // <前缀和, 出现次数>
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // 空子数组的前缀和为 0

        for (int num : nums) {
            prefixSum += num;

            // 目标差值: 如果存在这个数, 说明某两个前缀和的差值为 k
            // 也就是说出现了我们要的子串组
            int targetDiff = prefixSum - k;
            if (prefixSumCount.containsKey(targetDiff)) {
                // 在这个情况下, 我们就可以累加这些子数组数量
                subarrayCount += prefixSumCount.get(targetDiff);
            }

            int count = prefixSumCount.getOrDefault(prefixSum, 0) + 1;
            prefixSumCount.put(prefixSum, count);
        }

        return subarrayCount;
    }
}
