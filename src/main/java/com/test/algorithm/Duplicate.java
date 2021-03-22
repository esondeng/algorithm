package com.test.algorithm;

/**
 * * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * *
 * * Example 1:
 * * Input: [1,3,4,2,2]
 * * Output: 2
 * *
 * * Example 2:
 * * Input: [3,1,3,4,2]
 * * Output: 3
 * *
 * * Note:
 * * You must not modify the array (assume the array is read only).
 * * You must use only constant, O(1) extra space.
 * * Your runtime complexity should be less than O(n2).
 * * There is only one duplicate number in the array, but it could be repeated more than once.
 * <p>
 * 思路：
 * 1. 充分利用下标
 * 2. 排序再求重复 n * log n
 *
 * @author dengxiaolin
 * @since 2020/12/08
 */
public class Duplicate {

    public static void main(String[] args) {
        Duplicate duplicate = new Duplicate();
        System.out.println(duplicate.findDuplicate(new int[] {1, 3, 4, 2, 2}));
    }

    public int findDuplicate(int[] m) {
        for (int i = 0; i < m.length; i++) {
            while (m[i] != i + 1) {
                if (m[i] == m[m[i] - 1]) {
                    return m[i];
                }
                swap(m, i, m[i] - 1);
            }
        }

        return -1;
    }

    private void swap(int[] m, int i, int j) {
        int temp = m[i];
        m[i] = m[j];
        m[j] = temp;
    }
}
