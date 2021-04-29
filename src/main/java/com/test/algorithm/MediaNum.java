package com.test.algorithm;

import java.util.Arrays;

/**
 * 找中位数
 * 思路1：两个array合并
 * 思路2：在两个思路合并的基础上优化，减少空间
 *
 * @author dengxiaolin
 * @since 2021/04/27
 */
public class MediaNum {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                nums[k++] = nums1[i++];
            }
            else {
                nums[k++] = nums2[j++];
            }
        }

        if (i < nums1.length) {
            for (; i < nums1.length; i++) {
                nums[k++] = nums1[i];
            }
        }

        if (j < nums2.length) {
            for (; j < nums2.length; j++) {
                nums[k++] = nums2[j];
            }
        }

        System.out.println(Arrays.toString(nums));
        if (k % 2 == 1) {
            return nums[k / 2];
        }
        else {
            return ((double) (nums[k / 2 - 1] + nums[k / 2])) / 2;
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int k = 0;

        int mediaIndex = (nums1.length + nums2.length) / 2;
        int pre = 0;
        int current = 0;

        while (i < nums1.length && j < nums2.length && k <= mediaIndex) {
            if (nums1[i] <= nums2[j]) {
                pre = current;
                current = nums1[i++];
                k++;
            }
            else {
                pre = current;
                current = nums2[j++];
                k++;
            }
        }

        while (i < nums1.length && k <= mediaIndex) {
            pre = current;
            current = nums1[i++];
            k++;
        }

        while (j < nums2.length && k <= mediaIndex) {
            pre = current;
            current = nums2[j++];
            k++;
        }

        if ((nums1.length + nums2.length) % 2 == 1) {
            return current;
        }
        else {
            return ((double) (pre + current)) / 2;
        }
    }

    public static void main(String[] args) {
        int[] m = new int[] {1, 3};
        int[] n = new int[] {2};

        System.out.println(findMedianSortedArrays2(m, n));
    }
}
