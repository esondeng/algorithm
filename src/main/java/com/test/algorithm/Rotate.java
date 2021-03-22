package com.test.algorithm;

/**
 * You are given an n x n 2D matrix representing an image.
 * *
 * * Rotate the image by 90 degrees (clockwise).
 * *
 * * Note:
 * *
 * * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * *
 * * Example 1:
 * *
 * * Given input matrix =
 * * [
 * *   [1,2,3],
 * *   [4,5,6],
 * *   [7,8,9]
 * * ],
 * *
 * * rotate the input matrix in-place such that it becomes:
 * * [
 * *   [7,4,1],
 * *   [8,5,2],
 * *   [9,6,3]
 * * ]
 * * Example 2:
 * *
 * * Given input matrix =
 * * [
 * *   [ 5, 1, 9,11],
 * *   [ 2, 4, 8,10],
 * *   [13, 3, 6, 7],
 * *   [15,14,12,16]
 * * ],
 * *
 * * rotate the input matrix in-place such that it becomes:
 * * [
 * *   [15,13, 2, 5],
 * *   [14, 3, 4, 1],
 * *   [12, 6, 8, 9],
 * *   [16, 7,10,11]
 * * ]
 * * </pre>
 *
 * @author dengxiaolin
 * @since 2020/11/27
 */
public class Rotate {

    public static void main(String[] args) {
        int[][] m = new int[][] {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}

        };

        Rotate rotate = new Rotate();
        int count = m.length / 2;
        int end = m.length - 1;

        for (int i = 0; i < count; i++) {
            rotate.rotate(m, i, end - i);
        }

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void rotate(int[][] m, int start, int end) {
        int tmp;
        int high = m[start].length - 1;
        for (int i = start; i < end; i++) {
            tmp = m[start][i];
            int second = m[i][high - start];
            int third = m[high - start][high - i];
            int forth = m[high - i][start];

            m[start][i] = forth;
            m[high - i][start] = third;
            m[high - start][high - i] = second;
            m[i][high - start] = tmp;
        }
    }
}
