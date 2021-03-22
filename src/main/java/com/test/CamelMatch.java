package com.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dengxiaolin
 * @since 2020/11/16
 */
public class CamelMatch {
    public static void main(String[] args) {
        CamelMatch camelMatch = new CamelMatch();
        camelMatch.camelMatch(new String[] {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FoBaT")
                .forEach(System.out::println);


    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        return Arrays.stream(queries)
                .map(t -> camelMatch(t, pattern))
                .collect(Collectors.toList());
    }

    private boolean camelMatch(String query, String pattern) {
        int index1 = 0;
        int index2 = 0;

        while (index1 < query.length() && index2 < pattern.length()) {
            char char1 = query.charAt(index1);
            char char2 = pattern.charAt(index2);

            if (isUpperCase(char1)) {
                if (char1 != char2) {
                    return false;
                }

                index1++;
                index2++;
            }
            else if (isLowerCase(char2)) {
                if (char1 != char2) {
                    return false;
                }

                index1++;
                index2++;
            }
            else if (isLowerCase(char1) && isUpperCase(char2)) {
                index1++;
            }
            else {
                return false;
            }
        }

        while (index1 < query.length() && isLowerCase(query.charAt(index1))) {
            index1++;

        }
        return index1 == query.length() && index2 == pattern.length();
    }

    private boolean isUpperCase(char char1) {
        return char1 >= 'A' && char1 <= 'Z';
    }

    private boolean isLowerCase(char char1) {
        return char1 >= 'a' && char1 <= 'z';
    }
}
