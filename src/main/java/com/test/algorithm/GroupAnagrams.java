package com.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of strings, group anagrams together.
 * *
 * * Example:
 * *
 * * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * * Output:
 * * [
 * *   ["ate","eat","tea"],
 * *   ["nat","tan"],
 * *   ["bat"]
 * * ]
 * *
 * * Note:
 * *
 * * All inputs will be in lowercase.
 * * The order of your output does not matter.
 * * </pre>
 *
 * @author dengxiaolin
 * @since 2020/11/27
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams g = new GroupAnagrams();
        g.group(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}).forEach(t -> {
            t.forEach(str -> System.out.print(str + " "));
            System.out.println();
        });
    }

    public List<List<String>> group(String[] m) {
        List<List<String>> result = new ArrayList<>();
        if (m == null) {
            return result;
        }

        int[] count = new int[26];
        Arrays.stream(m).collect(Collectors.groupingBy(t -> {
            Arrays.fill(count, 0);
            for (int i = 0; i < t.length(); i++) {
                count[t.charAt(i) - 'a']++;
            }

            StringBuilder keyBuilder = new StringBuilder();

            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    keyBuilder.append((char) (i + 'a'));
                }
            }
            return keyBuilder.toString();
        })).forEach((k, v) -> result.add(v));

        return result;
    }


}
