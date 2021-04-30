package com.test.algorithm;

/**
 * Search Insert Position
 * Easy
 * <p>
 * 3452
 * <p>
 * 293
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 * <p>
 * 思路1：
 * 找到第一个target大的index
 * 思路2：二分法
 *
 * @author dengxiaolin
 * @since 2021/04/30
 */
public class SearchInsertPosition {
    /**
     * o(n) 时间复杂度
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minIndex = 0;
        for (; minIndex < nums.length; minIndex++) {
            if (nums[minIndex] >= target) {
                return minIndex;
            }
        }

        return minIndex;
    }

    /**
     * o(n) 时间复杂度
     */
    public int searchInsert2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            else if (nums[middle] < target) {
                if (middle < nums.length - 1 && target < nums[middle + 1]) {
                    return middle + 1;
                }
                else if (middle == nums.length - 1) {
                    return middle + 1;
                }
                else {
                    start = middle + 1;
                }
            }
            else {
                if (middle > 0 && nums[middle - 1] < target) {
                    return middle;
                }
                else if (middle == 0) {
                    return 0;
                }
                else {
                    end = middle - 1;
                }
            }

        }

        return 0;
    }
}
