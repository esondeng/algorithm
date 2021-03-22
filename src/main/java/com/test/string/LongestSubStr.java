package com.test.string;

import java.util.Arrays;

/**
 * 求没有重复字符的最长子串长度
 * 字符串只有字母
 * <p>
 * 思路：记取当前字符串上次出现的index,用来更新startIndex
 * a - 97
 * z -122
 * A - 65
 * Z - 90
 *
 * @author dengxiaolin
 * @since 2020/11/25
 */
public class LongestSubStr {

    public static void main(String[] args) {
        LongestSubStr longestSubStr = new LongestSubStr();
        System.out.println(longestSubStr.longestSubStr("pwwkew"));
    }

    public int longestSubStr(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] index = new int[128];
        Arrays.fill(index, -1);

        int max = 0;
        int startIndex = -1;

        for (int i = 0; i < s.length(); i++) {
            if (index[s.charAt(i)] != -1) {
                startIndex = index[s.charAt(i)];
            }
            int length = i - startIndex;
            index[s.charAt(i)] = i;

            max = Math.max(length, max);
        }

        return max;
    }
}
