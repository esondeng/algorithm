package com.test.dynamic;

import java.util.Arrays;

/**
 * * Say you have an array for which the ith element is the price of a given stock on day i.
 * *
 * * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * *
 * * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * *
 * * Example:
 * * Input: [1,2,3,0,2]
 * * Output: 3
 * *
 * * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * <p>
 * 思路：动态规划
 * 1. 使用二维数据，行表示第i个价格
 * 2. 有三列，第一列表示买，第二列标书休息，第三列表示卖
 *
 * @author dengxiaolin
 * @since 2020/12/09
 */
public class Profit {

    public static void main(String[] args) {
        int[][] inputs = {
                {1, 2, 3, 7}
        };
        for (int[] input : inputs) {
            System.out.println("input = " + Arrays.toString(input));
            int profit = new Profit().maxProfit(input);
            System.out.println("profit = " + profit);
        }
    }

    public int maxProfit(int[] prices) {

        if (prices == null || prices.length < 1) {
            return 0;
        }

        // 第一列buy，第二列cooldown， 第三列：sell
        int[][] dp = new int[prices.length + 1][3];
        for (int i = 1; i <= prices.length; i++) {
            // 买第i个，只能是i-1休息
            dp[i][0] = dp[i - 1][1];

            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]);

            // i卖的话，i-1可能是买,i-2,i-3..都可能是买
            if (i >= 2) {
                int max = 0;
                for (int j = i; j >= 2; j--) {
                    max = Math.max(max, dp[j - 1][0] + prices[j - 1] - prices[j - 2]);
                }
                dp[i][2] = max;
            }
        }

        for (int i = 0; i < dp.length; i++) {
            Arrays.stream(dp[i]).forEach(t -> System.out.print(t + " "));
            System.out.println();
        }

        return Math.max(Math.max(dp[prices.length][0], dp[prices.length][1]), dp[prices.length][2]);
    }
}
