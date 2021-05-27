package com.test.tree;

import java.util.Stack;

/**
 * get k th smallest in a BST
 *
 * @author dengxiaolin01
 * @since 2021/05/27
 */
public class KthAtBst {
    private static int count = 0;

    public static int getKth(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode p = root;
        int i = 0;
        while (!stack.isEmpty()) {
            while (p != null && p.left != null) {
                p = p.left;
                stack.push(p);
            }

            p = stack.pop();
            if (i++ == k) {
                return p.val;
            }

            if (p.right != null) {
                p = p.right;
                stack.push(p);
            }
            else {
                p = null;
            }
        }

        return 0;
    }

    public static int getKth2(TreeNode root, int k) {
        if (root.left != null) {
            getKth2(root.left, k);
        }

        if (++count == k) {
            return root.val;
        }
        if (root.right != null) {
            getKth2(root.right, k);
        }

        return 0;
    }


}
