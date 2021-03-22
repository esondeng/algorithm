package com.test.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 划分子串，同一个字母只能出现在一个子串里面，拆分成尽可能多的子串
 *
 * @author dengxiaolin
 * @since 2020/10/22
 */
public class PartitionLabel {
    public static void main(String[] args) {
        partitionLabels("ababcbacadefegdehijhklij").forEach(System.out::println);
    }

    public static List<String> partitionLabels(String s) {
        int[] maxIndexArray = buildMaxIndexArray(s);
        List<String> result = new ArrayList<>();

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, maxIndexArray[s.charAt(i)]);
            if (end == i) {
                result.add(s.substring(start, end + 1));
                start = end + 1;
            }
        }

        return result;
    }


    public static List<Integer> partitionLabels2(String s) {
        int[] maxIndexArray = buildMaxIndexArray(s);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int end = findMaxIndex(s, i, maxIndexArray[s.charAt(i)], maxIndexArray);
            result.add(end - i + 1);
            i = end;
        }

        return result;
    }

    private static int[] buildMaxIndexArray(String s) {
        int[] maxIndexArray = new int[128];
        for (int i = 0; i < s.length(); i++) {
            int charInt = s.charAt(i);
            maxIndexArray[charInt] = i;
        }

        return maxIndexArray;
    }

    private static int findMaxIndex(String s, int startIndex, int max, int[] maxIndexArray) {
        int maxIndex = max;

        for (int i = startIndex + 1; i <= max - 1; i++) {
            if (maxIndex < maxIndexArray[s.charAt(i)]) {
                maxIndex = maxIndexArray[s.charAt(i)];
            }
        }

        if (max == maxIndex) {
            return max;
        }
        else {
            return findMaxIndex(s, max, maxIndex, maxIndexArray);
        }
    }

    public static int findLongestSubstr(String s) {
        int[] startIndexArray = new int[128];
        int start = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            start = Math.max(start, startIndexArray[s.charAt(i)]);
            startIndexArray[s.charAt(i)] = i + 1;
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

}
