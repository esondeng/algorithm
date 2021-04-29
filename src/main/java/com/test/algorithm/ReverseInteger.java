package com.test.algorithm;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * <p>
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 123
 * Output: 321
 *
 * @author dengxiaolin
 * @since 2021/04/28
 */
public class ReverseInteger {
    private static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;

            int temp = result * 10 + pop;
            // 注意边界
            if (temp / 10 != result) {
                return 0;
            }


            result = temp;
        }

        return result;
    }
}
