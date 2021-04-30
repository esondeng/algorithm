package com.test.dfs;

import java.util.Stack;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * <p>
 * 贪婪算法+
 * 深度优先遍历
 * <p>
 * 思路1：从右到左：贪心算法
 * 思路2：从左到右，贪心+回溯算法
 *
 * @author dengxiaolin
 * @since 2020/11/20
 */
public class Jump {

    public static void main(String[] args) {
        Jump jump = new Jump();
        System.out.println(canReach(new int[] {2, 3, 1, 1, 4}));
    }


    private static boolean canReach(int[] nums) {
        int des = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= des) {
                des = i;
            }
        }
        return des == 0;

    }

    /**
     * 从做到右，回溯算法
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        return canJump(nums, 0, stack);
    }

    private boolean canJump(int[] nums, int index, Stack<Integer> stack) {
        int sum = stack.stream().mapToInt(t -> t).sum();
        if (sum >= nums.length - 1) {
            return true;
        }

        for (int i = nums[index]; i > 0; i--) {
            stack.push(i);
            if (canJump(nums, sum, stack)) {
                return true;
            }
            stack.pop();
        }

        return false;
    }
}
