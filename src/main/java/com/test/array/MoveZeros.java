package com.test.array;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0’s to the end of it
 * while maintaining the relative order of the non-zero elements.
 * For example,
 * given nums = [0, 1, 0, 3, 12],
 * after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * @author dengxiaolin
 * @since 2020/11/19
 */
public class MoveZeros {

    public static void main(String[] args) {
        int[] m = new int[] {1, 0, 2, 0, 3};
        MoveZeros moveZeros = new MoveZeros();
        moveZeros.moveZeros(m);
        Arrays.stream(m).forEach(System.out::println);
    }

    private void moveZeros(int[] m) {
        if (m == null || m.length == 0) {
            return;
        }

        int index = 0;
        for (int i = 0; i < m.length; i++) {
            if (m[i] != 0) {
                m[index++] = m[i];
            }
        }

        while (index < m.length) {
            m[index++] = 0;
        }

    }
}
