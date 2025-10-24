package com.niki914.top_100_liked._2025._9;

import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.PriorityQueue;

public class D12 {

    @QuestionInfo(
            name = "数据流的中位数",
            type = QuestionType.Heap,
            difficulty = QuestionDifficulty.HARD,
            link = "https://leetcode.cn/problems/find-median-from-data-stream/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    private static class MedianFinder {
        // 小顶堆，存储较大的一半数字
        /**
         * 小顶堆自实现参考
         *
         * @see D11.MinHeap
         */
        private PriorityQueue<Integer> minHeap;
        // 大顶堆，存储较小的一半数字
        private PriorityQueue<Integer> maxHeap;

        /*
        arr = small[$maxHeap] + large[$minHeap]

        median = (maxHeap.peek + minHeap.peek) / 2
         */

        public MedianFinder() {
            // 初始化小顶堆
            minHeap = new PriorityQueue<>();
            // 初始化大顶堆，使用反向比较器
            maxHeap = new PriorityQueue<>((a, b) -> b - a /* a > b ?=> (b - a) > 0 */);
        }

        /**
         * 1. (maxHeap, minHeap) = {12}, {}
         * 2. () = {}, {12}
         * 3. (do nothing): in this condition, total num count is odd
         * 4. () = {5}, {12}
         * 5. () = {}, {5, 12}
         * 6. () = {5}, {12} : total num count is even
         */
        public void addNum(int num) {
            // 默认先加入大顶堆
            maxHeap.offer(num);
            // 将大顶堆的最大值移到小顶堆，保持小顶堆存储较大值
            minHeap.offer(maxHeap.poll());
            // 平衡堆大小：大顶堆最多比小顶堆多一个元素
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            // 如果总数为奇数，返回大顶堆的堆顶
            // 原因见 addNum()
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            // 如果总数为偶数，返回两个堆顶的平均值
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}