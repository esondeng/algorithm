package com.test.algorithm;

/**
 * Given n non-negative integers a1, a2, ...,
 * an , where each represents a point at coordinate (i, ai). n
 * vertical lines are drawn such that the two endpoints of the line
 * i is at (i, ai) and (i, 0). Find two lines, which, together
 * with the x-axis forms a container, such that the container
 * contains the most water.
 *
 * <p>
 * Notice that you may not slant the container.
 *
 * @author dengxiaolin
 * @since 2021/04/28
 */
public class MaxArea {

    private int maxArea(int[] m) {
        int left = 0;
        int right = m.length - 1;

        int max = 0;

        while (left < right) {
            max = Math.max(max, Math.min(m[left], m[right]) * (right - left));
            if (m[left] < m[right]) {
                left++;
            }
            else {
                right--;
            }
        }

        return max;
    }
}
