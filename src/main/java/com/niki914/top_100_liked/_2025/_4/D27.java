package com.niki914.top_100_liked._2025._4;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D27 extends Day {

    @Override
    @NotNull
    public Day getNext() {
        return new D28();
    }

    @QuestionInfo(
            name = "找到字符串中所有字母异位词",
            type = QuestionType.SlidingWindow,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 2
    )
    public List<Integer> findAnagrams(String s, String p) {

        /*

    25.11.11 解得 使用哈希值解法 未使用滑动窗口

    int hash(String s) {
        char[] chars = s.toCharArray();

        int[] arr = new int[26];

        for (char c: chars) {
            arr[c - 'a']++;
        }

        int hash = 0;

        for (int count: arr) {
            hash = hash * 31 + count;
        }

        return hash;
    }

    public List<Integer> findAnagrams(String s, String p) {
        Set<Integer> set = new HashSet<>();

        Map<String, Integer> cache = new HashMap<>();

        int len = p.length();
        int pHash = hash(p);

        cache.put(p, pHash);

        for(int i = 0; i <= s.length() - len; i++) {
            String subStr = s.substring(i, i + len);

            int subHash;

            if(cache.containsKey(subStr)){
                subHash = cache.get(subStr);
            } else {
                subHash = hash(subStr);
                cache.put(subStr, subHash);
            }

            if(subHash == pHash) {
                set.add(i);
            }
        }

        return new ArrayList<Integer>(set);
    }
         */

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
            type = QuestionType.Substring,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 1
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
            // 也就是说在 [0..$currIndex] 中出现了我们要的子串组
            // 此处不会重复添加的原因是，前面不直接添加，而是缓存，等后面检查到的时候再添加
            int targetDiff = prefixSum - k;
            if (prefixSumCount.containsKey(targetDiff)) {
                // 在这个情况下, 我们就可以累加这些子数组数量
                subarrayCount += prefixSumCount.get(targetDiff);
            }

            int count = prefixSumCount.getOrDefault(
                    /* 这里容易写错 */prefixSum,
                    0
            ) + 1;
            prefixSumCount.put(prefixSum, count);
        }

        return subarrayCount;
    }
}
