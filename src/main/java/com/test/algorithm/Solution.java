package com.test.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 求二维数据平衡的行，定义：前面行的总和 == 后面行的总和
 *
 * @author dengxiaolin
 * @since 2020/11/10
 */
public class Solution {

    public List<Integer> getAll(int[][] m) {
        List<Integer> result = new ArrayList<>();
        if (m.length <= 2) {
            return result;
        }

        int[] num = new int[m.length];

        for (int row = 0; row < m.length; row++) {
            int count = 0;
            for (int col = 0; col < m[row].length; col++) {
                count += m[row][col];
            }
            num[row] = count;
        }

        int[] leftArray = new int[num.length];
        int left = 0;
        for (int i = 1; i < num.length; i++) {
            leftArray[i] = left;
            left += num[i];
        }

        int[] rightArray = new int[num.length];
        int right = 0;
        for (int i = num.length - 1; i >= 0; i--) {
            rightArray[i] = right;
            right += num[i];
        }


        for (int i = 2; i < m.length - 1; i++) {
            if (leftArray[i] == rightArray[i]) {
                result.add(i);
            }
        }

        return result;

    }
}
