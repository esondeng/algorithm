package com.test.algorithm;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * <p>
 * Input: s = "aab", p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 * <p>
 * Input: s = "mississippi", p = "mis*is*p*."
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 * <p>
 * 思路：
 * 动态规划：
 * dp[s.length][p].length表示结果
 * i,j 表示开始下标是否相等，因为*表示前面可为0，因此从后往前遍历
 *
 * @author dengxiaolin
 * @since 2021/04/28
 */
public class RegularMatch {

    private static boolean regularMatch(String s, String p) {
        int length1 = s.length();
        int length2 = p.length();

        boolean[][] dp = new boolean[length1 + 1][length2 + 1];
        dp[length1][length2] = true;

        for (int i = length1 - 1; i >= 0; i--) {
            for (int j = length2 - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + 1];
                }
                else {
                    boolean first_match = (i < length1 &&
                            (s.charAt(i) == p.charAt(j) ||
                                    p.charAt(j) == '.'));
                    // 后面一个是'*'
                    if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                        dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j + 1];
                    }
                    else {
                        dp[i][j] = first_match && dp[i + 1][j + 1];
                    }
                }
            }
        }
        print(dp);

        return dp[0][0];
    }

    private static void print(boolean[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(regularMatch("mississippi", "mis*is*p*."));
    }
}
