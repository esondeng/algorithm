package com.test.tree;

import java.util.Stack;

/**
 * 中序遍历二叉树的变种，iterator模式
 *
 * @author dengxiaolin
 * @since 2020/10/29
 */
public class BSTIterator {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(7);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(15);
        treeNode.right.left = new TreeNode(9);
        treeNode.right.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(treeNode);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        if (root == null) {
            return;
        }

        stack.push(root);

        while (root != null && root.left != null) {
            root = root.left;
            stack.push(root);
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            TreeNode child = node.right;
            stack.push(child);

            while (child != null && child.left != null) {
                child = child.left;
                stack.push(child);
            }
        }

        return node.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
