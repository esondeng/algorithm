package com.test.algorithm;

import java.util.Stack;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * *
 * * Each element in the array represents your maximum jump length at that position.
 * *
 * * Determine if you are able to reach the last index.
 * *
 * * Example 1:
 * * Input: [2,3,1,1,4]
 * * Output: true
 * * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * *
 * * Example 2:
 * * Input: [3,2,1,0,4]
 * * Output: false
 * * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * *              jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * 思路：递归回溯+贪心
 *
 * @author dengxiaolin
 * @since 2020/11/27
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] m = new int[] {2, 3, 1, 1, 4};
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canReach(m));
    }

    public boolean canReach(int[] m) {
        if (m == null || m.length == 0) {
            return false;
        }

        return canReach(m, 0, new Stack<>());
    }

    private boolean canReach(int[] m, int index, Stack<Integer> stack) {
        int sum = 0;
        for (int i = 0; i < stack.size(); i++) {
            sum += stack.get(i);
        }

        if (sum >= m.length - 1) {
            return true;
        }

        for (int i = m[index]; i > 0; i--) {
            stack.push(i);
            if (canReach(m, index + i, stack)) {
                return true;
            }
            stack.pop();
        }

        return false;
    }
}
