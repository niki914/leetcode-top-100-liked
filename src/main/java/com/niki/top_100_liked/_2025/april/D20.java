package com.niki.top_100_liked._2025.april;

import com.niki.top_100_liked.util.QType;

import java.util.HashMap;
import java.util.Map;

public class D20 {

    @QType(
            name = "两数之和",
            type = "哈希",
            difficulty = "简单",
            link = "https://leetcode.cn/problems/two-sum/?envType=study-plan-v2&envId=top-100-liked"
    )
    static class Solution1 {
        /**
         * 从前面的数中找是否存在目标差值，map 的记录方式保证了返回的索引不同
         * 如果在一开始就全部记入 map，可能要先删除自身再进行查找，浪费资源
         */
        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length < 2)
                return new int[]{};

            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                int com = target - num;

                if (map.containsKey(com))
                    return new int[]{i, map.get(com)};

                map.put(num, i);
            }

            return new int[]{};
        }
    }
}
