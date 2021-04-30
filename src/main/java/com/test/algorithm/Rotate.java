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
 * <p>
 * 思路：寻找中心点确认旋转的范围:
 * int n = matrix.length;
 * int rest = n % 2;
 * int rowlimit = n / 2;
 * int columnlimit = rest == 1 ? n / 2 + 1 : n / 2;
 * 找出旋转规律
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

        rotate(m);

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void rotate(int[][] matrix) {

        if (matrix == null || matrix.length == 1) {
            return;
        }

        int n = matrix.length;
        int rest = n % 2;

        int rowLimit = n / 2;
        int columnLimit = rest == 1 ? n / 2 + 1 : n / 2;

        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < columnLimit; j++) {
                swap(matrix, i, j, n - 1);
            }
        }

    }

    private static void swap(int[][] matrix, int i, int j, int n) {
        int temp = matrix[i][j];

        matrix[i][j] = matrix[n - j][i];
        matrix[n - j][i] = matrix[n - i][n - j];
        matrix[n - i][n - j] = matrix[j][n - i];
        matrix[j][n - i] = temp;
    }
}
