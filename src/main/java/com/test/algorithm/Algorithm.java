package com.test.algorithm;


import java.util.Arrays;
import java.util.List;

/**
 * @author dengxiaolin
 * @since 2020/08/11
 */
public class Algorithm {

    public static void main(String[] args) {
//        int n = 3;
//        int totalVolume = 9;
//        int p[][] = new int[n + 1][totalVolume + 1];
//
//        List<Item> itemList = new ArrayList<>();
//        itemList.add(Item.of(1, 1));
//        itemList.add(Item.of(1, 1));
//        itemList.add(Item.of(1, 1));
//        itemList.add(Item.of(1, 1));
//        itemList.add(Item.of(2, 3));
//        itemList.add(Item.of(2, 3));
//        itemList.add(Item.of(2, 3));
//        itemList.add(Item.of(2, 3));
//        itemList.add(Item.of(7, 5));
//        itemList.add(Item.of(7, 5));
//        itemList.add(Item.of(7, 5));
//        itemList.add(Item.of(7, 5));
//
//        testK3(itemList, totalVolume);

//        List<Item1> itemList1 = new ArrayList<>();
//        itemList1.add(Item1.of(1, 1, 4));
//        itemList1.add(Item1.of(2, 3, 4));
//        itemList1.add(Item1.of(7, 5, 4));
//
//        test2k(itemList1, totalVolume);

//        int[] m = new int[3];
//        m[0] = 2;
//        m[1] = 1;
//        m[2] = 3;
//        for (int i = 0; i < m.length; i++) {
//            heapfy(m, i);
//        }

        System.out.println(uniqueOccurrences(new int[] {1, 2, 2, 1, 1, 3}));

    }

    /**
     * 有没出现过重复数字
     *
     * @param arr
     * @return
     */
    public static boolean uniqueOccurrences(int[] arr) {
        int[] counter = new int[2001];
        for (int i = 0; i < arr.length; i++) {
            counter[arr[i] + 1000]++;
        }

        boolean[] same = new boolean[2001];
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > 0) {
                if (!same[counter[i]]) {
                    same[counter[i]] = true;
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }

    public static int find(int[] m, int target) {
        if (m == null || m.length == 0) {
            return -1;
        }

        int start = 0;
        int end = m.length - 1;

        // 只有一个元素的场景，因此是小于等于
        while (start <= end) {
            int middle = (start + end) / 2;
            if (m[middle] < target) {
                start = middle + 1;
            }
            else if (m[middle] > target) {
                end = middle - 1;
            }
            else {
                return middle;
            }
        }

        return -1;
    }

    public static void countSort(int[] m) {
        Arrays.stream(m).forEach(System.out::print);
        System.out.println();
        int min = m[0];
        int max = m[0];

        // 找出最大值最小值
        for (int i = 0; i < m.length; i++) {
            if (m[i] > max) {
                max = m[i];
            }
            if (m[i] < min) {
                min = m[i];
            }
        }

        // 计数
        int[] c = new int[max - min + 1];
        for (int i = 0; i < m.length; i++) {
            c[m[i] - min]++;
        }

        // 遍历count数组回填数组
        int index = 0;
        for (int i = 0; i < c.length; i++) {
            int count = c[i];
            int value = i + min;
            if (count > 0) {
                while (count-- > 0) {
                    m[index++] = value;
                }
            }
            else {
                index++;
            }
        }

        Arrays.stream(m).forEach(System.out::print);
        System.out.println();
    }


    private static void heapfy(int[] m, int currentIndex) {
        int startIndex = (m.length - currentIndex) / 2 + currentIndex;
        for (; startIndex >= currentIndex; startIndex--) {
            int leftIndex = 2 * startIndex + 1 - currentIndex;
            int rightIndex = 2 * startIndex + 2 - currentIndex;

            int minIndex = startIndex;
            if (leftIndex > 0 && leftIndex < m.length) {
                if (m[minIndex] > m[leftIndex]) {
                    minIndex = leftIndex;
                }
            }

            if (rightIndex > 0 && rightIndex < m.length) {
                if (m[minIndex] > m[rightIndex]) {
                    minIndex = rightIndex;
                }
            }

            if (minIndex != startIndex) {
                int temp = m[startIndex];
                m[startIndex] = m[minIndex];
                m[minIndex] = temp;
            }

            Arrays.stream(m).forEach(System.out::print);
            System.out.println();
        }
    }


    public static void test(List<Item> itemList, int totalVolume) {
        int n = itemList.size();
        int p[][] = new int[n + 1][totalVolume + 1];

        print(p, 0);

        for (int i = 1; i <= n; i++) {
            Item item = itemList.get(i - 1);
            int volume = item.getVolume();

            for (int j = 0; j <= totalVolume; j++) {
                if (j >= volume) {
                    int newValue = p[i - 1][j - volume] + item.getValue();
                    p[i][j] = p[i - 1][j] > newValue ? p[i - 1][j] : newValue;
                }
                else {
                    p[i][j] = p[i - 1][j];
                }
            }
            print(p, i);
        }

        System.out.println(p[3][9]);
    }


    private static void testK(List<Item1> itemList, int totalVolume) {
        int n = itemList.size();
        int p[][] = new int[n + 1][totalVolume + 1];


        // 初始化第一行数据
        for (int i = 0; i <= totalVolume; i++) {
            p[0][i] = 0;
        }
        print(p, 0);

        for (int i = 1; i <= n; i++) {
            Item1 item = itemList.get(i - 1);
            int volume = item.getVolume();
            int count = item.getCount();

            for (int j = 0; j <= totalVolume; j++) {
                int max = p[i - 1][j];
                for (int k = 1; k <= count; k++) {
                    // 循环看看k个第i物品是否能放下
                    int kVolume = k * volume;
                    if (j >= kVolume) {
                        int newValue = p[i - 1][j - kVolume] + k * item.getValue();
                        max = max > newValue ? max : newValue;
                    }
                }
                p[i][j] = max;

            }
            print(p, i);
        }

        System.out.println(p[3][9]);
    }

    public static void test1(List<Item> itemList, int totalVolume) {
        int p[] = new int[totalVolume + 1];

        for (int i = 1; i <= itemList.size(); i++) {
            Item item = itemList.get(i - 1);
            int volume = item.getVolume();

            for (int j = totalVolume; j >= volume; j--) {
                int newValue = p[j - volume] + item.getValue();
                p[j] = newValue > p[j] ? newValue : p[j];
            }
        }


        System.out.println(p[totalVolume]);
    }

    public static void test2(List<Item> itemList, int totalVolume) {
        int p[] = new int[totalVolume + 1];

        for (int i = 1; i <= itemList.size(); i++) {
            Item item = itemList.get(i - 1);
            int volume = item.getVolume();

            for (int j = volume; j <= totalVolume; j++) {
                int newValue = p[j - volume] + item.getValue();
                p[j] = newValue > p[j] ? newValue : p[j];
            }
        }


        System.out.println(p[totalVolume]);
    }


    public static void test2k(List<Item1> itemList, int totalVolume) {
        int p[] = new int[totalVolume + 1];

        for (int i = 1; i <= itemList.size(); i++) {
            Item1 item = itemList.get(i - 1);
            int volume = item.getVolume();

            for (int j = totalVolume; j >= volume; j--) {
                int max = p[j];
                for (int k = 1; k <= item.getCount(); k++) {
                    if (j >= k * volume) {
                        int newValue = p[j - k * volume] + k * item.getValue();
                        max = max > newValue ? max : newValue;
                    }
                }
                p[j] = max;
            }
        }


        System.out.println(p[totalVolume]);
    }


    private static void testK3(List<Item> itemList, int totalVolume) {
        int n = itemList.size();
        int p[][] = new int[n + 1][totalVolume + 1];

        for (int i = 1; i <= n; i++) {
            Item item = itemList.get(i - 1);
            int volume = item.getVolume();
            int value = item.getValue();

            for (int j = 0; j <= totalVolume; j++) {
                if (j >= volume) {
                    int newValue = p[i - 1][j - volume] + value;
                    p[i][j] = newValue > p[i - 1][j] ? newValue : p[i - 1][j];
                }
                else {
                    p[i][j] = p[i - 1][j];
                }

            }
            print(p, i);
        }

        System.out.println(p[12][9]);
    }

    private static class Item {
        int volume;
        int value;

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public static Item of(int volume, int value) {
            Item item = new Item();
            item.setVolume(volume);
            item.setValue(value);

            return item;
        }
    }


    private static class Item1 {
        int volume;
        int value;
        int count;

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public static Item1 of(int volume, int value, int count) {
            Item1 item = new Item1();
            item.setVolume(volume);
            item.setValue(value);
            item.setCount(count);

            return item;
        }
    }

    private static void print(int[][] p, int i) {
        for (int k = 0; k <= 9; k++) {
            System.out.print(p[i][k] + " ");
        }
        System.out.println();
    }
}
