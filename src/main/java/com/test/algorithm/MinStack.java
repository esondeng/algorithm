package com.test.algorithm;

import java.util.Stack;

/**
 * 在栈的基础上加一个随时获取最小值的操作
 * Design a stack that supports push, pop, top, and
 * retrieving the minimum element in constant time.
 * push(x) – Push element x onto stack.
 * pop() – Removes the element on top of the stack.
 * top() – Get the top element.
 * getMin() – Retrieve the minimum element in the stack.
 * 思路：双栈
 *
 * @author dengxiaolin
 * @since 2020/11/24
 */
public class MinStack {

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(int x) {
        stack1.push(x);
        Integer min = stack2.peek();
        if (min == null) {
            stack2.push(x);
        }
        else {
            stack2.push(Math.min(min, x));
        }
    }

    public Integer pop() {
        int x = stack1.pop();
        stack2.pop();
        return x;
    }

    public Integer top() {
        return stack1.peek();
    }

    public Integer min() {
        return stack2.peek();
    }


}
