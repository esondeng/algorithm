package com.test.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 没有入度的节点
 *
 * @author dengxiaolin
 * @since 2020/11/02
 */
public class Edges {

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < edges.size(); i++) {
            map.put(edges.get(i).get(1), 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(i)) {
                result.add(i);
            }
        }

        return result;
    }
}

