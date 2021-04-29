package com.test.algorithm;

import java.util.Stack;

/**
 * 判断是否正确的表达式
 *
 * @author dengxiaolin
 * @since 2020/11/25
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("}{}"));
    }

    private static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (tmp == '(' || tmp == '{' || tmp == '[') {
                stack.push(tmp);
            }
            else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character character = stack.pop();
                boolean isValid = (character == '(' && tmp == ')')
                        || (character == '[' && tmp == ']')
                        || (character == '{' && tmp == '}');

                if (!isValid) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
