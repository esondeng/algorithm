package com.test.matrix;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * <p>
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * <p>
 * Example 1:
 * <p>
 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * <p>
 * <p>
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 * <p>
 * Example 2:
 * <p>
 * nums = [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * <p>
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * <p>
 * 思路：
 * 这道题给我们一个二维数组，让我们求矩阵中最长的递增路径，规定我们只能上下左右行走，
 * 不能走斜线或者是超过了边界。那么这道题的解法要用递归和DP来解，用DP的原因是为了提高效率，避免重复运算。
 * 我们需要维护一个二维动态数组dp，其中dp[i][j]表示数组中以(i,j)为起点的最长递增路径的长度，初始将dp数组都赋为0，
 * 当我们用递归调用时，遇到某个位置(x, y), 如果dp[x][y]不为0的话，我们直接返回dp[x][y]即可，
 * 不需要重复计算。我们需要以数组中每个位置都为起点调用递归来做，比较找出最大值。在以一个位置为起点用DFS搜索时，
 * 对其四个相邻位置进行判断，如果相邻位置的值大于上一个位置，则对相邻位置继续调用递归，并更新一个最大值，搜素完成后返回即可，
 * 参见代码如下：
 *
 * @author dengxiaolin
 * @since 2021/06/22
 */
public class LongestIncreasingPath {
    public static void main(String[] args) {
        int[][] matrix = new int[][] {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println(lip(matrix));
    }

    private static int max = 0;

    public static int lip(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Integer.max(max, dfs(matrix, dp, i, j));
            }
        }

        return max;
    }


    private static int dfs(int[][] matrix, int[][] dp, int i, int j) {
        // 改进：记录下每一个访问过的点的最大长度，当重新访问到这个点的时候，就不需要重复计算
        if (dp[i][j] > 0) {
            return dp[i][j];
        }

        // 最小为1,一个点也是
        int val = 1;
        if (i + 1 < dp.length && matrix[i + 1][j] > matrix[i][j]) {
            val = Math.max(val, 1 + dfs(matrix, dp, i + 1, j));
        }

        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            val = Math.max(val, 1 + dfs(matrix, dp, i - 1, j));
        }

        if (j + 1 < dp[0].length && matrix[i][j + 1] > matrix[i][j]) {
            val = Math.max(val, 1 + dfs(matrix, dp, i, j + 1));
        }

        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            val = Math.max(val, 1 + dfs(matrix, dp, i, j - 1));
        }

        dp[i][j] = val;
        return val;
    }
}
