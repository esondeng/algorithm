package com.test.dfs;

import java.util.Arrays;
import java.util.Stack;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * *
 * * Example 1:
 * * Input: coins = [1, 2, 5], amount = 11
 * * Output: 3
 * * Explanation: 11 = 5 + 5 + 1
 * *
 * * Example 2:
 * * Input: coins = [2], amount = 3
 * * Output: -1
 * *
 * * Note:
 * * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * 思路：
 * 1. 贪婪算法 + 深度优先 + 回溯
 * 2. 动态规划, 比较背包问题，容积 -》amount
 * 2.1   DP数组的初始值是 amount+1
 * 2.2   动态转移条件是余额大于硬币面值大小
 * 2.3 动态转移方程是 dp[i] = min(dp[i], dp[i-coin]+1)
 * 2.4 return dp[amount] > amount ? -1 : dp[amount]
 *
 * @author dengxiaolin
 * @since 2020/12/09
 */
public class CoinChange {
    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.minPath(new int[] {1, 2, 5}, 28));
    }

    public int minPath(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        if (coins.length == 1 && coins[0] != amount) {
            return -1;
        }

        Arrays.sort(coins);
        return dfs(coins, amount, new Stack<>());
    }

    private int dfs(int[] coins, int amount, Stack<Integer> stack) {
        int sum = 0;
        for (int i = 0; i < stack.size(); i++) {
            sum += stack.get(i);
        }

        if (sum == amount) {
            return stack.size();
        }
        else if (sum > amount) {
            return -1;
        }
        else {
            for (int i = coins.length - 1; i >= 0; i--) {
                stack.push(coins[i]);
                int counter = dfs(coins, amount, stack);
                if (counter > 0) {
                    return counter;
                }
                stack.pop();
            }
        }
        return -1;

    }

    public int minPathDynamic(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];

            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j - coin] + 1, dp[j]);
            }
        }

        return dp[amount] == max ? -1 : dp[amount];
    }
}


