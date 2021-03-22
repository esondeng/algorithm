package com.test.dfs;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * *
 * * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * *
 * * Example:
 * * board =
 * * [
 * *   ['A','B','C','E'],
 * *   ['S','F','C','S'],
 * *   ['A','D','E','E']
 * * ]
 * *
 * * Given word = "ABCCED", return true.
 * * Given word = "SEE", return true.
 * * Given word = "ABCB", return false.
 * * 思路：深度优先回溯， 邻居就是上下左右四个方向
 *
 * @author dengxiaolin
 * @since 2020/11/30
 */
public class WordExist {

    public static void main(String[] args) {
        WordExist wordExist = new WordExist();
        char[][] array = new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(wordExist.exist(array, "ABCCESCC"));
    }

    boolean[][] visit;

    public boolean exist(char[][] m, String target) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return false;
        }

        if (target == null) {
            return false;
        }

        visit = new boolean[m.length][m[0].length];
        char firstChar = target.charAt(0);

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == firstChar) {
                    if (exist(m, target, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    private boolean exist(char[][] m, String target, int i, int j, int index) {
        if (index >= target.length()) {
            return true;
        }

        if (i < 0 || i >= m.length || j < 0 || j >= m[0].length || visit[i][j] || m[i][j] != target.charAt(index)) {
            return false;
        }

        visit[i][j] = true;

        if (exist(m, target, i - 1, j, index + 1)) {
            return true;
        }

        if (exist(m, target, i + 1, j, index + 1)) {
            return true;
        }

        if (exist(m, target, i, j - 1, index + 1)) {
            return true;
        }

        if (exist(m, target, i, j + 1, index + 1)) {
            return true;
        }

        // 回溯
        visit[i][j] = false;

        return false;
    }
}
