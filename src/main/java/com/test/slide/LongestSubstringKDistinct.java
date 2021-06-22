package com.test.slide;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2,
 * T is "ece" which its length is 3.
 * 思路：滑动窗口法
 *
 * @author dengxiaolin
 * @since 2021/06/22
 */
public class LongestSubstringKDistinct {
    public int LongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>(16);
        int left = 0;
        int right = 0;
        int max = 0;

        // 滑动窗口, 双层循环; 外循环中右移right,直至满足条件; 满足条件时, 内循环中右移left,直至不满足条件; 然后重新右移right
        while (right < s.length()) {
            char current = s.charAt(right);
            map.put(current, map.getOrDefault(current, 0) + 1);
            if (map.size() <= k) {
                max = Math.max(max, current - left + 1);
            }

            // 内循环中右移left, 缩小滑窗, 注意只能到>k,不能等于
            while (map.size() > k) {
                char ch2 = s.charAt(left);
                map.put(ch2, map.get(ch2) - 1);
                if (map.get(ch2) == 0) {
                    map.remove(ch2);
                }

                left++;
            }

            right++;
        }

        return max;
    }
}
