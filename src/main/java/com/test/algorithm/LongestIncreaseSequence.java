package com.test.algorithm;

/**
 * 数组中的最长递增子序列
 * 思路：动态规划
 * 转移方程：
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
        dp[0] = 1;

        int max = 0;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i + 1], dp[j] + 1);
                }
                // 比前面的所有都小
                else if (dp[i] == 0) {
                    dp[i] = 1;
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;

    }
}
