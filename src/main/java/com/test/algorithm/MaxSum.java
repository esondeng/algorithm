package com.test.algorithm;

/**
 * 最大子序和
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * *
 * * Example:
 * * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * * Output: 6
 * * Explanation: [4,-1,2,1] has the largest sum = 6.
 * *
 * * Follow up:
 * * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 *
 * @author dengxiaolin
 * @since 2020/11/27
 */
public class MaxSum {

    public static void main(String[] args) {
        MaxSum maxSum = new MaxSum();
        System.out.println(maxSum.getMaxSum(new int[] {-1, -2}));
    }

    public int getMaxSum(int[] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        int maxSum = m[0];
        int currentSum = m[0];

        for (int i = 1; i < m.length; i++) {
            if (currentSum < 0) {
                currentSum = 0;
            }

            currentSum = currentSum + m[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
