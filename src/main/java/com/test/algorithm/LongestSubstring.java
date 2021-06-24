package com.test.algorithm;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "aaabb", k = 3
 * <p>
 * Output:
 * 3
 * <p>
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 * <p>
 * Input:
 * s = "ababbc", k = 2
 * <p>
 * Output:
 * 5
 * <p>
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * 找出字符串中的最长子字符串，满足该子字符串中任何字符出现的次数都大于k。
 * 思路：
 * 1。这是一个经典的分治法解决的问题，关键在于我们如何将这个问题分解为更小的子问题。
 * 反过来想，这个子字符串一定不包含什么元素呢？当一个元素出现的总次数小于k，那么该元素一定不在子字符串中，只需要将其作为分界点，分别找出左半部分和右半部分的满足条件的最长子字符串。
 *
 * @author dengxiaolin
 * @since 2021/06/24
 */
public class LongestSubstring {

    public int longestSubstring(String s, int k) {
        return longestSubstring(s, k, 0, s.length() - 1);

    }

    private int longestSubstring(String s, int k, int left, int right) {
        if (left > right) {
            return 0;
        }

        int[] count = new int[26];
        for (int i = left; i <= right; i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = left; i <= right; i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                int leftLength = longestSubstring(s, k, left, i - 1);
                int rightLength = longestSubstring(s, k, i + 1, right);

                return Math.max(leftLength, rightLength);
            }
        }

        return right - left + 1;
    }
}
