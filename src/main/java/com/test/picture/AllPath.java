package com.test.picture;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 有向图所有路径，深度优先回溯算法
 * 从0到3的所有路径
 *
 * @author dengxiaolin
 * @since 2020/11/03
 */
public class AllPath {
    public static void main(String[] args) {
        AllPath allPath = new AllPath();
        allPath.allPathsSourceTarget(allPath.buildGraph()).forEach(t -> {
            t.forEach(item -> {
                System.out.print(item);
                System.out.print("\t");
            });
            System.out.println();
        });
    }

    private int[][] buildGraph() {
        return new int[][] {{1, 2}, {3}, {3}, {}};
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<List<Integer>> result = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return result;
        }
        Stack<Integer> path = new Stack<>();
        path.push(0);

        findPath(graph, 0, path, result);
        return result;
    }

    private void findPath(int[][] graph, int current, Stack<Integer> path, List<List<Integer>> result) {
        if (current == graph.length - 1) {
            result.add(new ArrayList<>(path));
        }

        for (int i = 0; i < graph[current].length; i++) {
            path.push(graph[current][i]);
            findPath(graph, graph[current][i], path, result);
            path.pop();
        }


    }
}
