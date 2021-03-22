package com.test.algorithm;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * *
 * * Note: You can only move either down or right at any point in time.
 * *
 * * Example:
 * *
 * * Input:
 * * [
 * *   [1,3,1],
 * *   [1,5,1],
 * *   [4,2,1]
 * * ]
 * * Output: 7
 * *
 * * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * <p>
 * 思路：深度优先 + 回溯
 *
 * @author dengxiaolin
 * @since 2020/11/30
 */
public class UniquePathSum {

    public static void main(String[] args) {
        UniquePathSum pathSum = new UniquePathSum();
        int[][] m = new int[][] {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        System.out.println(pathSum.minSum(m));
    }

    private int minSum;

    public int minSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }

        int sum = m[0][0];
        minSum(m, 0, 0, sum);
        return minSum;

    }

    private void minSum(int[][] m, int i, int j, int sum) {
        if (i == m.length - 1 && j == m[0].length - 1) {
            if (minSum == 0) {
                minSum = sum;
            }
            else {
                minSum = Math.min(minSum, sum);
            }
            return;
        }

        // 向下
        if (i <= m.length - 2) {
            minSum(m, i + 1, j, sum + m[i + 1][j]);
        }

        // 向右
        if (j <= m[0].length - 2) {
            minSum(m, i, j + 1, sum + m[i][j + 1]);
        }
    }
}
