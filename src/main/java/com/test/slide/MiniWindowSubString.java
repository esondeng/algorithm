package com.test.slide;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 * <p>
 * 思路：滑动窗口法,看当前窗口的cnt是否等于t的size
 *
 * @author dengxiaolin
 * @since 2021/06/01
 */
public class MiniWindowSubString {

    public static String miniWindowSubString(String s, String t) {
        Map<Character, Integer> map = new HashMap<>(16);
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int minLen = Integer.MAX_VALUE;
        int cnt = 0;
        int left = 0;
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (map.containsKey(current)) {
                map.put(current, map.get(current) - 1);
                if (map.get(current) >= 0) {
                    cnt++;
                }
            }

            // 删除多余的字符
            while (cnt == t.length()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    result = s.substring(left, i + 1);
                }
                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);
                    // 说明已经不满足t的字符个数, 遍历到不满足为止。
                    if (map.get(leftChar) > 0) {
                        cnt--;
                    }
                }

                left++;
            }
        }
        return result;
    }
}
