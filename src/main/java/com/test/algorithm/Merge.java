package com.test.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * *
 * * Example 1:
 * * Input: [[1,3],[2,6],[8,10],[15,18]]
 * * Output: [[1,6],[8,10],[15,18]]
 * * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * *
 * * Example 2:
 * * Input: [[1,4],[4,5]]
 * * Output: [[1,5]]
 * * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * *
 * * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 * *
 * * </pre>
 *
 * @author dengxiaolin
 * @since 2020/11/27
 */
public class Merge {

    public static void main(String[] args) {
        Merge m = new Merge();
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list.add(list1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(6);
        list.add(list2);

        List<Integer> list3 = new ArrayList<>();
        list3.add(8);
        list3.add(10);
        list.add(list3);

        m.merge(list);

        list.forEach(t -> {
            t.forEach(i -> System.out.print(i + " "));
            System.out.println();
        });
    }

    public List<List<Integer>> merge(List<List<Integer>> list) {
        if (list == null) {
            return null;
        }

        list.sort(Comparator.comparingInt(t -> t.get(0)));
        merge(list, 0);

        return list;

    }


    private void merge(List<List<Integer>> list, int startIndex) {
        if (startIndex == list.size() - 1) {
            return;
        }

        while (startIndex <= list.size() - 2) {
            List<Integer> list1 = list.get(startIndex);
            List<Integer> list2 = list.get(startIndex + 1);

            if (list2.get(0) <= list1.get(1)) {
                int max = Math.max(list1.get(1), list2.get(1));
                list1.set(1, max);
                list.remove(list2);
            }
            else {
                startIndex++;
            }
        }
    }
}
