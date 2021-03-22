package com.test.array;

/**
 * You are given a list of non-negative integers, a1, a2, …, an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * <p>
 * 思路：深度遍历DFS
 *
 * @author dengxiaolin
 * @since 2020/11/19
 */
public class TargetSum {
    private int countter = 0;

    public int targetSum(int[] m, int target) {
        targetSum(m, target, 0, 0);
        return countter;
    }

    private void targetSum(int[] m, int target, int startIndex, int sum) {
        // 数组遍历完成
        if (startIndex == m.length) {
            if (sum == target) {
                countter++;
            }
        }
        else {
            targetSum(m, target, startIndex + 1, sum + m[startIndex]);
            targetSum(m, target, startIndex + 1, sum - m[startIndex]);
        }
    }
}
