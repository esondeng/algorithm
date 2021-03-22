package com.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * 下一个排列
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * *
 * * 1,2,3 → 1,3,2
 * * 3,2,1 → 1,2,3
 * * 1,1,5 → 1,5,1
 *
 * @author dengxiaolin
 * @since 2020/11/26
 */
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation s = new NextPermutation();
        s.nextPermutation(new int[] {1, 2, 3}).forEach(System.out::println);
    }

    public List<Integer> nextPermutation(int[] m) {
        if (m == null || m.length == 0) {
            return Collections.emptyList();
        }

        List<Integer> list = new ArrayList<>();
        Arrays.stream(m).forEach(list::add);
        list.sort(Comparator.comparingInt(t -> t));

        List<List<Integer>> result = new ArrayList<>();
        boolean[] visit = new boolean[m.length];
        Stack<Integer> stack = new Stack<>();

        buildPermutations(list, visit, stack, result);

        int index = 0;
        for (; index < result.size(); index++) {
            if (find(result.get(index), m)) {
                break;
            }
        }

        return result.get((index + 1) % list.size());

    }

    private void buildPermutations(List<Integer> list, boolean[] visit, Stack<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == list.size()) {
            result.add(new ArrayList<>(stack));
            return;
        }


        for (int i = 0; i < list.size(); i++) {
            if (!visit[i]) {
                stack.push(list.get(i));
                visit[i] = true;
                buildPermutations(list, visit, stack, result);
                stack.pop();
                visit[i] = false;
            }
        }
    }

    private boolean find(List<Integer> list, int[] m) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != m[i]) {
                return false;
            }
        }

        return true;
    }


}
