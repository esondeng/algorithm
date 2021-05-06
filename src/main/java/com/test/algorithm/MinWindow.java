package com.test.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

/**
 * Minimum Window Substring
 * Hard
 * <p>
 * 6492
 * <p>
 * 437
 * <p>
 * Add to List
 * <p>
 * Share
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t. If there is no such window in s that covers all characters in t, return the empty string "".
 * <p>
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * <p>
 * 思路：1. 暴力法
 * 2. 滑动窗口法
 * 3. 优化滑动窗口
 * Algorithm
 * <p>
 * We start with two pointers, leftleft and rightright initially pointing to the first element of the string S.
 * We use the rightright pointer to expand the window until we get a desirable window i.e. a window that contains all of the characters of TT.
 * Once we have a window with all the characters, we can move the left pointer ahead one by one. If the window is still a desirable one we keep on updating the minimum window size.
 * If the window is not desirable any more, we repeat step \; 2step2 onwards.
 *
 * @author dengxiaolin
 * @since 2021/04/30
 */
public class MinWindow {

    public static String minWindow(String s, String t) {
        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = buildMap(t);

        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();

        // Left and Right pointer
        int l = 0, r = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<>(16);

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c) < dictT.get(c)) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done contracting. 一个字母一个字母来
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);

    }


    private static Map<Character, Integer> buildMap(String s) {
        Map<Character, Integer> map = new HashMap<>(16);
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        return map;
    }


    public static String minWindow2(String s, String t) {
        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = buildMap(t);
        List<Pair<Integer, Character>> filterPairs = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (dictT.containsKey(s.charAt(i))) {
                filterPairs.add(new Pair<>(i, s.charAt(i)));
            }
        }

        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();


        int l = 0;
        int r = 0;

        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<>(16);

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < filterPairs.size()) {
            // Add one character from the right to the window
            char c = filterPairs.get(r).getValue();
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = filterPairs.get(l).getValue();
                int start = filterPairs.get(l).getKey();
                int end = filterPairs.get(r).getKey();

                // Save the smallest window until now.
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c) < dictT.get(c)) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done contracting.
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);

    }


    public static void main(String[] args) {
        System.out.println(minWindow("acbdbaab", "aabd"));
    }
}
