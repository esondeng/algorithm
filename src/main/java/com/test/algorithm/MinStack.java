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

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        }
        else {
            // peek也会抛异常
            Integer min = minStack.peek();
            minStack.push(Math.min(val, min));
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }

        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
