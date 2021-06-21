package com.test.sort;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1, 5, 1, 1, 6, 4]
 * <p>
 * Output: One possible answer is [1, 4, 1, 5, 1, 6].
 * Example 2:
 * <p>
 * Input: nums = [1, 3, 2, 2, 3, 1]
 * <p>
 * Output: One possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 * <p>
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 * <p>
 * <p>
 * <p>
 * 这道题给了我们一个无序数组，让我们排序成摆动数组，满足nums[0] < nums[1] > nums[2] < nums[3]...，
 * 并给了我们例子。我们可以先给数组排序，然后在做调整。调整的方法是找到数组的中间的数，相当于把有序数组从中间分成两部分，
 * 然后从前半段的末尾取一个，在从后半的末尾去一个，这样保证了第一个数小于第二个数，然后从前半段取倒数第二个，
 * 从后半段取倒数第二个，这保证了第二个数大于第三个数，且第三个数小于第四个数，以此类推直至都取完，参见代码如下
 *
 * @author dengxiaolin
 * @since 2021/06/18
 */
public class WiggleSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort(new int[] {1, 5, 1, 1, 6, 4})));
    }

    /**
     * 时间复杂度o(n*logn), 空间复杂度o(n)
     *
     * @param m
     */
    public static int[] sort(int[] m) {
        Arrays.sort(m);

        int middle = (m.length - 1) / 2;
        int minStart = middle;
        int maxStart = m.length - 1;

        if (m.length % 2 == 1) {
            minStart = middle - 1;
        }

        int[] result = new int[m.length];
        int index = 0;
        for (int i = minStart; i >= 0; i--) {
            result[index * 2] = m[i];
            result[index * 2 + 1] = m[maxStart--];
            index++;
        }

        if (m.length % 2 == 1) {
            result[m.length - 1] = m[middle];
        }


        return result;
    }


}
