package com.test.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * *
 * * Example 1:
 * * Input: nums = [1,1,1,2,2,3], k = 2
 * * Output: [1,2]
 * *
 * * Example 2:
 * * Input: nums = [1], k = 1
 * * Output: [1]
 * *
 * * Note:
 * * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * <p>
 * 思路：
 * 1. 用map记录 [i]的个数
 * 2. 因为个数<= m.length, 因此用数据反向存储，index是个数，p[index]是m的成员
 * 3. 从后往前遍历p，判断p[i]是否在map里面
 * 因此时间复杂符是o(n)
 *
 * @author dengxiaolin
 * @since 2020/12/10
 */
public class TopFrequent {

    public static void main(String[] args) {
        TopFrequent topFrequent = new TopFrequent();
        String array = Arrays.toString(topFrequent.topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2));
        System.out.println(array);
    }

    public int[] topKFrequent(int[] m, int kMax) {
        int[] p = new int[m.length + 1];
        int[] result = new int[kMax];

        Map<Integer, Integer> map = new HashMap<>(16);
        Arrays.stream(m).forEach(t -> map.put(t, map.getOrDefault(t, 0) + 1));

        map.forEach((k, v) -> p[v] = k);
        int count = 0;
        for (int i = m.length; i >= 1; i--) {
            if (p[i] != 0) {
                result[count++] = p[i];
                if (count == kMax) {
                    return result;
                }
            }
        }

        return new int[0];
    }
}
