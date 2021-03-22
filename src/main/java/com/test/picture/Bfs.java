package com.test.picture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 广度优先遍历，求连续1的个数，比如下面的场景，结果是3
 * 1, 0, 0, 1
 * 1, 1, 0, 1
 * 1, 1, 0, 0
 * 0, 1, 0, 1
 *
 * @author dengxiaolin
 * @since 2020/11/20
 */
public class Bfs {
    private int count;
    private int[][] visit;

    public static void main(String[] args) {
        Bfs solution = new Bfs();

        int[][] m = new int[][] {
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 0, 1}

        };

        solution.find(m);
        System.out.println(solution.count);
    }

    public void find(int[][] m) {
        if (m == null || m.length == 0) {
            return;
        }

        visit = new int[m.length][m[0].length];


        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1 && visit[i][j] == 0) {
                    bfs(m, Arrays.asList(new Location(i, j)), m.length, m[0].length);
                    // 广度优先进行标记后记录次数
                    count++;
                }
            }
        }
    }

    /**
     * 广度优先进行标记, 非递归
     *
     * @param m
     * @param row
     * @param column
     * @param totalRow
     * @param totalColumn
     */
    private void bfsNotRecursion(int[][] m, int row, int column, int totalRow, int totalColumn) {
        visit[row][column] = 1;
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(row, column));

        // 寻找自己能访问的节点，广度优先
        int size = queue.size();
        while (!queue.isEmpty()) {
            for (int i = 0; i < size; i++) {
                Location index = queue.poll();
                if (index.row > 0 && m[index.row - 1][index.column] == 1 && visit[index.row - 1][index.column] == 0) {
                    queue.offer(new Location(index.row - 1, index.column));
                    visit[index.row - 1][index.column] = 1;
                }

                if (index.column > 0 && m[index.row][index.column - 1] == 1 && visit[index.row][index.column - 1] == 0) {
                    queue.offer(new Location(index.row, index.column - 1));
                    visit[index.row][index.column - 1] = 1;
                }

                if (index.column < totalColumn - 1 && m[index.row][index.column + 1] == 1 && visit[index.row][index.column + 1] == 0) {
                    queue.offer(new Location(index.row, index.column + 1));
                    visit[index.row][index.column + 1] = 1;
                }

                if (index.row < totalRow - 1 && m[index.row + 1][index.column] == 1 && visit[index.row + 1][index.column] == 0) {
                    queue.offer(new Location(index.row + 1, index.column));
                    visit[index.row + 1][index.column] = 1;
                }
            }
        }
    }
    


    private void bfs(int[][] m, List<Location> indexList, int totalRow, int totalColumn) {
        if (indexList.isEmpty()) {
            return;
        }

        List<Location> nextLevel = new ArrayList<>();
        indexList.forEach(index -> {
            visit[index.row][index.column] = 1;

            if (index.row > 0 && m[index.row - 1][index.column] == 1 && visit[index.row - 1][index.column] == 0) {
                nextLevel.add(new Location(index.row - 1, index.column));
            }

            if (index.column > 0 && m[index.row][index.column - 1] == 1 && visit[index.row][index.column - 1] == 0) {
                nextLevel.add(new Location(index.row, index.column - 1));
            }

            if (index.column < totalColumn - 1 && m[index.row][index.column + 1] == 1 && visit[index.row][index.column + 1] == 0) {
                nextLevel.add(new Location(index.row, index.column + 1));
            }

            if (index.row < totalRow - 1 && m[index.row + 1][index.column] == 1 && visit[index.row + 1][index.column] == 0) {
                nextLevel.add(new Location(index.row + 1, index.column));
            }
        });

        bfs(m, nextLevel, totalRow, totalColumn);
    }

}
