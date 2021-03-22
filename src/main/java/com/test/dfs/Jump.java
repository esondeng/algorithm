package com.test.dfs;

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
 *
 * @author dengxiaolin
 * @since 2020/11/20
 */
public class Jump {

    public static void main(String[] args) {
        Jump jump = new Jump();
        System.out.println(jump.canReach(new int[] {2, 3, 1, 1, 4}));
    }

    public boolean canReach(int[] m) {
        return canReach(m, 0);
    }

    private boolean canReach(int[] m, int sum) {
        if (sum >= m.length - 1) {
            return true;
        }

        for (int i = m[sum]; i >= 1; i--) {
            // sum没变就相当于回溯
            if (canReach(m, sum + m[sum])) {
                return true;
            }
        }

        return false;

    }
}
