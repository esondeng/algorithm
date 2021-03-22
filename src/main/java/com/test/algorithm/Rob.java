package com.test.algorithm;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * *
 * * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * *
 * * Example 1:
 * * Input: [1,2,3,1]
 * * Output: 4
 * * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * *              Total amount you can rob = 1 + 3 = 4.
 * *
 * * Example 2:
 * * Input: [2,7,9,3,1]
 * * Output: 12
 * * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * *              Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * @author dengxiaolin
 * @since 2020/12/01
 */
public class Rob {

    public static void main(String[] args) {
        Rob rob = new Rob();
        System.out.println(rob.maxRob(new int[] {1, 2, 3, 1}));
    }

    /**
     * 递归思路
     *
     * @param m
     * @return
     */
    public int maxRob(int[] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        return maxRob(m, m.length - 1);
    }

    private int maxRob(int[] m, int index) {
        if (index < 0) {
            return 0;
        }

        // 偷当前index和不偷当前Index
        return Math.max(maxRob(m, index - 2) + m[index], maxRob(m, index - 1));
    }


    /**
     * 动态规划思路
     */
    public int maxRob1(int[] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        // 第一列不抢，第二列抢
        int[][] dp = new int[m.length + 1][2];
        for (int i = 1; i < dp.length; i++) {
            // 不抢第i个
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);

            // 抢第i个
            dp[i][1] = dp[i - 1][0] + m[i - 1];
        }

        return Math.max(dp[m.length][0], dp[m.length][1]);
    }
}
