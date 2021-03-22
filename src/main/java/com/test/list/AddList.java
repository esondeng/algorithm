package com.test.list;

/**
 * @author dengxiaolin
 * @since 2021/01/04
 */
public class AddList {

    ListNode addList(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode current = head;

        int up = 0;

        while (list1 != null || list2 != null) {
            int x = list1 != null ? list1.val : 0;
            int y = list2 != null ? list2.val : 0;

            int val = (x + y + up) % 10;
            up = (x + y + up) / 10;

            current.next = new ListNode(val);
            current = current.next;

            if (list1 != null) {
                list1 = list1.next;
            }

            if (list2 != null) {
                list1 = list2.next;
            }
        }

        if (up == 1) {
            current.next = new ListNode(1);
        }

        return head.next;

    }
}
