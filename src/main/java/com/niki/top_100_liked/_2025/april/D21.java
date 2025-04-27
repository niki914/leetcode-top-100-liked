package com.niki.top_100_liked._2025.april;

import com.niki.top_100_liked.util.QType;

import java.util.*;

public class D21 {
    @QType(
            name = "字母异位词分组",
            type = "哈希",
            difficulty = "中等",
            link = "https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<Integer, List<String>> map = new HashMap<>();

        // 计算字符串的异位词哈希并写入 map
        for (String s : strs) {
            // 统计字符频率
            int[] charCount = new int[26];
            for (char c : s.toCharArray()) {
                charCount[c - 'a']++; // 字符编码直接作差, a 直接对应索引 0
            }

            // 计算频率数组的哈希值
            int hash = 0;
            for (int count : charCount) {
                hash = hash * 31 + count; // 31 是一个比较好的哈希因子
            }

            // 分组存储
            map.computeIfAbsent(hash, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    @QType(
            name = "最长连续序列",
            type = "哈希",
            difficulty = "中等",
            link = "https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        // 用 set 转录，降低查找的时间复杂度
        for (int num : nums)
            set.add(num);

        int max = 0;

        for (int num : set) {
            // 跳过有更小连续项的数字
            // 确保是连续序列的起点
            // 因为如果有更小的连续项，num 就不是起点，计算出的长度就必定不是最大的
            if (set.contains(num - 1)) continue;

            // start 是可能的连续序列的开头
            int start = num;
            int innerMax = 1; // 包含 num
            // 一直计数直到没有连续项
            while (set.contains(start + 1)) {
                start++;
                innerMax++;
            }

            // 比较和更新最大长度
            if (innerMax > max) max = innerMax;
        }

        return max;
    }

    @QType(
            name = "移动零",
            type = "双指针",
            difficulty = "简单",
            link = "https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked"
    )
    public void moveZeroes(int[] nums) {
        int leftP = 0;

        // 我个人确实不太理解，但是这个很清晰的一点是左指针的左边绝对全都是排好的非零数
        for (int rightP = 0; rightP < nums.length; rightP++) {
            int rightNum = nums[rightP];

            // 左右指针位置相同:
            //  a) 同时发现非零数，同时右移
            //  b) 同时发现0，左指针开始停留(停留在0)
            // 右指针先于左指针发现非零数，对换后左指针被换成一个非零数，然后左指针右移
            //  a) 右指针领先一位: 交换后左指针重新指向自己先前的0
            //  b) 右指针领先若干位: 由于(lPos, rPos)开区间内的数字都为0，左指针右移到一个新的0上

            // 后续的新状态可以继续用上面的情况来推断
            if (rightNum != 0) {
                // 调换左右指针
                int leftNum = nums[leftP];
                nums[leftP] = rightNum;
                nums[rightP] = leftNum;

                // 右移左指针
                leftP++;
            }
        }
    }
}
