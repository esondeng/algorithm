package com.test.algorithm;

/**
 * @author dengxiaolin
 * @since 2020/12/11
 */
public class HanmingDistance {

    public int distance(int x, int y) {
        int tmp = x ^ y;
        int count = 0;

        while (tmp != 0) {
            tmp = tmp & (tmp - 1);
            count++;
        }

        return count;
    }
}
