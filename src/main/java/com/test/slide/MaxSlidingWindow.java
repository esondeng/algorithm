package com.test.slide;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 最大滑动窗口
 * 思路：递减队列，双向队列算法
 *
 * @author dengxiaolin
 * @since 2021/05/31
 */
public class MaxSlidingWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];
        int index = 0;

        Deque<Integer> deque = new LinkedList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.addLast(i);
            if (i >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}
