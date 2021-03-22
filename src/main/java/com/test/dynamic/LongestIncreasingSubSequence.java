package com.test.dynamic;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * *
 * * Example:
 * * Input: [10,9,2,5,3,7,101,18]
 * * Output: 4
 * *
 * * Explanation:
 * * The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * *
 * * Note:
 * * There may be more than one LIS combination, it is only necessary for you to return the length.
 * * Your algorithm should run in O(n2) complexity.
 * *
 * * Follow up: Could you improve it to O(n log n) time complexity?
 * <p>
 * 思路， LIS
 * 动态规划，转移方程是：
 * *      1. dp[i+1] 表示以 nums[i]为结尾元素的最长上升子序列的长度
 * *      2. 初始值是 1
 * *      3. 动态转移条件和方程为
 * *          nums[i] > nums[j]， (0<= j< i)
 * *              dp[i] = max(dp[i], dp[j]+1)
 * *      4. return max(dp[i])
 *
 * @author dengxiaolin
 * @since 2020/12/08
 */
public class LongestIncreasingSubSequence {

    public static void main(String[] args) {
        LongestIncreasingSubSequence subSequence = new LongestIncreasingSubSequence();
        System.out.println(subSequence.lis(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lis(int[] m) {
        int[] dp = new int[m.length + 1];
        // 每个元素的最小子序列是1
        Arrays.fill(dp, 1);

        int max = 0;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] > m[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
