package com.test.tree;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * *
 * * Example:
 * * Given a binary tree
 * *           1
 * *          / \
 * *         2   3
 * *        / \
 * *       4   5
 * * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * *
 * * Note: The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * 计算二叉树的最长路径
 * 思路，经过自己，depth(left) + depth(right) +2
 * 不经过自己， getChildMaxDepth(left) or getChildMaxDepth(right)
 *
 * @author dengxiaolin
 * @since 2020/11/18
 */
public class LongestPath {

    int max = 0;

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);

        treeNode.right = new TreeNode(3);


        LongestPath longestPath = new LongestPath();
        longestPath.maxVal(treeNode);
        System.out.println(longestPath.max);

    }

    public int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxPath(root.left);
        int right = maxPath(root.right);

        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }


    public int maxVal(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxVal(root.left);
        int right = maxVal(root.right);

        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

}
