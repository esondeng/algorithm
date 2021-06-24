package com.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * [LeetCode] First Unique Character in a String 字符串第一个不同字符
 * <p>
 * <p>
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * <p>
 * Examples:
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 * <p>
 * 思路：用map建立char和次数，再遍历string
 *
 * @author dengxiaolin
 * @since 2021/06/24
 */
public class FirstUniqueCharacter {
    public int findFirstUniqueIndex(String s) {
        Map<Character, Integer> map = new HashMap<>(16);
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}
