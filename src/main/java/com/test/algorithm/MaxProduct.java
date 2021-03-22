package com.test.algorithm;

/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 * *
 * * Example 1:
 * * Input: [2,3,-2,4]
 * * Output: 6
 * * Explanation: [2,3] has the largest product 6.
 * *
 * * Example 2:
 * * Input: [-2,0,-1]
 * * Output: 0
 * * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * 思路：动态规划，连续子串最大乘积
 * 转移函数：如果当前是负数：
 * max = Math.max(imin * m[i], m[i]);
 * 如果是正数
 * max = Math.max(imax * m[i], m[i]);
 *
 * @author dengxiaolin
 * @since 2020/12/01
 */
public class MaxProduct {

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        System.out.println(maxProduct.maxSum(new int[] {2, 3, -2, 6}));
    }

    public int maxProduct(int[] nums) {
        int max = nums[0];

        // 当前的最大值
        int imax = nums[0];

        // 当前的最小值
        int imin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 如果是负数，imax和imin交换
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            // 为了连续，取自己
            imin = Math.min(imin * nums[i], nums[i]);
            max = Math.max(max, imax);
        }

        return max;
    }


    /**
     * 最大子序和
     * 转移函数，一直累加，当遇到总和<0时，重新计数
     *
     * @param nums
     * @return
     */
    public int maxSum(int[] nums) {
        int max = nums[0];
        int currentSum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (currentSum < 0) {
                currentSum = 0;
            }

            currentSum += nums[i];
            max = Math.max(currentSum, max);
        }

        return max;
    }

}
