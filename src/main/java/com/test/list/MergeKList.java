package com.test.list;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Constraints:
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length won't exceed 10^4.
 * <p>
 * 思路： 分治法，归并思路
 *
 * @author dengxiaolin
 * @since 2021/04/29
 */
public class MergeKList {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        else {
            return merge(lists, 0, lists.length - 1);
        }
    }


    private ListNode merge(ListNode[] lists, int i, int j) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return lists[i];
        }

        int middle = (i + j) / 2;
        ListNode left = merge(lists, i, middle);
        ListNode right = merge(lists, middle + 1, j);

        return MergeList.mergeTwoLists(left, right);
    }
}
