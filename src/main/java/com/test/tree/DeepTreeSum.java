package com.test.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 广度遍历获取叶子节点的综合
 *
 * @author dengxiaolin
 * @since 2020/10/22
 */
public class DeepTreeSum {

    public static void main(String[] args) {
        Node root = new Node();
        root.val = 1;
        Node right = new Node();
        right.val = 2;
        root.right = right;

        DeepTreeSum deepTreeSum = new DeepTreeSum();
        System.out.println(deepTreeSum.deepSum(root));
    }

    private int deepSum(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Node> nodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                nodes.add(node);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (queue.isEmpty()) {
                return nodes.stream().mapToInt(Node::getVal).sum();
            }
        }

        return 0;
    }

    private static class Node {
        int val;
        private Node left;
        private Node right;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }


    /**
     * 计算爷爷节点是偶数的所有节点的和
     *
     * @param root
     * @return
     */
    public int sumEvenGrandparent(Node root) {
        int sum = 0;
        if (root.val % 2 == 0) {
            List<Node> nodes = getChildren(root);
            for (int i = 0; i < nodes.size(); i++) {
                sum += nodes.get(i).val;
            }
        }

        if (root.left != null) {
            sum += sumEvenGrandparent(root.left);
        }

        if (root.right != null) {
            sum += sumEvenGrandparent(root.right);
        }

        return sum;
    }

    private List<Node> getChildren(Node root) {
        List<Node> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        addChildren(root.left, result);
        addChildren(root.right, result);

        return result;
    }

    private void addChildren(Node parent, List<Node> nodes) {
        if (parent == null) {
            return;
        }

        if (parent.left != null) {
            nodes.add(parent.left);
        }

        if (parent.right != null) {
            nodes.add(parent.right);
        }
    }
}

