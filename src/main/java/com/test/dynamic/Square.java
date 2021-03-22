package com.test.dynamic;

import java.util.Arrays;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * *
 * * Example:
 * *
 * * Input:
 * *
 * * 1 0 1 0 0
 * * 1 0 1 1 1
 * * 1 1 1 1 1
 * * 1 0 0 1 0
 * *
 * * Output: 4
 * Thought
 * *      方法一，暴力法
 * *          遍历以 matrix[i][j]为右下角元素、由'1'构成的正方形，求最大边长
 * *          时间复杂度为 O((mn)^2)
 * *
 * *      方法二，动态规划
 * *          dp[i+1][j+1] 表示以 matrix[i][j] 为右下角元素的正方形的最大边长
 * *          若 matrix[i][j] == '1'，则 dp[i+1][j+1] 为其左上角、左侧、上面元素的最小值加1，
 * *              即dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
 *
 * @author dengxiaolin
 * @since 2020/12/04
 */
public class Square {

    public static void main(String[] args) {
        int[][] m = new int[][] {
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };

        Square square = new Square();
        System.out.print(square.maxSquare(m));

    }

    public int maxSquare(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        int max = 0;
        int[][] dp = new int[m.length + 1][m[0].length + 1];

        for (int i = 1; i <= m.length; i++) {
            for (int j = 1; j <= m[0].length; j++) {
                if (m[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return max * max;
    }
}
