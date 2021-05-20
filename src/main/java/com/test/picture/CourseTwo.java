package com.test.picture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites,
 * for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1] .
 * <p>
 * 思路：有向图的遍历：
 * 1. 先找入度为0，+ 队列
 *
 * @author dengxiaolin
 * @since 2021/05/20
 */
public class CourseTwo {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>(numCourses * 2);
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            map.computeIfAbsent(prerequisites[i][1], ArrayList::new)
                    .add(prerequisites[i][0]);
        }


        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int count = 0;

        while (!queue.isEmpty()) {
            Integer num = queue.poll();
            result[count++] = num;

            if (map.containsKey(num)) {
                map.get(num).forEach(course -> {
                    inDegree[course]--;
                    if (inDegree[course] == 0) {
                        queue.offer(course);
                    }
                });
            }
        }

        return count == numCourses ? result : new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(4, new int[][] {
                {1, 0},
                {2, 3},
                {1, 3}
        })));
    }
}
