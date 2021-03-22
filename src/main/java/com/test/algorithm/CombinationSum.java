package com.test.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * *
 * * The same repeated number may be chosen from candidates unlimited number of times.
 * *
 * * Note:
 * * All numbers (including target) will be positive integers.
 * * The solution set must not contain duplicate combinations.
 * *
 * * Example 1:
 * * Input: candidates = [2,3,6,7], target = 7,
 * * A solution set is:
 * * [
 * *   [7],
 * *   [2,2,3]
 * * ]
 * *
 * * Example 2:
 * * Input: candidates = [2,3,5], target = 8,
 * * A solution set is:
 * * [
 * *   [2,2,2,2],
 * *   [2,3,3],
 * *   [3,5]
 * * ]
 * 思路：深度优先遍历+回溯
 *
 * @author dengxiaolin
 * @since 2020/11/27
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum s = new CombinationSum();
        s.getCombination(new int[] {2, 3, 5}, 8).forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }

    public List<List<Integer>> getCombination(int[] m, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (m == null || m.length == 2) {
            return result;
        }

        getCombination(m, target, new Stack<>(), result);

        return result;
    }

    private void getCombination(int[] m, int target, Stack<Integer> stack, List<List<Integer>> result) {
        int sum = 0;
        for (int i = 0; i < stack.size(); i++) {
            sum += stack.get(i);
        }

        if (sum >= target) {
            if (sum == target) {
                List<Integer> list = new ArrayList<>(stack);
                list.sort(Comparator.comparingInt(t -> t));

                if (!result.contains(list)) {
                    result.add(list);
                }
            }
            return;
        }

        for (int i = 0; i < m.length; i++) {
            stack.push(m[i]);
            getCombination(m, target, stack, result);
            stack.pop();
        }
    }


}
