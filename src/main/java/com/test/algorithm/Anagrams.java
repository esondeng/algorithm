package com.test.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * *
 * * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * *
 * * The order of output does not matter.
 * *
 * * Example 1:
 * * Input:
 * * s: "cbaebabacd" p: "abc"
 * * Output:
 * * [0, 6]
 * * Explanation:
 * * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * *
 * * Example 2:
 * * Input:
 * * s: "abab" p: "ab"
 * * Output:
 * * [0, 1, 2]
 * * Explanation:
 * * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * 思路：
 * 1. 暴力法，m * n, 计算p中各个字母的个数，循环
 * 2.
 *
 * @author dengxiaolin
 * @since 2020/12/11
 */
public class Anagrams {

    public static void main(String[] args) {
        anagrams("abab", "ab").forEach(System.out::println);
    }

    public static List<Integer> anagrams(String s, String p) {
        if (s == null || p == null) {
            return null;
        }

        if (s.length() < p.length()) {
            return null;
        }


        List<Integer> result = new ArrayList<>();

        Map<Character, Integer> countMap = buildCountMap(p);
        for (int i = 0; i <= s.length() - p.length(); i++) {
            String tmp = s.substring(i, i + p.length());
            Map<Character, Integer> map = buildCountMap(tmp);

            if (countMap.equals(map)) {
                result.add(i);
            }
        }

        return result;
    }


    private static Map<Character, Integer> buildCountMap(String tmp) {
        Map<Character, Integer> countMap = new HashMap<>(64);
        for (int i = 0; i < tmp.length(); i++) {
            char char1 = tmp.charAt(i);
            countMap.put(char1, countMap.getOrDefault(char1, 0) + 1);
        }

        return countMap;
    }
}
