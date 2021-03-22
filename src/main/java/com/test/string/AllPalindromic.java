package com.test.string;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * *
 * * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * *
 * * Example 1:
 * *
 * * Input: "abc"
 * * Output: 3
 * * Explanation: Three palindromic strings: "a", "b", "c".
 * *
 * *
 * * Example 2:
 * *
 * * Input: "aaa"
 * * Output: 6
 * * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * 思路：中心扩展法， 对于每一个字符，可能是回文串的中心
 * 1. 回文串长度是奇数
 * 2. 回文串长度是偶数
 *
 * @author dengxiaolin
 * @since 2020/12/14
 */
public class AllPalindromic {

    private static int counter = 0;

    public static void main(String[] args) {
        System.out.println(allPalindromic("abc"));
    }

    public static int allPalindromic(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        for (int i = 0; i < s.length(); i++) {
            // 回文串中心是奇数
            calculate(s, i, i);

            // 回文串中心是偶数
            calculate(s, i, i + 1);
        }

        return counter;
    }

    /**
     * 以left，right为中心
     *
     * @param s
     * @param left
     * @param right
     */
    private static void calculate(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            counter++;
            left--;
            right++;
        }

    }
}
