package com.test.picture;

/**
 * 求岛屿的边界个数，比如下面的场景，答案是16
 * [0,1,0,0]
 * [1,1,1,0]
 * [0,1,0,0]
 * [1,1,0,0]
 *
 * 思路：扩展边界。为了阻止越界问题
 *
 * @author dengxiaolin
 * @since 2020/11/20
 */
public class Island {
    public static void main(String[] args) {
        Island island = new Island();
        int[][] m = new int[][] {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}

        };

        System.out.println(island.getEdgeCount(m));
    }

    public int getEdgeCount(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        // 防止越界问题
        int[][] newArray = new int[m.length + 2][m[0].length + 2];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                newArray[i + 1][j + 1] = m[i][j];
            }
        }

        int count = 0;
        for (int i = 1; i <= m.length; i++) {
            for (int j = 1; j <= m[0].length; j++) {
                if (newArray[i][j] == 1) {
                    if (newArray[i][j - 1] == 0) {
                        count++;
                    }
                    if (newArray[i - 1][j] == 0) {
                        count++;
                    }
                    if (newArray[i][j + 1] == 0) {
                        count++;
                    }
                    if (newArray[i + 1][j] == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
