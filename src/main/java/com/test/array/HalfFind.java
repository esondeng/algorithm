package com.test.array;

/**
 * @author dengxiaolin
 * @since 2021/05/19
 */
public class HalfFind {

    public static int halfFind(int[] nums, int target) {
        return halfFind(nums, 0, nums.length - 1, target);
    }

    private static int halfFind(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int middle = (end + start) / 2;
            if (target < nums[middle]) {
                if (nums[start] < nums[middle]) {
                    end = middle - 1;
                }
                else {
                    end = middle + 1;
                }
            }
            else if (target == nums[middle]) {
                return middle;
            }
            else {
                // in current range
                if (nums[middle] < nums[end] && target <= nums[end]) {
                    start = middle + 1;
                }
                else {
                    end = middle - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4, 5, 1, 2, 3};
        System.out.println(halfFind(nums, 5));
    }
}
