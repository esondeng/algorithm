package com.test.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * You are given a binary tree in which each node contains an integer value.
 * *
 * * Find the number of paths that sum to a given value.
 * *
 * * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * *
 * * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * *
 * * Example:
 * *
 * * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * *
 * *       10
 * *      /  \
 * *     5   -3
 * *    / \    \
 * *   3   2   11
 * *  / \   \
 * * 3  -2   1
 * *
 * * Return 3. The paths that sum to 8 are:
 * *
 * * 1.  5 -> 3
 * * 2.  5 -> 2 -> 1
 * * 3. -3 -> 11
 * <p>
 * 思路1：因为只能往下遍历，对于当前节点来说，要么包含，要么不包含，递归
 * 思路2：包含当前节点的个数 + left节点满足条件的个数 + right节点满足条件的个数
 *
 * @author dengxiaolin
 * @since 2020/12/11
 */
public class Sum {

    public static void main(String[] args) {


        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);

        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);


        List<List<TreeNode>> result = new ArrayList<>();
        findPath(root, 8, new Stack<>(), result);

        result.forEach(list -> {
            list.forEach(t -> System.out.print(t.val + " "));
            System.out.println();
        });


    }

    public static void findPath(TreeNode root,
                                int sum,
                                Stack<TreeNode> stack,
                                List<List<TreeNode>> result) {
        if (root == null) {
            return;
        }

        stack.push(root);

        if (!stack.isEmpty()) {
            int total = stack.stream().mapToInt(t -> t.val).sum();
            if (total == sum) {
                result.add(new ArrayList<>(stack));
            }
        }

        findPath(root.left, sum, stack, result);
        findPath(root.right, sum, stack, result);
        stack.pop();

        // 不包含root, 重新开stack， 重新计算
        findPath(root.left, sum, new Stack<>(), result);
        findPath(root.right, sum, new Stack<>(), result);
    }


    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathNum(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathNum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        sum -= root.val;
        int num = sum == 0 ? 1 : 0;
        return num + pathNum(root.left, sum) + pathNum(root.right, sum);
    }
}
