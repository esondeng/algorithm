package com.test.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * 思路：dfs
 * 思路2：动态规划, 转移函数：dp[i] = dp[j] == true && s.subString(j, i) in word list, 其中j >=0 且 <= i
 *
 * @author dengxiaolin
 * @since 2021/05/06
 */
public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (wordBreak(s.substring(word.length()), wordDict)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }


    public static void main(String[] args) {
        System.out.println(wordBreak2("leetcode", Arrays.asList("leet", "code")));
    }
}
