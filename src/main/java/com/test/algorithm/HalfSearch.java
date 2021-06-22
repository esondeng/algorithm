package com.test.algorithm;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * *
 * * Your algorithm's runtime complexity must be in the order of O(log n).
 * *
 * * If the target is not found in the array, return [-1, -1].
 * *
 * *
 * * Example 1:
 * *
 * * Input: nums = [5,7,7,8,8,10], target = 8
 * * Output: [3,4]
 * * Example 2:
 * *
 * * Input: nums = [5,7,7,8,8,10], target = 6
 * * Output: [-1,-1]
 * 思路：
 * 二分查找发的使用
 *
 * @author dengxiaolin
 * @since 2020/11/26
 */
public class HalfSearch {

    public static void main(String[] args) {
        HalfSearch halfSearch = new HalfSearch();
        Arrays.stream(halfSearch.halfSearch(new int[] {5, 7, 7, 8, 8, 10}, 6))
                .forEach(System.out::println);
    }

    public int[] halfSearch(int[] m, int target) {
        int[] result = new int[] {-1, -1};
        if (m == null) {
            return result;
        }

        int low = 0;
        int high = m.length - 1;

        while (low <= high) {
            int middleIndex = (low + high) / 2;
            if (m[middleIndex] == target) {
                int left = middleIndex;
                int right = middleIndex;

                while (left > 0 && m[left] == target) {
                    left--;
                }
                while (right <= m.length - 1 && m[right] == target) {
                    right++;
                }

                result[0] = left + 1;
                result[1] = right - 1;
                return result;
            }
            else if (m[middleIndex] > target) {
                high = middleIndex - 1;
            }
            else {
                low = middleIndex + 1;
            }
        }

        return result;
    }
}
