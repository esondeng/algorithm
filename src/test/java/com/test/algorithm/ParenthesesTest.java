package com.test.algorithm;

import org.junit.jupiter.api.Test;

import com.test.dfs.Parentheses;

/**
 * @author dengxiaolin
 * @since 2020/11/10
 */
public class ParenthesesTest {

    @Test
    public void test() {
        Parentheses parentheses = new Parentheses();
        parentheses.generateParentheses(3).forEach(System.out::println);
    }
}
