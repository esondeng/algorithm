package com.test.dynamic;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * <p>
 * 思路：爬梯子问题1. 递归， 2.类似菲薄莱切数列动态规划
 * <p>
 * 爬梯子是一维数组的动态规划，uniquePath是二维数组的动态规划
 *
 * @author dengxiaolin
 * @since 2021/04/30
 */
public class ClimbStairs {
    /**
     * 动态规划，菲薄莱切数列
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            if (i >= 2) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[n];
    }

    /**
     * 递归
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {

        if (n == 1) {
            return 1;
        }

        if (n == 0) {
            return 1;
        }


        return climbStairs2(n - 2) + climbStairs2(n - 1);
    }

}
