package com.test.tree;

/**
 * @author dengxiaolin
 * @since 2020/11/30
 */
public class ValidTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(3);

        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(4);

        ValidTree validTree = new ValidTree();
        System.out.println(validTree.isSymmetric(treeNode));

    }

    public boolean isValid(TreeNode treeNode) {
        if (treeNode == null) {
            return true;
        }

        if (treeNode.left != null) {
            if (treeNode.left.val >= treeNode.val) {
                return false;
            }
        }

        if (treeNode.right != null) {
            if (treeNode.right.val <= treeNode.val) {
                return false;
            }
        }

        return isValid(treeNode.left) && isValid(treeNode.right);
    }

    /**
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * *
     * * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     * *
     * *     1
     * *    / \
     * *   2   2
     * *  / \ / \
     * * 3  4 4  3
     * *
     * * But the following [1,2,2,null,3,null,3] is not:
     * *
     * *     1
     * *    / \
     * *   2   2
     * *    \   \
     * *    3    3
     * *
     * * Note:
     * * Bonus points if you could solve it both recursively and iteratively.
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);

    }


    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }
}
