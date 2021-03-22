package com.test.water;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水
 * <p>
 * 思路：
 * 1. left < right的情况：从左往右，包含自己在内的最大值减去自己
 * 2. right < left, 从右往左的逻辑
 *
 * @author dengxiaolin
 * @since 2020/11/20
 */
public class Water {

    public static void main(String[] args) {
        Water water = new Water();
        System.out.println(water.trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int low = 0;
        int high = height.length - 1;

        int result = 0;

        int lowMax = 0;
        int highMax = 0;

        while (low < high) {
            if (height[low] < height[high]) {
                lowMax = Math.max(lowMax, height[low]);
                result += lowMax - height[low];
                low++;
            }
            else {
                highMax = Math.max(highMax, height[high]);
                result += highMax - height[high];
                high--;
            }
        }

        return result;
    }

}
