package com.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 * <p>
 * Input: nums = []
 * Output: []
 * <p>
 * 思路：
 * 1.深度优先 + 回溯
 * 2.根据深度优先的方式循环
 * i : current index
 * left : 左边
 * right ： 右边
 *
 * @author dengxiaolin
 * @since 2021/04/28
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        Stack<Integer> stack = new Stack<>();
        fillThreeSum(nums, 0, stack, result);

        return result;
    }


    public static void fillThreeSum(int[] nums, int startIndex, Stack<Integer> stack, List<List<Integer>> result) {
        if (stack.size() == 3) {
            int sum = 0;
            for (int i = 0; i < stack.size(); i++) {
                sum += stack.get(i);
            }

            if (sum == 0) {
                List<Integer> list = new ArrayList<>(stack);
                list.sort(Comparator.comparingInt(t -> t));
                if (!result.contains(list)) {
                    result.add(list);
                }
            }
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            stack.push(nums[i]);
            fillThreeSum(nums, i + 1, stack, result);
            stack.pop();
        }
    }


    public void threeSum(int[] nums, List<List<Integer>> result) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 防止重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    //防止重复添加相同内容List
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    //防止重复添加相同内容List
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                }
                else if (nums[i] + nums[left] + nums[right] < 0) {
                    // 说明左边的小了
                    left++;
                }
                else {
                    // 说明右边的大了
                    right--;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] m = new int[] {3, 0, -2, -1, 1, 2};
        threeSum(m).forEach(list -> {
            list.forEach(e -> System.out.print(e + " "));
            System.out.println();
        });
    }


}
