package com.test.algorithm;

import java.util.Arrays;

/**
 * 无重复字符串的最大子串
 * 思路：双指针法，startIndex -> end
 *
 * @author dengxiaolin
 * @since 2021/04/27
 */
public class LongestWithoutRepeat {
    public static String longest(String s) {
        int[] index = new int[128];
        Arrays.fill(index, -1);

        String longest = "";
        int startIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            if (index[s.charAt(i)] != -1) {
                startIndex = index[s.charAt(i)] + 1;
            }
            index[s.charAt(i)] = i;

            String str = s.substring(startIndex, i + 1);
            if (str.length() > longest.length()) {
                longest = str;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        String s = "abcdecdfgm";
        System.out.println(longest(s));


        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println((int) 'A');
        System.out.println((int) 'Z');
    }
}
