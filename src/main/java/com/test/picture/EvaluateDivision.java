package com.test.picture;

import java.util.Arrays;
import java.util.List;

/**
 * Equations are given in the format A / B = k, where A and B are
 * variables represented as strings, and k
 * is a real number (floating point number). Given some queries,
 * return the answers. If the answer does not exist, return -1.0.
 * *
 * * Example:
 * * Given a / b = 2.0, b / c = 3.0.
 * * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * *
 * * The input is: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries , where equations.size() == values.size(), a
 * nd the values are positive. This represents the equations. Return vector<double>.
 * *
 * * According to the example above:
 * *
 * * equations = [ ["a", "b"], ["b", "c"] ],
 * * values = [2.0, 3.0],
 * * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * *
 * * The input is always valid. You may assume that evaluating
 * the queries will result in no division by zero and there is no contradiction.
 * <p>
 * 思路：
 * 有向图的问题，先看入度，循环查找
 *
 * @author dengxiaolin
 * @since 2020/12/10
 */
public class EvaluateDivision {

    public static void main(String[] args) {
        List<List<Character>> equations = Arrays.asList(Arrays.asList('a', 'b'), Arrays.asList('b', 'c'));
        List<Double> values = Arrays.asList(2.0D, 3.0D);
        List<List<Character>> queries = Arrays.asList(
                Arrays.asList('a', 'c'),
                Arrays.asList('b', 'a'),
                Arrays.asList('a', 'e'),
                Arrays.asList('a', 'a'),
                Arrays.asList('x', 'x'));

        System.out.println(Arrays.toString(division(equations, values, queries)));
    }

    public static double[] division(List<List<Character>> equations, List<Double> values, List<List<Character>> queries) {
        int[] entry = new int[128];
        for (int i = 0; i < equations.size(); i++) {
            entry[(int) equations.get(i).get(0)]++;
            entry[(int) equations.get(i).get(1)]++;
        }


        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = division(equations, values, entry, queries.get(i));
        }

        return result;
    }

    private static double division(List<List<Character>> equations, List<Double> values, int[] entry, List<Character> query) {
        if (entry[(int) query.get(0)] == 0 || entry[(int) query.get(1)] == 0) {
            return -1.0D;
        }

        Character character1 = query.get(0);
        Character character2 = query.get(1);
        if (character1.equals(character2)) {
            return 1.0D;
        }

        double count = 1D;
        boolean[] visit = new boolean[equations.size()];

        while (true) {
            boolean find = false;
            for (int i = 0; i < equations.size(); i++) {
                List<Character> equation = equations.get(i);
                if (!visit[i] && equation.get(0).equals(character1)) {
                    count *= values.get(i);
                    if (equation.get(1).equals(character2)) {
                        return count;
                    }
                    else {
                        character1 = equation.get(1);
                    }

                    visit[i] = true;
                    find = true;
                }
                else if (!visit[i] && equation.get(0).equals(character2)) {
                    count /= values.get(i);
                    if (equation.get(1).equals(character1)) {
                        return count;
                    }
                    else {
                        character2 = equation.get(1);

                    }

                    visit[i] = true;
                    find = true;
                }
            }

            if (!find) {
                return -1.0D;
            }
        }
    }
}
