package com.test.tree;

/**
 * @author dengxiaolin
 * @since 2020/11/18
 */
public class SubTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(7);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(15);
        treeNode.right.left = new TreeNode(9);
        treeNode.right.right = new TreeNode(20);


        TreeNode s = new TreeNode(15);
        s.left = new TreeNode(9);
        s.right = new TreeNode(20);

        System.out.println(isSubtree(treeNode, s));

    }


    public static boolean isSubtree(TreeNode root, TreeNode s) {
        if (root == null && s == null) {
            return true;
        }
        else if (root == null) {
            return false;
        }
        else if (s == null) {
            return true;
        }

        if (root.val == s.val) {
            return isSubtree(root.left, s.left) && isSubtree(root.right, s.right);
        }
        else {
            return isSubtree(root.left, s) || isSubtree(root.right, s);
        }
    }
}
