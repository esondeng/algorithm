package com.test.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * <p>
 * Example 2:
 * Given the list [1,[4,[6]]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * <p>
 * 题意及分析：嵌套的list，要求给出其迭代器的next()和hasNext函数。可以使用一个stack来维护当前list剩余元素。
 * 我开始是在next里对stack做操作，但是由于当出现[]这种空list时没有办法判断hasNext，容易出现空指针操作。
 * 所以需要在hasNext()里面对stack操作。将list反向添加进stack中，这样就可以让先出现的数先被stack弹出。
 *
 * @author dengxiaolin
 * @since 2021/06/22
 */
public class NestedListIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack = new Stack<>();

    public NestedListIterator(List<NestedInteger> nestedList) {
        if (nestedList != null && !nestedList.isEmpty()) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }
    }


    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }

        NestedInteger nowInteger = stack.peek();
        if (nowInteger.isInteger()) {
            return true;
        }

        while (!stack.isEmpty()) {
            nowInteger = stack.pop();
            List<NestedInteger> list = nowInteger.getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
            if (stack.isEmpty()) {
                return false;
            }
            nowInteger = stack.peek();
            if (nowInteger.isInteger()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }


    private static interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a
        // nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a
        // single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a
        // nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }
}
