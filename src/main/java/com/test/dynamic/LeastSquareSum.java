package com.test.dynamic;

/**
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * *
 * * Example 1:
 * *
 * * Input: n = 12
 * * Output: 3
 * * Explanation: 12 = 4 + 4 + 4.
 * * Example 2:
 * *
 * * Input: n = 13
 * * Output: 2
 * * Explanation: 13 = 4 + 9.
 * <p>
 * 思路：动态规划
 * 转移方程：
 * 1. 最坏的情况是m[n] = n;
 * 2. m(n) = Math.min(m[n - j * j] + 1, m[n]), 其中 1 <= j && j * j <= n
 *
 * @author dengxiaolin
 * @since 2020/12/08
 */
public class LeastSquareSum {

    public static void main(String[] args) {
        LeastSquareSum leastSquareSum = new LeastSquareSum();
        System.out.println(leastSquareSum.leastSquare(13));
    }

    public int leastSquare(int n) {
        int[] m = new int[n + 1];

        for (int i = 1; i < m.length; i++) {
            // 最坏的情况
            m[i] = i;
            for (int j = 1; j * j <= i; j++) {
                m[i] = Math.min(m[i - j * j] + 1, m[i]);
            }
        }

        return m[n];
    }
}
