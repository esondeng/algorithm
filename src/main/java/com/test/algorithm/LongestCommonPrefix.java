package com.test.algorithm;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * @author dengxiaolin
 * @since 2021/04/28
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }


        int i = 0;

        while (true) {
            if (i >= strs[0].length()) {
                break;
            }

            char char1 = strs[0].charAt(i);
            boolean valid = true;
            for (int index = 1; index < strs.length; index++) {
                if (i >= strs[index].length() || (char1 != strs[index].charAt(i))) {
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                break;
            }

            i++;
        }

        return strs[0].substring(0, i);
    }

    public static void main(String[] args) {
        String[] s = new String[] {"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix(s));
    }
}
