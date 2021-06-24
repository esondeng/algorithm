package com.test.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * 1.insert(val): Inserts an item val to the set if not already present.
 * 2.remove(val): Removes an item val from the set if present.
 * 3.getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 * Example:
 * <p>
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * <p>
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * <p>
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * <p>
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * <p>
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * <p>
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * <p>
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 * 思路：肯定是借助java已有的数据结构进行设计，常用的有ArrayList,HashMap,HashSet,能做到随机取数的有前两个，能实现判断和删除O（1）是否包含的是后两个，
 * 而且map想判断的话，数据必须存key，这样就不能取数了。用list取数的时候，必须要知道数据和下标的对应关系，所以可以map和list配合
 *
 * @author dengxiaolin
 * @since 2021/06/24
 */
public class RandomizedSet {
    private Map<Integer, Integer> map = new HashMap<>();
    private List<Integer> list = new ArrayList<>();

    public boolean insert(int value) {
        if (map.containsKey(value)) {
            return false;
        }

        list.add(value);
        map.put(value, list.size() - 1);

        return true;
    }

    public boolean remove(int value) {
        if (map.containsKey(value)) {
            int index = map.get(value);
            map.remove(value);

            // list的最后一个元素
            int num = list.get(list.size() - 1);
            list.set(index, num);
            list.remove(list.size() - 1);
            return true;

        }
        return false;
    }

    public int getRandom() {
        Random random = new Random();
        // 随机获取下标进行取数
        return list.get(random.nextInt(list.size()));
    }
}
