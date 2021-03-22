package com.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 获取指定个数的组合， 深度优先回溯算法
 *
 * @author dengxiaolin
 * @since 2020/10/27
 */
public class Combination {

    public static void main(String[] args) {
        Combination combination = new Combination();
        combination.permute(new int[] {1, 2, 3, 4}).forEach(t -> {
            t.forEach(System.out::print);
            System.out.print(" ");
            System.out.println();
        });


    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        find(n, 1, k, new Stack<>(), result);

        return result;
    }

    private void find(int n, int start, int k, Stack<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i <= n; i++) {
            stack.push(i);
            find(n, i + 1, k, stack, result);
            // 回溯算法
            stack.pop();
        }
    }


    /**
     * 全排列
     *
     * @param m
     * @return
     */
    public List<List<Integer>> combine(int[] m) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visit = new boolean[m.length];

        Stack<Integer> stack = new Stack<>();
        find(m, stack, visit, result);

        return result;
    }


    /**
     * 深度优先回溯，查找
     *
     * @param m
     * @param stack
     * @param visit
     * @param result
     */
    private void find(int[] m, Stack<Integer> stack, boolean[] visit, List<List<Integer>> result) {
        if (stack.size() == m.length) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < m.length; i++) {
            if (!visit[i]) {
                stack.push(m[i]);
                visit[i] = true;

                find(m, stack, visit, result);
                stack.pop();
                visit[i] = false;
            }
        }
    }


    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }

        boolean[] visit = new boolean[nums.length];
        premute(nums, visit, new Stack<>(), result);

        return result;
    }

    private void premute(int[] nums, boolean[] visit, Stack<Integer> stack, List<List<Integer>> result) {

        if (stack.size() == nums.length) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visit[i]) {
                stack.push(nums[i]);
                visit[i] = true;
                premute(nums, visit, stack, result);
                stack.pop();
                visit[i] = false;
            }
        }
    }


}
