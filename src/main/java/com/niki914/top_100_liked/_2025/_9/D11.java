package com.niki914.top_100_liked._2025._9;

import com.niki914.top_100_liked.Day;
import com.niki914.top_100_liked.util.annotation.Link;
import com.niki914.top_100_liked.util.annotation.QuestionDifficulty;
import com.niki914.top_100_liked.util.annotation.QuestionInfo;
import com.niki914.top_100_liked.util.annotation.QuestionType;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Link(
        last = D10.class,
        next = D12.class
)
public class D11 implements Day {

    @QuestionInfo(
            name = "数组中的第K个最大元素",
            type = QuestionType.Heap,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/kth-largest-element-in-an-array/description/?envType=study-plan-v2&envId=top-100-liked",
            numberInType = 1
    )
    public int findKthLargest(int[] nums, int k) {
        // 目标是找到升序排序后，索引为 N - k 的元素
        int targetIndex = nums.length - k;
        int left = 0;
        int right = nums.length - 1;

        while (true) {
            // 执行分区操作，返回枢轴元素最终的位置索引
            int pivotIndex = partition(nums, left, right);

            if (pivotIndex == targetIndex) {
                // 找到了目标位置
                return nums[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                // 枢轴位置太靠前，目标在右侧（更大索引）
                left = pivotIndex + 1;
            } else {
                // 枢轴位置太靠后，目标在左侧（更小索引）
                right = pivotIndex - 1;
            }
        }
    }

    // 快速排序的分区操作（将小于枢轴的元素放在左边，大于枢轴的元素放在右边）
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left]; // 选择最左边的元素作为枢轴
        int j = left; // j 用来追踪小于等于 pivot 的元素的边界

        // 遍历 [left + 1, right]，将所有小于等于 pivot 的元素移动到左边
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] <= pivot) {
                j++;
                swap(nums, i, j);
            }
        }

        // 交换枢轴元素到最终位置
        swap(nums, left, j);
        return j; // 返回枢轴元素最终的索引
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

//        // 创建最小堆，容量为 k
//        MinHeap minHeap = new MinHeap(k);
//
//        // 遍历数组
//        for (int num : nums) {
//            minHeap.offer(num); // 插入（维护小顶堆的顺序）
//
//            // 保持堆大小为 k
//            // 只从前面（小值）移除
//            if (minHeap.size() > k) {
//                minHeap.poll(); // 移除最小元素
//            }
//        }
//
//        // 堆顶，也就是最小的，此处刚好为第 k 大的
//        return minHeap.peek();
//    }

    static class MinHeap {
        private int[] heap; // 存储堆的数组
        private int size;  // 当前堆大小
        private int capacity; // 堆的最大容量

        public MinHeap(int capacity) {
            this.capacity = capacity;
            this.heap = new int[capacity];
            this.size = 0;
        }

        /**
         * 插入元素
         */
        public void offer(int val) {
            if (size < capacity) {
                // 堆未满，直接插入到末尾并上浮
                heap[size] = val;
                siftUp(size);
                size++;
            } else if (val > heap[0]) {
                // 如果堆满且新元素大于堆顶，替换堆顶并下沉
                heap[0] = val;
                siftDown(0);
            }
        }

        /**
         * 移除堆顶（最小元素）
         */
        public int poll() {
            if (size == 0) throw new IllegalStateException("Heap is empty");
            int result = heap[0];
            heap[0] = heap[size - 1]; // 用最后一个元素替换堆顶，然后沉底
            size--;
            siftDown(0); // 堆顶下沉
            return result;
        }

        /**
         * 获取堆顶
         */
        public int peek() {
            if (size == 0) throw new IllegalStateException("Heap is empty");
            return heap[0];
        }

        // 获取当前堆大小
        public int size() {
            return size;
        }

        // 上浮：将新插入的元素调整到正确位置
        private void siftUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2; // 父节点索引
                if (heap[index] >= heap[parent]) break; // 如果当前节点 >= 父节点，停止
                swap(heap, index, parent); // 交换
                index = parent; // 继续向上检查
            }
        }

        // 下沉：将堆顶元素调整到正确位置
        private void siftDown(int index) {
            int minIndex = index;
            while (true) {
                int left = 2 * index + 1; // 左子节点
                int right = 2 * index + 2; // 右子节点

                // 找到当前节点、左子节点、右子节点中最小的
                if (left < size && heap[left] < heap[minIndex]) {
                    minIndex = left;
                }
                if (right < size && heap[right] < heap[minIndex]) {
                    minIndex = right;
                }

                if (minIndex == index) break; // 如果当前节点最小，停止
                swap(heap, index, minIndex); // 交换
                index = minIndex; // 继续向下检查
            }
        }

        // 交换数组中的两个元素
        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    @QuestionInfo(
            name = "前 K 个高频元素",
            type = QuestionType.Heap,
            difficulty = QuestionDifficulty.MEDIUM,
            link = "https://leetcode.cn/problems/top-k-frequent-elements/description/?envType=study-plan-v2&envId=top-100-liked"
    )
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计每个元素的频率
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // 2. 使用最小堆维护频率最高的 k 个元素
        // 用 entry.value 比较，频次大者为大
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                /*
                    Comparator: a > b 返回正，等于返回 0...
                 */
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue()); // 按频率升序

        // 和上一题差不多
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // 移除频率最小的
            }
        }

        // 3. 提取堆中的 k 个元素
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }
}