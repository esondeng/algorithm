package com.test.string;

/**
 * 最长回文子串
 * 思路：双指针法
 *
 * @author dengxiaolin
 * @since 2020/11/25
 */
public class LongestPalindromic {
    public static void main(String[] args) {
        LongestPalindromic longestPalindromic = new LongestPalindromic();
        System.out.println(longestPalindromic.longestPalindromic("cbbd"));
    }

    public String longestPalindromic(String s) {
        if (s == null || s.length() <= 1) {
            return null;
        }

        int low = 0;
        int high = s.length() - 1;

        while (low < high) {
            if (isPalindromic(s, low, high)) {
                return s.substring(low, high + 1);
            }
            else if (isPalindromic(s, low + 1, high)) {
                return s.substring(low + 1, high + 1);
            }
            else if (isPalindromic(s, low, high - 1)) {
                return s.substring(low, high);
            }
            low++;
            high--;
        }

        return null;
    }

    private boolean isPalindromic(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            }
            else {
                return false;
            }
        }

        return true;
    }
}
