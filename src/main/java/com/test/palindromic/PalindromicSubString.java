package com.test.palindromic;

/**
 * 获取回文子串的个数
 * 思路：
 * 1.暴力法
 * 2.中心法， 当前char是中心，考虑奇数和偶数
 *
 * @author dengxiaolin
 * @since 2020/11/18
 */
public class PalindromicSubString {
    public static void main(String[] args) {
        System.out.println(palindromic1("abba"));
    }

    public static int palindromic(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() + 1; j++) {
                if (isPalindromic(s.substring(i, j))) {
                    count++;
                }
            }
        }

        return count;
    }


    private static boolean isPalindromic(String s) {
        if (s.length() == 1) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }


    public static int palindromic1(String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            counter += getPalindromicCount(s, i);
        }

        return counter;
    }

    private static int getPalindromicCount(String s, int index) {
        int left = index - 1;
        int right = index + 1;

        // 一个char也是回文串
        int count = 1;

        // 奇数中心
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left--) == s.charAt(right++)) {
                count++;
            }
        }

        // 偶数中心
        left = index;
        right = index + 1;

        while (left >= 0 && right < s.length()) {
            if (s.charAt(left--) == s.charAt(right++)) {
                count++;
            }
        }

        return count;
    }


}
