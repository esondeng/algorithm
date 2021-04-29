package com.test.list;

/**
 * @author dengxiaolin
 * @since 2021/04/29
 */
public class MergeList {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode current = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            else {
                current.next = new ListNode(l2.val);
                l2 = l2.next;
            }

            current = current.next;
        }

        current.next = l1 == null ? l2 : l1;

        return dummyHead.next;
    }


}
