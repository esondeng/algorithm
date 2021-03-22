package com.test.dfs;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * *
 * * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * *
 * * How many possible unique paths are there?
 * *
 * *
 * * Above is a 7 x 3 grid. How many possible unique paths are there?
 * *
 * * Note: m and n will be at most 100.
 * *
 * * Example 1:
 * * Input: m = 2, n = 3
 * * Output: 3
 * * Explanation:
 * * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * * 1. Right -> Right -> Down
 * * 2. Right -> Down -> Right
 * * 3. Down -> Right -> Right
 * *
 * * Example 2:
 * * Input: m = 7, n = 3
 * * Output: 28
 * <p>
 * 思路，深度优先
 *
 * @author dengxiaolin
 * @since 2020/11/30
 */
public class UniquePath {

    public static void main(String[] args) {
        UniquePath path = new UniquePath();
        System.out.println(path.pathCount(7, 3));
    }

    private int counter;

    public int pathCount(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] array = new int[m][n];
        pathCount(array, 0, 0);

        return counter;
    }

    private void pathCount(int[][] m, int i, int j) {
        if (i == m.length - 1 && j == m[0].length - 1) {
            counter++;
            return;
        }


        // 向下
        if (i <= m.length - 2) {
            pathCount(m, i + 1, j);
        }

        // 向右
        if (j <= m[0].length - 2) {
            pathCount(m, i, j + 1);
        }
    }
}
