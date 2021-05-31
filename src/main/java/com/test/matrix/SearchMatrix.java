package com.test.matrix;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * *
 * * Integers in each row are sorted in ascending from left to right.
 * * Integers in each column are sorted in ascending from top to bottom.
 * *
 * * Example:
 * * Consider the following matrix:
 * * [
 * *   [1,   4,  7, 11, 15],
 * *   [2,   5,  8, 12, 19],
 * *   [3,   6,  9, 16, 22],
 * *   [10, 13, 14, 17, 24],
 * *   [18, 21, 23, 26, 30]
 * * ]
 * * Given target = 5, return true.
 * * Given target = 20, return false.
 * <p>
 * 思路：行列指针法, 充分利用排序特性, 右上角开始遍历
 * i =0， j = m[0].length -1， 这个点才是精髓
 * while(i < m.length && j < m[0].length){
 * if(m[i][j] < p){
 * j --;
 * }
 * else if(m[i][j] == p){
 * return true;
 * }
 * else{
 * i++;
 * }
 * }
 *
 * @author dengxiaolin
 * @since 2020/12/08
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] m = new int[][] {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {18, 21, 23, 26, 30}

        };

        SearchMatrix matrix = new SearchMatrix();
        System.out.println(matrix.searchMatrix(m, 20));
    }

    public boolean searchMatrix(int[][] m, int p) {
        int i = 0;
        int j = m[0].length - 1;

        while (i < m.length && j >= 0) {
            if (m[i][j] < p) {
                i++;
            }
            else if (m[i][j] == p) {
                return true;
            }
            else {
                j--;
            }
        }

        return false;
    }
}
