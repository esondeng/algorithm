package com.test.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Example 1:
 * <p>
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * 思路：排序问题
 *
 * @author dengxiaolin
 * @since 2021/05/06
 */
public class LargestNumber {
    public static String largestNumber(List<Integer> nums) {
        nums.sort((Integer o1, Integer o2) -> (o2.toString() + o1.toString()).compareTo(o1.toString() + o2.toString()));

        StringBuilder sb = new StringBuilder();
        nums.forEach(sb::append);

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(largestNumber(Arrays.asList(3, 30, 34, 5, 9)));
    }
}
