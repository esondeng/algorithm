package com.test.tree;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * *
 * * Note:
 * * You may assume that duplicates do not exist in the tree.
 * *
 * * For example, given
 * *
 * * preorder = [3,9,20,15,7]
 * * inorder = [9,3,15,20,7]
 * * Return the following binary tree:
 * *
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 *
 * @author dengxiaolin
 * @since 2020/12/01
 */
public class ConstructTree {

    public static void main(String[] args) {

        ConstructTree constructTree = new ConstructTree();
        TreeNode root = constructTree.constructTree(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7});
        System.out.println(root.val);

    }

    public TreeNode constructTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length != inOrder.length) {
            return null;
        }

        return constructTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    private TreeNode constructTree(int[] preOrder,
                                   int preStart,
                                   int preEnd,
                                   int[] inOrder,
                                   int inStart,
                                   int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preOrder[preStart];
        int indexOfRoot = findIndex(inOrder, rootVal);

        if (indexOfRoot == -1) {
            throw new RuntimeException("in-order cant't find val in pre-order");
        }

        TreeNode root = new TreeNode(rootVal);

        // 递归构建当前根结点的左子树，左子树的结点个数是 idxOfRoot-inStart
        // 先序遍历序列中，当前根结点的左子树的范围是 [preStart+1, preStart+(idxOfRoot-inStart))，根据起始位置
        // 中序遍历序列中，当前根结点的左子树的范围是 [inStart, idxOfRoot-1)
        root.left = constructTree(
                preOrder,
                preStart + 1,
                preStart + (indexOfRoot - inStart),
                inOrder,
                inStart,
                indexOfRoot - 1);

        // 递归构建当前根结点的右子树，右子树的结点个数是 inEnd-idxOfRoot
        // 先序遍历序列中，当前结点的右子树的范围是 [preEnd-(inEnd-idxOfRoot) +1, preEnd)，根据终止位置和偏移量计算范围
        // 中序遍历序列中，当前结点的右子树的范围是 [idxOfRoot+1, inEnd)
        root.right = constructTree(
                preOrder,
                preEnd - (inEnd - indexOfRoot) + 1,
                preEnd,
                inOrder,
                indexOfRoot + 1,
                inEnd);

        return root;
    }

    private int findIndex(int[] inOrder, int val) {
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == val) {
                return i;
            }
        }

        return -1;
    }
}
