package com.test.slide;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 * <p>
 * 思路：跟最小窗口String比起来区别是，这里需要关注顺序
 * 双指针发
 *
 * @author dengxiaolin
 * @since 2021/06/01
 */
public class MiniWindowSubSequence {
    public static String miniWindow(String s, String t) {
        int i = 0;
        int j = 0;

        int minLen = Integer.MAX_VALUE;
        String result = "";

        while (i < s.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                if (++j == t.length()) {
                    int end = i;

                    // 内循环，缩短窗口
                    while (--j >= 0) {
                        while (s.charAt(i--) != t.charAt(j)) {

                        }
                    }
                    ++i;
                    ++j;
                    if (end - i + 1 < minLen) {
                        result = s.substring(i, end + 1);
                        minLen = result.length();
                    }
                }
            }
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(miniWindow("abcdebdde", "bde"));
    }
}
