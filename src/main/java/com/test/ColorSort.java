package com.test;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * *
 * * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * *
 * * Note: You are not suppose to use the library's sort function for this problem.
 * *
 * * Example:
 * * Input: [2,0,2,1,1,0]
 * * Output: [0,0,1,1,2,2]
 * *
 * * Follow up:
 * * A rather straight forward solution is a two-pass algorithm using counting sort.
 * * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * * Could you come up with a one-pass algorithm using only constant space?
 * <p>
 * 思路：双指针法，+ zeroIndex（0，需要放的位置），j是2只要放的位置
 * 注意: 序号i是否能移动
 *
 * @author dengxiaolin
 * @since 2020/11/30
 */
public class ColorSort {

    public static void main(String[] args) {
        int[] m = new int[] {2, 0, 2, 1, 1, 0};
        ColorSort colorSort = new ColorSort();
        colorSort.sort(m);
    }

    public void sort(int[] m) {
        if (m == null || m.length <= 1) {
            return;
        }

        int zeroIndex = 0;

        int i = 0;
        int j = m.length - 1;

        // 双指针法
        while (i <= j) {
            if (m[i] == 0) {
                swap(m, i, zeroIndex);
                i++;
                zeroIndex++;
            }
            else if (m[i] == 2) {
                swap(m, i, j);
                j--;
            }
            else if (m[i] == 1) {
                swap(m, i, zeroIndex);
                i++;
            }

        }
    }

    private void swap(int[] m, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = m[i];
        m[i] = m[j];
        m[j] = temp;

        Arrays.stream(m).forEach(t -> System.out.print(t + " "));
        System.out.println();
    }
}
