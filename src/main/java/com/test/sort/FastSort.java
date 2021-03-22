package com.test.sort;

import java.util.Arrays;

/**
 * 快排
 *
 * @author dengxiaolin
 * @since 2020/12/04
 */
public class FastSort {
    public static void main(String[] args) {
        FastSort sort = new FastSort();
        int[] m = new int[] {3, 2, 1};
        sort.fastSort(m);

        Arrays.stream(m)
                .forEach(System.out::println);

    }

    public void fastSort(int[] m) {
        if (m == null || m.length == 0) {
            return;
        }

        sort(m, 0, m.length - 1);

    }

    private void sort(int[] m, int low, int high) {
        if (low >= high) {
            return;
        }
        int bundle = findBundle(m, low, high);
        sort(m, low, bundle - 1);
        sort(m, bundle + 1, high);
    }

    private int findBundle(int[] m, int low, int high) {
        int key = m[low];
        while (low < high) {
            // 找到第一个比key小的，更新low
            while (low < high && m[high] >= key) {
                high--;
            }

            m[low] = m[high];

            // 找到第一个比key大的，更新high
            while (low < high && m[low] <= key) {
                low++;
            }

            m[high] = m[low];

        }

        m[low] = key;

        return low;
    }
}
