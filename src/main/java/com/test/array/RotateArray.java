package com.test.array;

import java.util.Arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * <p>
 * 思路：
 * 考虑链表的情况，就是后面k个移动到前面；
 * 因此，把后面k个作为中转，一直移动
 * [1, 2, 3, 4, 5, 6, 7]
 * [6, 7, 3, 4, 5, 1, 2]
 * [6, 7, 1, 2, 5, 3, 4]
 * [6, 7, 1, 2, 3, 4, 5]
 * [6, 7, 1, 2, 3, 4, 5]
 *
 * @author dengxiaolin
 * @since 2021/05/06
 */
public class RotateArray {
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (nums.length < 2 || k == 0) {
            return;
        }

        for (int i = 0; i < k; i++) {
            int temp = nums[nums.length - 1];

            // 从后往前遍历
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

    public static void rotate2(int[] nums, int k) {
        k = k % nums.length;
        if (nums.length < 2 || k == 0) {
            return;
        }

        for (int i = 0; i < k; i++) {
            int current = i;
            int next = (current + k) % nums.length;

            int currentVal = nums[current];
            int currentNext = nums[next];

            do {
                nums[next] = currentVal;
                currentVal = currentNext;

                System.out.println(Arrays.toString(nums));

                current = next;
                next = (current + k) % nums.length;



            } while (next != i);
        }
    }

    private static void swap(int[] nums, int i, int j) {


        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(nums));
        rotate2(nums, 3);
        System.out.println(Arrays.toString(nums));


    }

}
