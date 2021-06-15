package com.test.algorithm;

import java.util.Arrays;

/**
 * @author dengxiaolin
 * @since 2021/06/10
 */
public class Sort {

    public static void main(String[] args) {
        int[] m = new int[] {4, 2, 5, 6};
        quickSort(m);
        System.out.println(Arrays.toString(m));
    }

    public static void quickSort(int[] m) {
        if (m == null || m.length < 2) {
            return;
        }

        quickSort1(m, 0, m.length - 1);

    }

    private static void quickSort1(int[] m, int left, int right) {
        if (left >= right) {
            return;
        }

        int bundery = partition(m, left, right);
        quickSort1(m, left, bundery - 1);
        quickSort1(m, bundery + 1, right);


    }

    private static int partition(int[] m, int left, int right) {
        int key = m[left];
        while (left < right) {
            while (left < right && m[right] >= key) {
                right--;
            }

            m[left] = m[right];
            while (left < right && m[left] <= key) {
                left++;
            }
            m[right] = m[left];
        }

        m[left] = key;
        return left;
    }
}
