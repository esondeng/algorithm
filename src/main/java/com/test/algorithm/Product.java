package com.test.algorithm;

import java.util.Arrays;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * *
 * * Example:
 * * Input:  [1,2,3,4]
 * * Output: [24,12,8,6]
 * *
 * * Note: Please solve it without division and in O(n).
 * *
 * * Follow up:
 * * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 * <p>
 * * Thought
 * *      动态规划，从左右两边做动态规划
 * *
 * *      result[i] = L * R；
 * *
 * *      L0 = 1
 * *      Li = 1 * nums[0] * nums[1] * … * nums[i-1]
 * *
 * *      R0 = 1
 * *      Rk = 1 * nums[n-1] * nums[n-2] * … * nums[k+1]
 * *
 * * Challenge
 * *
 * * Algorithm
 * *      1. int[] result = new int[nums.length]；
 * *      2. 从左遍历，result[i] = Li；
 * *      3. 从右遍历，result[i] *= Ri；
 * *      4. return result。
 *
 * @author dengxiaolin
 * @since 2020/12/08
 */
public class Product {

    public static void main(String[] args) {

        Product product = new Product();
        Arrays.stream(product.productExceptSelf(new int[] {1, 2, 3, 4}))
                .forEach(System.out::println);
    }

    public int[] productExceptSelf(int[] m) {
        if (m == null || m.length == 0) {
            return new int[0];
        }

        int[] result = new int[m.length];

        int left = 1;
        for (int i = 0; i < m.length; i++) {
            result[i] = left;
            left *= m[i];
        }


        int right = 1;
        for (int i = m.length - 1; i >= 0; i--) {
            // left * right
            result[i] = result[i] * right;
            right *= m[i];
        }

        return result;
    }

}
