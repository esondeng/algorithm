package com.test.algorithm;

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * <p>
 * 思路：类似二分查找法
 *
 * @author dengxiaolin
 * @since 2021/05/06
 */
public class FindPeakElement {
    public int findPeak(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return right;
    }
}
