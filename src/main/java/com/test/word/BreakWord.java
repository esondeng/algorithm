package com.test.word;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * *
 * * Note:
 * *
 * * The same word in the dictionary may be reused multiple times in the segmentation.
 * * You may assume the dictionary does not contain duplicate words.
 * *
 * * Example 1:
 * * Input: s = "leetcode", wordDict = ["leet", "code"]
 * * Output: true
 * * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * *
 * * Example 2:
 * * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * * Output: true
 * * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * *              Note that you are allowed to reuse a dictionary word.
 * *
 * * Example 3:
 * * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * * Output: false
 * <p>
 * 思路：深度优先回溯
 *
 * @author dengxiaolin
 * @since 2020/12/01
 */
public class BreakWord {

    public static void main(String[] args) {
        BreakWord breakWord = new BreakWord();
        System.out.println(breakWord.couldBreak(
                "catsandog",
                0,
                new String[] {"cats", "dog", "sand", "and", "cat"}));
    }


    private boolean couldBreak(String source, int startIndex, String[] words) {
        if (startIndex == source.length()) {
            return true;
        }

        for (int i = 0; i < words.length; i++) {
            int endIndex = startIndex + words[i].length();
            if (endIndex <= source.length()) {
                String subStr = source.substring(startIndex, endIndex);
                if (subStr.equals(words[i])) {
                    if (couldBreak(source, endIndex, words)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
