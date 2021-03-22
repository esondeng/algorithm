package com.test.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先
 * 对于当前位置来说要么左括号，要么右括号
 * 另外一定是先放左括号，再放右括号
 *
 * @author dengxiaolin
 * @since 2020/10/23
 */
public class Parentheses {

    public static void main(String[] args) {
        Parentheses parentheses = new Parentheses();
        parentheses.generateParentheses(3).forEach(System.out::println);
    }

    /**
     * 使用系统栈的深度优先遍历
     *
     * @param n
     * @return
     */
    public List<String> generateParentheses(int n) {
        List<String> result = new ArrayList<>();
        generateParentheses(result, "", 0, 0, n);
        return result;
    }

    private void generateParentheses(List<String> result, String s, int left, int right, int n) {
        if (left > n || right > n) {
            return;
        }

        if (left == n && right == n) {
            result.add(s);
        }

        // 还可以继续加
        if (left >= right) {
            // 先加左括号
            generateParentheses(result, s + '(', left + 1, right, n);
            generateParentheses(result, s + ')', left, right + 1, n);
        }
    }
}
