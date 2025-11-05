package com.niki914.top_100_liked._2025._4;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D21 {
    @QuestionInfo(
            name = "字母异位词分组",
            type = QuestionType.Hash,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 2
    )
    public List<List<String>> groupAnagrams(String[] strs) {
        // 这种解法是 hash 值（Int）结合 Map，必须记住 'a' 的 ASCI 码是 26
        // 最好还知道 31 是一个不错的哈希因子
        // 这是因为 31 是一个素数，并且乘法可优化为位运算（ 31 == 32 - 1），很高效


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
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<>());
            }
            map.get(hash).add(s);
        }

        return new ArrayList<>(map.values());
    }

    @QuestionInfo(
            name = "最长连续序列",
            type = QuestionType.Hash,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 3
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

            set.remove(start);

            // 一直计数直到没有连续项
            while (set.contains(start + 1)) {
                start++;
                innerMax++;
                set.remove(start);
            }

            // 比较和更新最大长度
            if (innerMax > max) max = innerMax;
        }

        return max;
    }

    @QuestionInfo(
            name = "移动零",
            type = QuestionType.TwoPointers,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 1
    )
    public void moveZeroes(int[] nums) {
        int l = 0;

        /*
            2025.11.5 review

            这个算法的表现是，只要 rNum 不为 0 就交换左右并让 l 自增
            这个效果是，遇到 0 就会跳过并且在遇到下一个非零数时，l 必然停留在一个 0 上，这时交换就能保持顺序调换
         */

        // 我个人确实不太理解，但是这个很清晰的一点是左指针的左边绝对全都是排好的非零数
        for (int r = 0; r < nums.length; r++) {
            int rNum = nums[r];

            // 左右指针位置相同:
            //  a) 同时发现非零数，同时右移
            //  b) 同时发现0，左指针开始停留(停留在0)
            // 右指针先于左指针发现非零数，对换后左指针被换成一个非零数，然后左指针右移
            //  a) 右指针领先一位: 交换后左指针重新指向自己先前的0
            //  b) 右指针领先若干位: 由于(lPos, rPos)开区间内的数字都为0，左指针右移到一个新的0上

            // 后续的新状态可以继续用上面的情况来推断
            if (rNum != 0) {
                // 调换左右指针
                nums[r] = nums[l];
                nums[l] = rNum;

                // 右移左指针
                l++;
            }
        }
    }
}
