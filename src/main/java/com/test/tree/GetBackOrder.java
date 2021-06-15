package com.test.tree;

import java.util.Arrays;

/**
 * 根据前序，中序来获取后序
 * 思路：
 * 特性A，对于前序遍历，第一个肯定是根节点；
 * 特性B，对于后序遍历，最后一个肯定是根节点；
 * 特性C，利用前序或后序遍历，确定根节点，在中序遍历中，根节点的两边就可以分出左子树和右子树；
 * 特性D，对左子树和右子树分别做前面3点的分析和拆分，相当于做递归，我们就可以重建出完整的二叉树；
 *
 * @author dengxiaolin
 * @since 2021/06/15
 */
public class GetBackOrder {
    private static int index = 0;

    public static void main(String[] args) {
        int[] pre = new int[] {1, 2, 3, 4};
        int[] in = new int[] {2, 1, 3, 4};

        int[] back = buildBackOrder(pre, in);
        System.out.println(Arrays.toString(back));
    }

    public static int[] buildBackOrder(int[] pre, int[] in) {
        TreeNode treeNode = buildTreeNode(pre, 0, pre.length - 1, in, 0, in.length - 1);
        int[] result = new int[pre.length];
        backOrder(treeNode, result);
        return result;
    }

    public static TreeNode buildTreeNode(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        TreeNode node = new TreeNode(pre[preStart]);
        int inIndex = getIndex(in, inStart, inEnd, pre[preStart]);
        int leftTreeLen = inIndex - inStart;

        if (inIndex == inStart) {
            node.left = null;
        }
        else {
            node.left = buildTreeNode(pre, preStart + 1, preStart + leftTreeLen, in, inStart, inIndex - 1);
        }

        if (inIndex == inEnd) {
            node.right = null;
        }
        else {
            node.right = buildTreeNode(pre, preStart + leftTreeLen + 1, preEnd, in, inIndex + 1, inEnd);
        }

        return node;
    }

    /**
     * 获取前序节点在中序里面的序号
     */
    private static int getIndex(int[] in, int inStart, int inEnd, int preVal) {
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == preVal) {
                return i;
            }
        }

        return -1;
    }

    private static void backOrder(TreeNode treeNode, int[] result) {
        if (treeNode == null) {
            return;
        }

        backOrder(treeNode.left, result);
        backOrder(treeNode.right, result);

        result[index++] = treeNode.val;
    }
}
