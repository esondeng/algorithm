package com.test.dfs;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * *
 * * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * *
 * * Example 1:
 * * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * * Output: 5
 * * Explanation:
 * *
 * * -1+1+1+1+1 = 3
 * * +1-1+1+1+1 = 3
 * * +1+1-1+1+1 = 3
 * * +1+1+1-1+1 = 3
 * * +1+1+1+1-1 = 3
 * *
 * * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * *
 * * Note:
 * * The length of the given array is positive and will not exceed 20.
 * * The sum of elements in the given array will not exceed 1000.
 * * Your output answer is guaranteed to be fitted in a 32-bit integer.
 *
 * @author dengxiaolin
 * @since 2020/12/11
 */
public class TargetSum {
    public static void main(String[] args) {
        System.out.println(targetSum(new int[] {1, 1, 1, 1, 1}, 3));
    }

    public static int targetSum(int[] m, int sum) {
        if (m == null || m.length == 0) {
            return 0;
        }

        return dfs(m, 0, sum, 0);
    }

    private static int dfs(int[] m, int start, int sum, int currentSum) {
        if (start == m.length) {
            return currentSum == sum ? 1 : 0;
        }

        return dfs(m, start + 1, sum, currentSum + m[start])
                + dfs(m, start + 1, sum, currentSum - m[start]);
    }

}
