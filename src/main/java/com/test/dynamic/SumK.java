package com.test.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number
 * of continuous subarrays whose sum equals to k.
 * *
 * * Example 1:
 * * Input:nums = [1,1,1], k = 2
 * * Output: 2
 * *
 * * Note:
 * * The length of the array is in range [1, 20,000].
 * * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * <p>
 * 思路：
 * 1. 暴力法,n平方的时间复杂度
 * 2. 动态规划，下面我们来发现规律
 * pre[i] = pre[i−1] + nums[i]， pre[i]表示[0...i]的所有的和
 * 针对pre[j] + k == pre[i], 这个条件就表示 pre[j] 满足条件。
 * 如果有个地方存储pre[j]的个数，用map可以存储，key是pre[j], value是pre[j]对应的
 * 满足条件的连续子序列个数
 *
 * @author dengxiaolin
 * @since 2020/12/11
 */
public class SumK {
    private static int counter;

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[] {3, 0, 0, 3}, 3));
    }


    /**
     * 暴力法
     */
    private static void sumk(int[] m, int k, int startIndex, int currentSum) {
        for (int i = 0; i < m.length; i++) {
            int sum = m[i];
            if (sum == k) {
                counter++;
            }
            for (int j = i + 1; j < m.length; j++) {
                sum += m[j];
                if (sum == k) {
                    counter++;
                }
            }
        }

    }

    /**
     * 动态规划
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>(16);
        mp.put(0, 1);

        int count = 0;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }

        return count;
    }


}
