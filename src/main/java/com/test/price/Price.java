package com.test.price;

import java.util.Stack;

/**
 * Lily非常喜欢旅游，经常和他老婆自驾出游。但是Lily是一个非常讲求性价比的程序员，所以每次在外面吃饭的时候都要控制价格。今年国庆Lily和他老婆出游，Lily规定每顿饭的价格上限为n，他老婆想点k个菜，餐厅提供m个菜。
 * 请问，在不超过Lily价格上限的情况下，点k个菜有多少种点法（同一种菜只能点一份）。
 * <p>
 * 输入：
 * 1、第一行为价格上限n，想点菜的个数k，以及菜品个数m
 * 2、接下来m行，每行一个菜品价格
 * 3、以上输入均为正整数
 * 输出：
 * 1、输出为一行，代表点菜的方案数
 * Sample Input:
 * 10 2 4
 * 10
 * 3
 * 5
 * 12
 * Sample Output:
 * 1
 * <p>
 * （Sample说明：因为需要点两个菜，且总价不超过10，所以只能有第二和第三个菜的组合方案。其他方案加起来都会超过10）
 * <p>
 * 思路，
 * 1. 深度优先回溯
 *
 * @author dengxiaolin
 * @since 2020/11/26
 */
public class Price {
    public static void main(String[] args) {
        Price price = new Price();
        int n = 10;
        int k = 2;
        int[] m = new int[] {10, 3, 5, 12};

        System.out.println(price.getKinds(n, k, m));
    }

    int counter;

    public int getKinds(int n, int k, int[] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        getKinds(n, k, m, 0, new Stack<>());
        return counter;
    }


    public void getKinds(int n, int k, int[] m, int startIndex, Stack<Integer> stack) {
        if (stack.size() == k) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += stack.get(i);
            }

            if (sum <= n) {
                counter++;
            }

            return;
        }

        for (int i = startIndex; i < m.length; i++) {
            stack.push(m[i]);
            getKinds(n, k, m, i + 1, stack);
            stack.pop();
        }
    }
}
