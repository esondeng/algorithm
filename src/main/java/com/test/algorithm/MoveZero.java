package com.test.algorithm;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * *
 * * Example:
 * * Input: [0,1,0,3,12]
 * * Output: [1,3,12,0,0]
 * *
 * * Note:
 * * You must do this in-place without making a copy of the array.
 * * Minimize the total number of operations.
 *
 * @author dengxiaolin
 * @since 2020/12/08
 */
public class MoveZero {

    public static void main(String[] args) {
        int[] m = new int[] {0, 1, 0, 3, 12};
        MoveZero moveZero = new MoveZero();
        moveZero.moveZero(m);

        Arrays.stream(m).forEach(System.out::println);
    }

    public void moveZero(int[] m) {
        int zeroIndex = 0;
        for (int i = 0; i < m.length; i++) {
            if (m[i] != 0) {
                m[zeroIndex++] = m[i];
            }
        }

        for (int i = zeroIndex; i < m.length; i++) {
            m[i] = 0;
        }
    }
}
