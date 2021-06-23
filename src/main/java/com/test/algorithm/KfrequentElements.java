package com.test.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * leecode-347
 * 描述
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 思路1：利用快速排序算法，o(n*logn)
 * 思路2：利用最小堆，通过维护一个元素数目为 𝑘 的最小堆，
 * 每次都将新的元素与堆顶端的元素（堆中频率最小的元素）进行比较，
 * 如果新的元素的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中。最终，堆中的 𝑘 个元素即为前 𝑘 个高频元素。
 * 思路3：利用桶排序算法
 *
 * @author dengxiaolin
 * @since 2021/06/23
 */
public class KfrequentElements {
    /**
     * 利用java自身的排序
     */
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(120000);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparingInt(Map.Entry::getValue));

        int[] result = new int[k];
        int index = 0;
        for (int i = list.size() - 1; i >= list.size() - k; i--) {
            result[index++] = list.get(i).getKey();
        }

        return result;
    }

    /**
     * PriorityQueue最小堆
     */
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(120000);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        map.forEach((k1, v) -> {
            if (queue.size() < k) {
                queue.add(k1);
            }
            else {
                if (map.get(k1) > map.get(queue.peek())) {
                    queue.remove();
                    queue.add(k1);
                }
            }
        });

        int[] result = new int[k];
        int index = 0;
        while (!queue.isEmpty()) {
            result[index++] = queue.remove();
        }

        return result;
    }


    /**
     * 桶排序
     */
    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(120000);
        int maxCount = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            maxCount = Math.max(maxCount, map.get(num));
        }

        List<Integer>[] array = new List[maxCount + 1];
        map.forEach((k1, v) -> {
            if (array[v] == null) {
                array[v] = new ArrayList<>();
            }
            array[v].add(k1);
        });

        int[] result = new int[k];
        int count = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            if (count >= k) {
                break;
            }

            if (array[i] != null) {
                for (Integer num : array[i]) {
                    result[count++] = num;
                    if (count >= k) {
                        break;
                    }
                }
            }
        }
        return result;
    }
}
