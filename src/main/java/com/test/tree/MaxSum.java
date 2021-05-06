package com.test.tree;

/**
 * @author dengxiaolin
 * @since 2021/05/04
 */
public class MaxSum {
    static int max = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        max(root);
        return max;
    }

    private static int max(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = Math.max(max(root.left), 0);
        int rightSum = Math.max(max(root.right), 0);

        max = Math.max(max, leftSum + rightSum + root.val);

        return Math.max(leftSum, rightSum) + root.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(maxPathSum(root));
    }
}
