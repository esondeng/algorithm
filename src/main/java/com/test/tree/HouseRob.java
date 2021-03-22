package com.test.tree;

/**
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root." Besides the root,
 * each house has one and only one parent house. After a tour,
 * the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * *
 * * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * *
 * * Example 1:
 * * Input: [3,2,3,null,3,null,1]
 * *
 * *      3
 * *     / \
 * *    2   3
 * *     \   \
 * *      3   1
 * * Output: 7
 * * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * *
 * * Example 2:
 * * Input: [3,4,5,1,3,null,1]
 * *
 * *      3
 * *     / \
 * *    4   5
 * *   / \   \
 * *  1   3   1
 * * Output: 9
 * * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * <p>
 * 思路：深度优先，抢root or 不抢root
 *
 * @author dengxiaolin
 * @since 2020/12/10
 */
public class HouseRob {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(3);
        root.right.right = new TreeNode(1);


        HouseRob houseRob = new HouseRob();
        System.out.println(houseRob.maxRob(root));

    }

    public int maxRob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 抢root
        int robRoot = root.val;
        if (root.left != null) {
            robRoot += maxRob(root.left.left);
            robRoot += maxRob(root.left.right);
        }

        if (root.right != null) {
            robRoot += maxRob(root.right.left);
            robRoot += maxRob(root.right.right);
        }

        // 不抢root
        int notRobRoot = maxRob(root.left) + maxRob(root.right);
        return Math.max(robRoot, notRobRoot);
    }
}
