package com.test.picture;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * *
 * * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * *
 * * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * *
 * * Example 1:
 * * Input: 2, [[1,0]]
 * * Output: true
 * * Explanation: There are a total of 2 courses to take.
 * *              To take course 1 you should have finished course 0. So it is possible.
 * *
 * * Example 2:
 * * Input: 2, [[1,0],[0,1]]
 * * Output: false
 * * Explanation: There are a total of 2 courses to take.
 * *              To take course 1 you should have finished course 0, and to take course 0 you should
 * *              also have finished course 1. So it is impossible.
 * *
 * * Note:
 * * The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 * * Read more about how a graph is represented.
 * * You may assume that there are no duplicate edges in the input prerequisites.
 * <p>
 * 思路：有向图的遍历， 拓扑排序
 * Challenge
 * *      入度、出度计算
 * *      程序实现
 * *      优化Solution1，避免重复遍历
 * *
 * * Algorithm
 * *      1. 初始化0~n-1每个元素的入度，设置满足条件的元素个数count为0，及入度为0的Queue；
 * *      2. 遍历入度数组，
 * *              若当前元素的入度为0
 * *                  将其加入到Queue
 * *      3. 循环访问Queue，直到其为空
 * *              取出第一个元素
 * *              count ++
 * *              将其出度元素的入度减1，若入度为0，则将其加入到Queue
 * *      4. 返回  count == numCourses
 *
 * @author dengxiaolin
 * @since 2020/12/04
 */
public class Course {
    public static void main(String[] args) {
        Course course = new Course();
        System.out.println(course.canFinish(4, new int[][] {
                {1, 0},
                {2, 3},
                {1, 3}
        }));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 2 || prerequisites == null) {
            return false;
        }

        // 计算入度
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][1]]++;
        }

        // 入度为0的都入队列，防止[0,1],[2,3] 这中没有链条的情况
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            // 出队列的时候课程数目加1
            Integer course = queue.poll();
            count++;

            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][0] == course) {
                    inDegree[prerequisites[i][1]]--;
                    if (inDegree[prerequisites[i][1]] == 0) {
                        queue.offer(prerequisites[i][1]);
                    }
                }
            }
        }

        return count == numCourses;

    }
}
