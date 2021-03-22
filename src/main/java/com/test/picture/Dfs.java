package com.test.picture;

import java.util.Stack;

/**
 * 岛屿个数
 * 思路：深度优先遍历
 *
 * @author dengxiaolin
 * @since 2020/11/26
 */
public class Dfs {
    int[][] visit;
    private int count;

    public static void main(String[] args) {
        Dfs solution = new Dfs();

        int[][] m = new int[][] {
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 0, 1}

        };


        solution.find(m);
        System.out.println(solution.count);
    }

    private void find(int[][] m) {
        if (m == null || m.length == 0) {
            return;
        }

        visit = new int[m.length][m[0].length];


        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1 && visit[i][j] == 0) {
                    dfsNotRecursion(m, new Location(i, j), m.length, m[0].length);
                    // 深度优先进行标记后记录次数
                    count++;
                }
            }
        }
    }

    private void dfs(int[][] m, int row, int column, int totalRow, int totalColumn) {
        if (row >= totalRow
                || row < 0
                || column < 0
                || column >= totalColumn
                || m[row][column] == 0
                || visit[row][column] == 1) {
            return;
        }

        visit[row][column] = 1;

        dfs(m, row, column - 1, totalRow, totalColumn);
        dfs(m, row, column + 1, totalRow, totalColumn);
        dfs(m, row - 1, column, totalRow, totalColumn);
        dfs(m, row + 1, column, totalRow, totalColumn);
    }


    private void dfsNotRecursion(int[][] m, Location location, int totalRow, int totalColumn) {
        Stack<Location> stack = new Stack<>();
        stack.push(location);

        while (!stack.isEmpty()) {
            location = stack.pop();
            int row = location.row;
            int column = location.column;
            visit[row][column] = 1;

            if (column > 0 && visit[row][column - 1] == 0 && m[row][column - 1] == 1) {
                stack.push(new Location(row, column - 1));
            }

            if (column < totalColumn - 1 && visit[row][column + 1] == 0 && m[row][column + 1] == 1) {
                stack.push(new Location(row, column + 1));
            }

            if (row > 0 && visit[row - 1][column] == 0 && m[row - 1][column] == 1) {
                stack.push(new Location(row - 1, column));
            }

            if (row < totalRow - 1 && visit[row + 1][column] == 0 && m[row + 1][column] == 1) {
                stack.push(new Location(row + 1, column));
            }

        }
    }
}
