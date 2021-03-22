package com.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 求子集,(组合)
 * 思路：深度优先，+ 回溯
 *
 * @author dengxiaolin
 * @since 2020/10/23
 */
public class SubSet {

    public static void main(String[] args) {
        int[] m = new int[] {1, 2, 3};

        recallSubSet(m).forEach(t -> {
            t.forEach(System.out::print);
            System.out.println();
        });
    }

    private static List<List<Integer>> allSubSet(int[] m) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int i = 0; i < m.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<Integer> subSet = new ArrayList<>(result.get(j));
                subSet.add(m[i]);
                result.add(subSet);
            }
        }

        return result;
    }


    /**
     * 深度优先回溯算法
     *
     * @param m
     * @return
     */
    private static List<List<Integer>> recallSubSet(int[] m) {
        List<List<Integer>> result = new ArrayList<>();
        find(m, 0, new Stack<>(), result);
        return result;
    }


    private static void find(int[] m, int startIndex, Stack<Integer> subSet, List<List<Integer>> result) {
        List<Integer> subSetClone = new ArrayList<>(subSet);
        result.add(subSetClone);

        for (int i = startIndex; i < m.length; i++) {
            subSet.add(m[i]);
            find(m, i + 1, subSet, result);
            // 回溯，删除分支回到根节点
            subSet.pop();
        }
    }


}
