package com.test.array;

/**
 * Given an integer array, you need to find one continuous subarray
 * that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order, too.
 * *
 * * You need to find the shortest such subarray and output its length.
 * *
 * * Example 1:
 * * Input: [2, 6, 4, 8, 10, 9, 15]
 * * Output: 5
 * * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to
 * make the whole array sorted in ascending order.
 * *
 * * Note:
 * * Then length of the input array is in range [1, 10,000].
 * * The input array may contain duplicates, so ascending order here means <=.
 * 思路：
 * 由于最小值可能在中间，从左往右找第一个m[i] > m[i+1]的i作为start已经不合适
 * 因此扩展办法是：
 * 1. start
 * 从左到右找到一个m[i] > m[i+1]的i，
 * 2. end
 * 从右到左，找到第一个 m[i] < m[i-1]
 * 3. 找到start到end的最大值和最小值
 * 4. 遍历 0到start-1， 找到第一个m[i] > m[start],
 * 5. 遍历 m.length -1 到end+1， 找到第一个m[i] < m[end]
 *
 * @author dengxiaolin
 * @since 2020/12/14
 */
public class UnsortSubArray {
    public static void main(String[] args) {
        System.out.println(unsortSubArray(new int[] {1, 3, 3, 4, 3}));
    }

    public static int unsortSubArray(int[] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        int start = 0;
        int end = 0;

        // 找到第一个i
        for (int i = 0; i < m.length - 1; i++) {
            if (m[i] > m[i + 1]) {
                start = i;
                break;
            }
        }

        for (int i = m.length - 1; i > 1; i--) {
            if (m[i] < m[i - 1]) {
                end = i;
                break;
            }
        }

        int unsortMin = m[start];
        int unSortMax = m[end];

        for (int i = start; i <= end; i++) {
            unsortMin = Math.min(unsortMin, m[i]);
            unSortMax = Math.max(unSortMax, m[i]);
        }

        // 向左扩展i
        for (int i = 0; i <= start - 1; i++) {
            if (m[i] > unsortMin) {
                start = i;
                break;
            }
        }

        // 向右扩展
        for (int i = m.length - 1; i >= end + 1; i--) {
            if (m[i] < unSortMax) {
                end = i;
                break;
            }
        }

        return end - start + 1;
    }
}
