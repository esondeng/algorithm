package com.test.algorithm;

import java.util.Arrays;

/**
 * @author dengxiaolin
 * @since 2021/04/27
 */
public class Kms {
    private static int[] next(String s) {
        int[] next = new int[s.length()];
        int k = 0;
        for (int i = 1; i < s.length(); i++) {
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k--;
            }

            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }

            next[i] = k;
        }

        return next;
    }

    public static boolean contains(String s1, String s2) {
        int[] next = next(s2);

        int k = 0;
        for (int i = 0; i < s1.length(); i++) {
            while (k > 0 && s1.charAt(i) != s2.charAt(k)) {
                k = next[k];
            }

            if (s1.charAt(i) == s2.charAt(k)) {
                k++;
            }

            if (k == s2.length()) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "abab";
        System.out.println(Arrays.toString(next(s)));

        System.out.println(contains("cabce", "abab"));
    }
}
