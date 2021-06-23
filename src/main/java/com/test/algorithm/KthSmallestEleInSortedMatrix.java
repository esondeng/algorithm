package com.test.algorithm;

import java.util.PriorityQueue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 * <p>
 * Example:
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * return 13.
 *
 * @author dengxiaolin
 * @since 2021/06/23
 */
public class KthSmallestEleInSortedMatrix {
    public static void main(String[] args) {
        int[][] a = new int[][] {
                {1, 5, 9, 11},
                {2, 6, 10, 14},
                {3, 7, 13, 15}
        };
        System.out.println(kthSmallest(a, 11));
    }

    /**
     * 使用最小堆弹出k个
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        //优先队列
        PriorityQueue<Tuple> queue = new PriorityQueue<>();

        // 这里有一个设计非常巧妙的地方，将第一行升序的序列先入队列，根据Tuple类型的定义，最小的元素将会在队首。
        // 每当队首元素出队列后，都将其所在列的下一个元素入队列，直到最后一行；
        // 这样，就可以比较一个元素的同一行与同一列中相邻的元素中哪个较小，从而保证队首元素始终是矩阵中所有元素的最小值
        for (int j = 0; j < cols; ++j) {
            queue.add(new Tuple(0, j, matrix[0][j]));
        }

        //进行k-1次出队、入队，最后队首元素一定是所有元素中的第k小
        for (int i = 0; i < k - 1; ++i) {
            Tuple p = queue.poll();
            System.out.print(p.val + " ");
            if (p.x == rows - 1) {
                //若是最后，则没有元素需要入队列了
                continue;
            }

            queue.add(new Tuple(p.x + 1, p.y, matrix[p.x + 1][p.y]));
        }
        System.out.println();
        return queue.poll().val;
    }


    private static class Tuple implements Comparable<Tuple> {
        int x;
        int y;
        int val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple t) {
            // 若新加入的元素较小，则排在队首
            return this.val - t.val;
        }
    }

    /**
     * 二分查找法
     * 最小值是（0，0），最大值是（matrix.length - 1, matrix[0].length -1）
     * 若采用Binary Search, 先猜一个值，这个值是由最小值matrix[0][0]和最大值matrix[n-1][n-1]求mid得来.
     * <p>
     * 然后算下小于等于这个mid的matrix元素个数，若是个数小于k, 就在mid到最大值之间再猜一个新的mid. 直到l 不再小于 r.
     * <p>
     * Time Complexity: O(nlog(matrix[n-1][n-1] - matrix[0][0])), 每次lowerThanMidCount用时O(n).
     */
    public static int kthSmallest2(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length - 1][matrix[0].length - 1];

        while (left < right) {
            int middle = (left + right) / 2;
            int lowerThanMidCount = lowerThanMidCount(matrix, middle);
            if (lowerThanMidCount < k) {
                left = middle + 1;
            }
            else {
                right = middle;
            }
        }

        return left;
    }

    /**
     * 算下小于等于这个mid的matrix元素个数
     *
     * @return
     */
    private static int lowerThanMidCount(int[][] matrix, int middle) {
        int i = 0;
        int j = matrix[0].length - 1;

        int count = 0;
        // 注意，从右上角开始遍历
        while (j > 0 && i <= matrix.length) {
            if (matrix[i][j] < middle) {
                i++;
                count += j + 1;
            }
            else {
                j--;
            }
        }

        return count;
    }
}
