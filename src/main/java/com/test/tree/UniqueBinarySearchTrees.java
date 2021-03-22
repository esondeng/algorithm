package com.test.tree;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * *
 * * Example:
 * * Input: 3
 * * Output: 5
 * *
 * * Explanation:
 * * Given n = 3, there are a total of 5 unique BST's:
 * *
 * *    1         3     3      2      1
 * *     \       /     /      / \      \
 * *      3     2     1      1   3      2
 * *     /     /       \                 \
 * *    2     1         2                 3
 * <p>
 * 思路：卡兰特公式，发现规则，动态规划
 * G(n) = G(0)*G(n-1) + G(1)*G(n-2) + G(2)*G(n-3) + ... + G(n-1)G(0)
 *
 * @author dengxiaolin
 * @since 2020/11/30
 */
public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        UniqueBinarySearchTrees trees = new UniqueBinarySearchTrees();
        System.out.println(trees.getCount(3));
    }

    public int getCount(int n) {
        int[] m = new int[n + 1];
        m[0] = 1;
        m[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                m[i] += m[j] * m[i - j - 1];
            }
        }

        return m[n];
    }
}
