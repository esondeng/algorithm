package com.test.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Count of Smaller Numbers After Self
 * [LeetCode] 315. Count of Smaller Numbers After Self 计算后面较小数字的个数
 * <p>
 * <p>
 * You are given an integer array nums and you have to return a new counts array. The countsarray has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example:
 * <p>
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * <p>
 * <p>
 * 这道题给定了一个数组，让我们计算每个数字右边所有小于这个数字的个数，目测不能用 brute force，OJ 肯定不答应，那么为了提高运算效率，首先可以使用用二分搜索法，思路是将给定数组从最后一个开始，用二分法插入到一个新的数组，这样新数组就是有序的，那么此时该数字在新数组中的坐标就是原数组中其右边所有较小数字的个数
 * <p>
 * 思路：
 * 1. 二分查找法，找到第一个比自己大的
 *
 * @author dengxiaolin
 * @since 2021/06/08
 */
public class SmallNumbers {
    public static void main(String[] args) {
        int[] result = countSmallers(new int[] {6, 5, 2, 6, 1});
        System.out.println(Arrays.toString(result));
    }

    public static int[] countSmallers(int[] nums) {
        int[] result = new int[nums.length];
        result[nums.length - 1] = 0;

        List<Integer> list = new ArrayList<>(nums.length);
        list.add(nums[nums.length - 1]);

        // 插入排序算法 + 二分查找法
        for (int i = nums.length - 2; i >= 0; i--) {
            int start = 0;
            int end = list.size() - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (list.get(mid) < nums[i]) {
                    start = mid + 1;
                }
                else if (list.get(mid) >= nums[i]) {
                    end = mid - 1;
                }
            }

            result[i] = start;
            list.add(start, nums[i]);
        }

        return result;
    }
}
