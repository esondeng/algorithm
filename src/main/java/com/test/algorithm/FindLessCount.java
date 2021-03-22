package com.test.algorithm;

import java.util.Arrays;

/**
 * 获取比index所在数字小的个数
 *
 * @author dengxiaolin
 * @since 2020/10/26
 */
public class FindLessCount {
    public static void main(String[] args) {
        int[] nums = new int[] {2, 1, 1, 3};
        Arrays.stream(smallerNumbersThanCurrent(nums))
                .forEach(System.out::println);

    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null) {
            return null;
        }

        int[] result = new int[nums.length];
        if (nums.length == 0 || nums.length == 1) {
            return result;
        }

        int min = result[0];
        int max = result[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        int[] count = new int[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - min]++;
        }

        // 计算小于等于自己的个数
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        // 填充没有的数字
        for (int i = 0; i < nums.length; i++) {
            int countIndex = nums[i] - min;
            result[i] = countIndex == 0 ? 0 : count[countIndex - 1];
        }

        return result;
    }
}
