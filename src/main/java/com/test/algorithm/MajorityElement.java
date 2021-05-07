package com.test.algorithm;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * <p>
 * 思路:
 * 1. 排序：取n/2序号的，事假复杂度是o(nlogn)
 * 2. 多数投票算法：Boyer-Moore Voting Algorithm
 * 为了解析算法的原理，我们只要考虑存在多数元素的情况即可，因为第二趟扫描可以检测出不存在多数元素的情况。
 * <p>
 * 举个例子，我们的输入数组为[1,1,0,0,0,1,0]，那么0就是多数元素。
 * 首先，candidate被设置为第一个元素1，count也变成1，由于1不是多数元素，所以当扫描到数组某个位置时，
 * count一定会减为0。在我们的例子中，当扫描到第四个位置时，count变成0.
 * <p>
 * 多数投票算法还有个好处：
 * 分布式Boyer-Moore
 * Boyer-Moore还有一个优点，那就是可以使用并行算法实现。相关算法可见Finding the Majority Element in Parallel
 * 其基本思想为对原数组采用分治的方法，把数组划分成很多段(每段大小可以不相同)，在每段中计算出candidate-count二元组，然后得到最终结果。
 * <p>
 * 举个例子，原数组为[1,1,0,1,1,0,1,0,0]
 * 划分1：
 * [1,1,0,1,1] –> (candidate,count)=(1,3)
 * 划分2：
 * [0,1,0,0] –> (candidate,count)=(0,2)
 * 根据(1,3)和(0,2)可得，原数组的多数元素为1.
 * <p>
 * 正因为这个特性，考虑若要从一个非常大的数组中寻找多数元素，数据量可能多大数百G，那么我们甚至可以用MapReduce的方式来解决这个问题。
 * <p>
 * ————————————————
 * 版权声明：本文为CSDN博主「kimixuchen」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/kimixuchen/article/details/52787307
 *
 * @author dengxiaolin
 * @since 2021/05/06
 */
public class MajorityElement {
    public static int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] {2, 3, 3}));
    }

}
