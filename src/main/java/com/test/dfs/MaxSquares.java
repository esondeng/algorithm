package com.test.dfs;

import java.util.Arrays;
import java.util.Map;

/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * <p>
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * 思路：动态规划
 * 转移方程是：dp[i] = Math.min(dp[i], dp[i-j*j] +1), 其中，1<= j <= i;
 * <p>
 * DFS
 *
 * @author dengxiaolin
 * @since 2021/06/02
 */
public class MaxSquares {
    public static int numSquares(int n, Map<Integer, Integer> map) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i = j * j] + 1);
            }
        }
        return dp[n];
    }

    public static int dfs(int n) {
        if (n == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            min = Math.min(min, dfs(n - i * i) + 1);
        }

        return min;
    }

    /**
     * 记录中间结果
     */
    public static int dfs1(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            min = Math.min(min, dfs(n - i * i) + 1);
        }
        map.put(n, min);
        return min;
    }
}
