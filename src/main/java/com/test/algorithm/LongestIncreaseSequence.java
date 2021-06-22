package com.test.algorithm;

import java.util.Arrays;

/**
 * 数组中的最长递增子序列
 * 思路：动态规划
 * 转移方程：
 * LIS
 * dp[i] = if(nums[i] > dp[j]) 其中 0 <= j < i, Math.max(dp[i], dp[j] + 1);
 * else (if(dp[i] == 0)){
 * dp[i] = 1;
 * }
 *
 * @author dengxiaolin
 * @since 2021/06/04
 */
public class LongestIncreaseSequence {

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int max = 0;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
     * <p>
     * Formally the function should:
     * Return true if there exists i, j, k
     * such that arr[i] < arr[j]< arr[k]given 0 ≤ i< j<k≤ n-1 else return false.
     * <p>
     * 思路：LIS的变种
     */
    public static boolean IncreasingTripletSubsequence(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] >= 3) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
