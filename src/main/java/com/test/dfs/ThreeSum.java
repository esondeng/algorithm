package com.test.dfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * 组合
 * 深度优先遍历，三数之和为0
 *
 * @author dengxiaolin
 * @since 2020/11/26
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        threeSum.threeSum(new int[] {-1, 0, 1, 2, -1, -4}).forEach(t -> {
            t.forEach(i -> System.out.println(i + " "));
            System.out.println();
        });
    }

    public List<List<Integer>> threeSum(int[] m) {
        List<List<Integer>> result = new ArrayList<>();

        if (m == null || m.length < 3) {
            return result;
        }

        calculate(m, 0, new Stack<>(), result);
        return result;
    }

    private void calculate(int[] m, int startIndex, Stack<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == 3) {
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                sum += stack.get(i);
            }

            if (sum == 0) {
                List<Integer> list = new ArrayList<>(stack);
                list.sort(Comparator.comparingInt(t -> t));
                if (!result.contains(list)) {
                    result.add(list);
                }
            }
            return;
        }

        for (int i = startIndex; i < m.length; i++) {
            stack.push(m[i]);
            calculate(m, i + 1, stack, result);
            stack.pop();
        }
    }
}
