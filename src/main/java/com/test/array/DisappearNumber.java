package com.test.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组元素1<= m[i] <=n, 找出没有出现的数字, 数组长度为n，元素可能重复
 * 思路：充分利用m[i]和下标的关系，把出现过的标记成负数（为了还能找回来计算下标）
 * 达到 i = m[i] -1的目的
 * <p>
 * 要求时间复杂度0(n);
 *
 * @author dengxiaolin
 * @since 2020/11/19
 */
public class DisappearNumber {
    public static void main(String[] args) {
        findDisappearNumber(new int[] {1, 2, 2}).forEach(System.out::println);
    }

    public static List<Integer> findDisappearNumber(int[] m) {
        List<Integer> result = new ArrayList<>();
        if (m == null || m.length == 0) {
            return result;
        }

        for (int i = 0; i < m.length; i++) {
            int index = Math.abs(m[i]) - 1;
            if (m[index] > 0) {
                m[index] = -m[index];
            }
        }

        for (int i = 0; i < m.length; i++) {
            if (m[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }


    public static List<Integer> findDisappearNumber1(int[] m) {
        List<Integer> result = new ArrayList<>();
        if (m == null || m.length == 0) {
            return result;
        }

        for (int i = 0; i < m.length; i++) {
            int index = m[i] - 1;
            m[index] = m[i];
        }

        for (int i = 0; i < m.length; i++) {
            if (m[i] != i + 1) {
                result.add(i + 1);
            }
        }

        return result;
    }
}
