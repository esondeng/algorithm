package com.test.algorithm;

import java.util.Arrays;

/**
 * @author dengxiaolin
 * @since 2021/05/18
 */
public class CountPrimes {
    /**
     * prime start at 2
     */
    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }

        int count = 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = 2; i * j < n; j++) {
                if (!isPrime[i * j]) {
                    continue;
                }
                else {
                    isPrime[i * j] = false;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }
}
