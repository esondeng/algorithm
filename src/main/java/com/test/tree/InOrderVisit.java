package com.test.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dengxiaolin
 * @since 2020/11/03
 */
public class InOrderVisit {

    public List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root.getLeft() != null) {
            inorder(root.getLeft(), result);
        }

        result.add(root.getVal());

        if (root.getRight() != null) {
            inorder(root.getRight(), result);
        }
    }


    private void inorderNoRecursion(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode current = root;
        while (!stack.isEmpty()) {
            while (current != null && current.getLeft() != null) {
                current = current.getLeft();
                stack.push(current);
            }

            current = stack.pop();
            result.add(current.getVal());

            if (current.getRight() != null) {
                current = current.getRight();
                stack.push(current);
            }
            else {
                current = null;
            }
        }
    }



}
