package com.niki.top_100_liked._2025.april;

import com.niki.top_100_liked.util.QType;

import java.util.*;

public class D21 {

    /**
     * 字母异位词分组
     * https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked
     */
    @QType("哈希")
    static class Solution1 {
        // 简单哈希算法，用于计算字母频次数组的哈希值
        private int hash(int[] arr) {
            int v = 0;
            for (int j : arr) {
                v = v * 31 + j; // 哈希因子
            }
            return v;
        }

        // 创建字母频次数组并返回哈希值
        private int countLetters(String str) {
            int[] arr = new int[26];

            for (char c : str.toCharArray()) {
                arr[c - 'a']++; // 字符编码直接作差，a直接对应索引0
            }

            return hash(arr);
        }

        public List<List<String>> groupAnagrams(String[] strs) {
            Map<Integer, List<String>> map = new HashMap<>();

            for (String s : strs) {
                int key = countLetters(s);

                // 计算字符串的异位词哈希并写入map
                if (map.containsKey(key)) {
                    map.get(key).add(s);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(s);
                    map.put(key, list);
                }
            }

            return new ArrayList<>(map.values());
        }
    }

    /**
     * 最长连续序列
     * https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked
     */
    @QType("哈希")
    static class Solution2 {
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
    }

    /**
     * 移动零
     * https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
     */
    @QType("双指针")
    static class Solution3 {
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
}
