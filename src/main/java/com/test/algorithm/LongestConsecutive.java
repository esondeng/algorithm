package com.test.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * <p>
 * 思路：1. 快速排序，然后遍历，o(nlogn)
 * 2. 时间复杂度o(n), 利用map天生去重，遍历一遍map即可
 *
 * @author dengxiaolin
 * @since 2021/05/06
 */
public class LongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>(2000);
        for (int num : nums) {
            set.add(num);
        }

        int max = 0;
        for (int num : nums) {
            if (set.contains(num)) {
                int left = num - 1;
                while (set.contains(left)) {
                    set.remove(left);
                    left--;
                }

                int right = num + 1;
                while (set.contains(right)) {
                    set.remove(right);
                    right++;
                }
                set.remove(num);

                max = Math.max(right - left - 1, max);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[] {1, 2, 0, 1}));
    }
}
