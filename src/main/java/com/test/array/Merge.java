package com.test.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * <p>
 * <p>
 * 思路：
 * 循环一遍，为了避免移动原数组，保留后面的。
 *
 * @author dengxiaolin
 * @since 2021/04/30
 */
public class Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }

        List<Interval> list = new ArrayList<>(intervals.length);
        for (int i = 0; i < intervals.length; i++) {
            list.add(Interval.of(intervals[i][0], intervals[i][1]));
        }

        list.sort(Comparator.comparingInt(Interval::getStart));

        Interval pre = list.get(0);
        List<Interval> result = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getStart() <= pre.getEnd()) {
                pre.setEnd(Math.max(pre.getEnd(), list.get(i).getEnd()));
            }
            else {
                result.add(pre);
                pre = list.get(i);
            }
        }

        result.add(pre);

        int[][] m = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            m[i][0] = result.get(i).getStart();
            m[i][1] = result.get(i).getEnd();
        }

        return m;
    }

    private static class Interval {
        int start;
        int end;

        public static Interval of(int start, int end) {
            Interval interval = new Interval();
            interval.start = start;
            interval.end = end;

            return interval;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}
