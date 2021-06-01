package com.test.algorithm;

import java.util.Stack;

/**
 * 题意：直方图中最大矩形面积
 * Given an array of integers heights representing the histogram's bar
 * height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 *
 * @author dengxiaolin
 * @since 2021/05/02
 */
public class LargestRectangle {
    public static int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;

        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length ? 0 : heights[i]);

            // 递增入栈，保证栈内索引对应的Height递增
            if (stack.size() == 0 || h >= heights[stack.peek()]) {
                stack.push(i);
            }
            else {
                // 计算该位置height高度的矩形
                int n = stack.pop();
                int left;
                if (stack.isEmpty()) {
                    // 若为空，则到最左边
                    left = -1;
                }
                else {
                    left = stack.peek();
                }

                result = Math.max(result, heights[n] * (i - left - 1));
                i--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = new int[] {1, 1};
        System.out.println(largestRectangleArea2(height));
    }
}
