package com.test.sort;

/**
 * 找出未排序的子序列长度
 *
 * @author dengxiaolin
 * @since 2020/11/18
 */
public class Sort {

    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray(new int[] {1, 2, 3}));
    }

    public static int findUnsortedSubarray(int[] m) {

        int start = findStart(m);
        int end = findEnd(m);

        int count = end - start + 1;
        return count > 1 ? count : 0;
    }


    private static int findStart(int[] m) {
        // 存在后面的比自己小
        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length - 1; j++) {
                if (m[j] < m[i]) {
                    return i;
                }
            }
        }

        return 0;
    }

    private static int findEnd(int[] m) {
        // 存在前面的比自己大
        for (int i = m.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (m[j] > m[i]) {
                    return i;
                }
            }
        }
        return 0;
    }
}
