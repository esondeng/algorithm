package com.test.picture;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * directed graph:
 * input + queue
 *
 * @author dengxiaolin
 * @since 2021/05/18
 */
public class DirectedGraph {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] countIn = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            countIn[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (countIn[i] == 0) {
                queue.offer(i);
            }
        }

        int total = 0;
        while (!queue.isEmpty()) {
            Integer num = queue.poll();
            total++;

            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == num) {
                    countIn[prerequisites[i][0]]--;
                    if (countIn[prerequisites[i][0]] == 0) {
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }

        return total == numCourses;
    }

    public static void main(String[] args) {
        int[][] m = new int[1][2];
        m[0][0] = 1;
        m[0][1] = 0;

        System.out.println(canFinish(2, m));

    }
}
