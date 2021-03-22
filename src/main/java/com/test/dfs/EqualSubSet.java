package com.test.dfs;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a non-empty array containing only positive integers,find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * *
 * * Note:
 * * Each of the array element will not exceed 100.
 * * The array size will not exceed 200.
 * *
 * * Example 1:
 * * Input: [1, 5, 11, 5]
 * * Output: true
 * * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * *
 * * Example 2:
 * * Input: [1, 2, 3, 5]
 * * Output: false
 * * Explanation: The array cannot be partitioned into equal sum subsets.
 * <p>
 * 思路： 深度优先 + 回溯
 * 跟顺序无关，就是一个组合问题，没必要用visit来记录访问了没有，直接递归index+1
 *
 * @author dengxiaolin
 * @since 2020/12/11
 */
public class EqualSubSet {

    public static void main(String[] args) {
        int[] m = new int[] {1, 2, 3, 5};
        Arrays.sort(m);
        System.out.println(couldSplitEqualSubset(m, 0, 11, new Stack<>()));
    }

    public static boolean couldSplitEqualSubset(int[] m, int index, int total, Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int sum = stack.stream().mapToInt(t -> t).sum();
            if (sum == total - sum) {
                return true;
            }

            if (sum > total - sum) {
                return false;
            }
        }

        for (int i = index; i < m.length; i++) {
            stack.push(m[i]);
            if (couldSplitEqualSubset(m, index + 1, total, stack)) {
                return true;
            }
            stack.pop();
        }

        return false;
    }
}
