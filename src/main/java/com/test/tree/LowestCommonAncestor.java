package com.test.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * *
 * * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * *
 * * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * *
 * *                3
 * *              /  \
 * *            5     1
 * *          / \    / \
 * *         6   2  0  8
 * *            / |
 * *           7  4
 * *
 * * Example 1:
 * * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * * Output: 3
 * * Explanation: The LCA of nodes 5 and 1 is 3.
 * *
 * * Example 2:
 * * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * * Output: 5
 * * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * *
 * * Note:
 * * All of the nodes' values will be unique.
 * * p and q are different and both values will exist in the binary tree.
 * <p>
 * 思路：
 * 1).看输入节点是否都在左子树或者右子树
 * 2).得到从根节点触发，结束是p,q的两条path，弹栈到第一个不相等的节点
 *
 * @author dengxiaolin
 * @since 2020/12/07
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.left = new TreeNode(8);

        LowestCommonAncestor s = new LowestCommonAncestor();

        System.out.println(s.lowestCommonAncestor1(root, 5, 4).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }

        if (root.val == p || root.val == q) {
            return root;
        }

        boolean isInLeftNode = isInTree(root.left, p) && isInTree(root.left, q);
        if (isInLeftNode) {
            return lowestCommonAncestor(root.left, p, q);
        }

        boolean isInRightNode = isInTree(root.right, p) && isInTree(root.right, q);
        if (isInRightNode) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;

    }

    private boolean isInTree(TreeNode root, int p) {
        if (root == null) {
            return false;
        }

        if (root.val == p) {
            return true;
        }

        return isInTree(root.left, p) || isInTree(root.right, p);
    }


    public TreeNode lowestCommonAncestor1(TreeNode root, int p, int q) {
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        fillPath(root, p, list1);
        fillPath(root, q, list2);


        TreeNode preNode = root;
        for (int i = 0; i < list1.size() && i < list2.size(); i++) {
            if (list1.get(i) == list2.get(i)) {
                preNode = list1.get(i);
            }
            else {
                break;
            }
        }

        return preNode;

    }


    /**
     * 获取根节点到当前节点的路径
     * 思路：深度优先 + 回溯
     *
     * @param root
     * @param p
     * @param list
     * @return
     */
    private boolean fillPath(TreeNode root, int p, List<TreeNode> list) {
        if (root == null) {
            return false;
        }

        list.add(root);
        if (root.val == p) {
            return true;
        }

        boolean leftFind = fillPath(root.left, p, list);


        if (leftFind) {
            return true;
        }

        boolean rightFind = fillPath(root.right, p, list);
        if (rightFind) {
            return true;
        }

        // 左右都找不到，就回退
        list.remove(list.size() - 1);
        return false;
    }

}
