package com.test.algorithm;

/**
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * 思路：中心法
 *
 * @author dengxiaolin
 * @since 2021/04/28
 */
public class LongestPalindrome {

    private static String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            // 奇数中心
            int left = i - 1;
            int right = i + 1;

            while (left >= 0 && right <= s.length() - 1) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left--;
                right++;
            }

            if (right - left - 1 > result.length()) {
                result = s.substring(left + 1, right);
            }

            // 偶数中心
            left = i;
            right = left + 1;

            while (left >= 0 && right <= s.length() - 1) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }

                left--;
                right++;
            }

            if (right - left - 1 > result.length()) {
                result = s.substring(left + 1, right);
            }
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
