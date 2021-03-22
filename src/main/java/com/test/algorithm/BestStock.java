package com.test.algorithm;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * <p>
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * <p>
 * In this case, no transaction is done, i.e. max profit = 0.
 * 思路：当前位置-当前位置的最小值，
 *
 * @author dengxiaolin
 * @since 2020/11/23
 */
public class BestStock {

    public static void main(String[] args) {
        BestStock bestStock = new BestStock();
        System.out.println(bestStock.bestStock(new int[] {
                7, 6, 4, 3, 1
        }));
    }

    public int bestStock(int[] m) {
        if (m == null || m.length < 2) {
            return 0;
        }

        int min = m[0];
        int maxProfit = 0;
        for (int i = 1; i < m.length; i++) {
            min = Math.min(min, m[i]);
            maxProfit = Math.max(m[i] - min, maxProfit);
        }

        return maxProfit;
    }
}
