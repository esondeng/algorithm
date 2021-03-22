package com.test.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 * *
 * * Note:
 * * The number of people is less than 1,100.
 * *
 * * Example
 * * Input:
 * * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * *
 * * Output:
 * * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * 思路：使用LinkedList的插入, 自己会插入，其他往后挪
 * 因此：
 * 1. 先排序，先H降序，K升序, 因为先排高的，后面晓得就会不影响自己的排名，直接使用k插入
 * 2. 在按照k插入
 *
 * @author dengxiaolin
 * @since 2020/12/11
 */
public class PeopleSort {
    public static void main(String[] args) {
        List<int[]> list = Arrays.asList(
                new int[] {7, 0},
                new int[] {4, 4},
                new int[] {7, 1},
                new int[] {5, 0},
                new int[] {6, 1},
                new int[] {5, 2}
        );

        sort(list);
    }

    public static List<int[]> sort(List<int[]> list) {
        list.sort(Comparator.<int[]>comparingInt(t -> t[0])
                .reversed()
                .thenComparingInt(t -> t[1]));

        List<int[]> result = new LinkedList<>();
        list.forEach(t -> {
            result.add(t[1], t);
        });

        return result;
    }
}
