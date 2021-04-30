package com.test.algorithm;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * 思路：
 * 二分查找的思路，多了一步判断，判断哪部分有序，是否在这部分中
 *
 * @author dengxiaolin
 * @since 2021/04/29
 */
public class BinarySearch {

    public static int search(int[] nums, int target) {
        int index = findIndex(nums, 0, nums.length - 1);

        int findIndex = binarySearch(nums, target, 0, index);
        if (findIndex > 0) {
            return findIndex;
        }

        return binarySearch(nums, target, index + 1, nums.length - 1);
    }

    public static int binarySearch(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int middle = (start + end) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            else if (nums[middle] < target) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }

        return -1;
    }

    private static int findIndex(int[] nums, int start, int end) {
        if (end <= 0 || start > end) {
            return -1;
        }

        if (start == end) {
            if (start >= 1 && nums[start] < nums[start - 1]) {
                return start - 1;
            }

            return -1;
        }

        int middle = (start + end) / 2;
        if (middle >= 1 && nums[middle] < nums[middle - 1]) {
            return middle - 1;
        }
        else {
            int index = findIndex(nums, start, middle - 1);
            if (index != -1) {
                return index;
            }

            index = findIndex(nums, middle + 1, end);
            if (index != -1) {
                return index;
            }

            return -1;
        }
    }


    public static int search2(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;

        while (begin <= end) {
            int middle = (begin + end) / 2;
            if (nums[middle] == target) {
                return middle;
            }

            // 左边有序
            if (nums[begin] <= nums[middle]) {
                if (nums[begin] <= target && target < nums[middle]) {
                    end = middle - 1;
                }
                else {
                    begin = middle + 1;
                }
            }
            else {
                if (nums[middle] < target && target <= nums[end]) {
                    begin = middle + 1;
                }
                else {
                    end = middle - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] m = new int[] {4, 5, 6, 0, 1, 2, 3};
        System.out.println(search(m, 7));
    }
}
