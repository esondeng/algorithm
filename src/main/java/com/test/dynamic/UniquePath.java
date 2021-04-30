package com.test.dynamic;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * 求从数组[0,0]走到[m,n]的不同路径数
 * <p>
 * 思路1：递归
 * 思路2：动态规划
 * 转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-1] (只能从上或者左边过来)！！！
 * 初始化：dp[0][0] == 1;
 *
 * @author dengxiaolin
 * @since 2021/04/30
 */
public class UniquePath {
    public static int uniquePaths(int m, int n) {
        return uniquePaths(0, 0, m, n);

    }

    public static int uniquePaths(int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (i >= m || j >= n) {
            return 0;
        }

        return uniquePaths(i, j + 1, m, n) + uniquePaths(i + 1, j, m, n);
    }

    public static int uniquePathsDynamic(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                }
                else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                }
                else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsDynamic(3, 3));
    }
}
