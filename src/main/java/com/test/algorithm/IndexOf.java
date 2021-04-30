package com.test.algorithm;

/**
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Example 3:
 * <p>
 * Input: haystack = "", needle = ""
 * Output: 0
 * <p>
 * 思路：
 * 1. kms 模式串回退
 * 2. 匹配串回退
 *
 * @author dengxiaolin
 * @since 2021/04/29
 */
public class IndexOf {

    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.equals("")) {
            return 0;
        }

        int i = 0;
        int j = 0;

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                if (j == needle.length()) {
                    return i - j;
                }
            }
            else {
                i++;
                i = i - j;
                j = 0;
            }
        }


        return -1;
    }

    private static int indexOf(String s, String p) {
        if (p == null || p.equals("")) {
            return 0;
        }

        int[] next = next(p);

        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            while (k > 0 && s.charAt(i) != p.charAt(k)) {
                // 找前面一个已经比对过相等的
                k = next[k - 1];
            }

            if (s.charAt(i) == p.charAt(k)) {
                k++;
            }

            if (k == p.length()) {
                return i + 1 - p.length();
            }
        }

        return -1;

    }

    public static int[] next(String s) {
        int[] next = new int[s.length()];
        // 第一个前缀和后缀为空，值就是0
        next[0] = 0;

        for (int i = 1, k = 0; i < s.length(); i++) {
            // 不相等的情况下，往前回溯查看当前字符适合和k-1的next相等
            while (k > 0 && s.charAt(i) != s.charAt(k)) {
                k = next[k - 1];
            }

            // 相等则k+1
            if (s.charAt(i) == s.charAt(k)) {
                k++;
            }
            // 找到k，赋值
            next[i] = k;
        }

        return next;
    }

    public static void main(String[] args) {
        System.out.println(indexOf("adcadcaddcadde", "adcadde"));
    }
}
