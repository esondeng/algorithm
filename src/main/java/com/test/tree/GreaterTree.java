package com.test.tree;

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every val of the original BST is changed to the original val plus sum of all keys greater than the original val in BST.
 * *
 * * Example:
 * *
 * * Input: The root of a Binary Search Tree like this:
 * *               5
 * *             /   \
 * *            2     13
 * *
 * * Output: The root of a Greater Tree like this:
 * *              18
 * *             /   \
 * *           20     13
 * 思路：从题目中可以看到从右子树累加
 * 因此，一条道走到黑的dfs上场了
 *
 * @author dengxiaolin
 * @since 2020/12/11
 */
public class GreaterTree {
    int sum = 0;

    public void greaterTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        // 到最右子树
        greaterTree(treeNode.right);

        treeNode.val += sum;
        sum += treeNode.val;

        greaterTree(treeNode.left);
    }

}
