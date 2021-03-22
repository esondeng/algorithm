package com.test.dynamic;

/**
 * 最大公共子串的长度
 * 动态规划
 * <p>
 * 行：字符串1
 * 列：字符串2
 *
 * @author dengxiaolin
 * @since 2020/11/18
 */
public class Lcs {

    public static void main(String[] args) {
        System.out.println(getLcs("teats", "seas"));
    }

    public static int getLcs(String s1, String s2) {
        int[][] m = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    m[i][j] = m[i - 1][j - 1] + 1;
                }
                else {
                    // 到底谁回退， s1回退或者s2回退
                    m[i][j] = Math.max(m[i - 1][j], m[i][j - 1]);
                }
            }
        }

        return m[s1.length()][s2.length()];
    }
}
