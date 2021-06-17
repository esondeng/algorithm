package com.test.algorithm;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Find Median from Data Stream
 * 寻找中位数
 * 思路，左边最大堆，右边最小堆， 利用JAVA的PriorityQueue，用数组来实现的最小堆。
 * 进来的数据，先放最大堆
 *
 * @author dengxiaolin
 * @since 2021/06/04
 */
public class MedianFinder {

    /**
     * 右边
     */
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    /**
     * 左边
     */
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public static void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static double findMedian() {
        return maxHeap.size() == minHeap.size() ? (double) (minHeap.peek() + maxHeap.peek()) / 2D : maxHeap.peek();
    }
}
