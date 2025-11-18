package com.niki914.top_100_liked._2025._4;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class D20 extends Day {

    @Override
    @NotNull
    public Day getNext() {
        return new D21();
    }

    /**
     * 从前面的数中找是否存在目标差值，map 的记录方式保证了返回的索引不同
     * 如果在一开始就全部记入 map，可能要先删除自身再进行查找，浪费资源
     */
    @QuestionInfo(
            name = "两数之和",
            type = QuestionType.Hash,
            difficulty = QuestionDifficulty.EASY,
            link = "https://leetcode.cn/problems/two-sum/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 1
    )
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2)
            return new int[]{};

        Map<Integer, Integer> map = new HashMap<>();
        // map: <value, index>

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int com = target - num;

            if (map.containsKey(com)) {
                // if target value is cached
                // then {i} and the cached index are the answers
                return new int[]{i, map.get(com)};
            }

            map.put(num, i);
        }

        return new int[]{};
    }
}
