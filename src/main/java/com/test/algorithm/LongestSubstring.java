package com.test.algorithm;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "aaabb", k = 3
 * <p>
 * Output:
 * 3
 * <p>
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 * <p>
 * Input:
 * s = "ababbc", k = 2
 * <p>
 * Output:
 * 5
 * <p>
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * 找出字符串中的最长子字符串，满足该子字符串中任何字符出现的次数都大于k。
 * 思路：
 * 1. 这是一个经典的分治法解决的问题，关键在于我们如何将这个问题分解为更小的子问题。
 * 反过来想，这个子字符串一定不包含什么元素呢？当一个元素出现的总次数小于k，那么该元素一定不在子字符串中，只需要将其作为分界点，分别找出左半部分和右半部分的满足条件的最长子字符串。
 * 2.
 *
 * @author dengxiaolin
 * @since 2021/06/24
 */
public class LongestSubstring {

    public int longestSubstring(String s, int k) {
        return longestSubstring(s, k, 0, s.length() - 1);

    }

    /**
     * 分治法
     */
    private int longestSubstring(String s, int k, int left, int right) {
        if (left > right) {
            return 0;
        }

        int[] count = new int[26];
        for (int i = left; i <= right; i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = left; i <= right; i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                int leftLength = longestSubstring(s, k, left, i - 1);
                int rightLength = longestSubstring(s, k, i + 1, right);

                return Math.max(leftLength, rightLength);
            }
        }

        return right - left + 1;
    }

    /**
     * 这道题给了我们一个字符串s和一个正整数k，让求一个最大子字符串并且每个字符必须至少出现k次。
     * 作为 LeetCode 第三次编程比赛的压轴题目，博主再一次没有做出来，虽然难度标识只是 Medium。
     * 后来在网上膜拜学习了大神们的解法，发现当时的没做出来的原因主要是卡在了如何快速的判断某
     * 一个字符串是否所有的字符都已经满足了至少出现k次这个条件，虽然博主也用 HashMap
     * 建立了字符和其出现次数之间的映射，但是如果每一次都要遍历 HashMap 中的所有字符看其出现次数是否大于等于k，
     * 未免有些不高效。而用 mask 就很好的解决了这个问题，由于字母只有 26 个，而整型 mask 有 32 位，
     * 足够用了，每一位代表一个字母，如果为1，表示该字母不够k次，如果为0就表示已经出现了k次，
     * 这种思路真是太聪明了，隐约记得这种用法在之前的题目中也用过，但是博主并不能举一反三( 沮丧脸:( )，
     * 还得继续努力啊。遍历字符串，对于每一个字符，都将其视为起点，然后遍历到末尾，增加 HashMap 中字母的出现次数，
     * 如果其小于k，将 mask 的对应位改为1，如果大于等于k，将 mask 对应位改为0。然后看 mask 是否为0，
     * 是的话就更新 res 结果，然后把当前满足要求的子字符串的起始位置j保存到 max_idx 中，等内层循环结束后，
     * 将外层循环变量i赋值为 max_idx+1，继续循环直至结束，参见代码如下：
     */
    public static int longestSubstring2(String s, int k) {
        int res = 0;
        int i = 0;
        int n = s.length();

        while (i + k <= n) {
            int[] m = new int[26];
            int mask = 0;
            int maxIdx = i;
            for (int j = i; j < n; ++j) {
                int t = s.charAt(j) - 'a';
                ++m[t];
                if (m[t] < k) {
                    mask |= (1 << t);
                }
                else {
                    mask &= (~(1 << t));
                }
                if (mask == 0) {
                    res = Math.max(res, j - i + 1);
                    maxIdx = j;
                }
            }
            i = maxIdx + 1;
        }
        return res;
    }


    /**
     * 滑动窗口算法
     * 虽然上面的方法很机智的使用了 mask 了标记某个子串的字母是否都超过了k，但仍然不是很高效，
     * 因为遍历了所有的子串，使得时间复杂度到达了平方级。来想想如何进行优化，因为题目中限定了字符串中只有字母，
     * 这意味着最多不同的字母数只有 26 个，最后满足题意的子串中的不同字母数一定是在 [1, 26] 的范围，
     * 这样就可以遍历这个范围，每次只找不同字母个数为 cnt，且每个字母至少重复k次的子串，来更新最终结果 res。
     * 这里让 cnt 从1遍历到 26，对于每个 cnt，都新建一个大小为 26 的数组 charCnt 来记录每个字母的出现次数，
     * 使用的思想其实还是滑动窗口 Sliding Window，使用两个变量 start 和 i 来分别标记窗口的左右边界，
     * 当右边界小于n时，进行 while 循环，需要一个变量 valid 来表示当前子串是否满足题意，初始化为 true，
     * 还需要一个变量 uniqueCnt 来记录子串中不同字母的个数。此时若 s[i] 这个字母在 charCnt 中的出现次数为0，
     * 说明遇到新字母了，uniqueCnt 自增1，同时把该字母的映射值加1。此时由于 uniqueCnt 变大了，
     * 有可能会超过之前限定了 cnt，所以这里用一个 while 循环，条件是当 uniqueCnt 大于 cnt ，
     * 此时应该收缩滑动窗口的左边界，那么对应的左边界上的字母的映射值要自减1，若减完后为0了，
     * 则 uniqueCnt 自减1，注意这里一会后加，一会先减的操作，不要搞晕了。当 uniqueCnt 没超过 cnt 的时候，
     * 此时还要看当前窗口中的每个字母的出现次数是否都大于等于k，遇到小于k的字母，则直接 valid 标记为 false 即可。
     * 最终若 valid 还是 true，则表示滑动窗口内的字符串是符合题意的，用其长度来更新结果 res 即可，参见代码如下：
     */
    public static int longestSubstring3(String s, int k) {
        int result = 0;
        int n = s.length();

        for (int cnt = 1; cnt <= 26; ++cnt) {
            int left = 0;
            int right = 0;
            int uniqueCnt = 0;

            int[] charCnt = new int[26];
            while (right < n) {
                boolean valid = true;
                if (charCnt[s.charAt(right++) - 'a']++ == 0) {
                    ++uniqueCnt;
                }
                while (uniqueCnt > cnt) {
                    if (--charCnt[s.charAt(left++) - 'a'] == 0) {
                        --uniqueCnt;
                    }
                }
                for (int j = 0; j < 26; ++j) {
                    if (charCnt[j] > 0 && charCnt[j] < k) {
                        valid = false;
                    }
                }

                if (valid) {
                    result = Math.max(result, right - left);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring2("aaabb", 3));
    }
}
