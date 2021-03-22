package com.test.list;

import java.util.Arrays;

/**
 * @author dengxiaolin
 * @since 2020/11/13
 */
public class ListSplit {

    public static void main(String[] args) {
        ListSplit listSplit = new ListSplit();
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);
        listNode1.next.next.next = new ListNode(4);
        listNode1.next.next.next.next = new ListNode(5);

        Arrays.stream(listSplit.splitListToParts(listNode1, 3)).forEach(t -> {
            while (t != null) {
                System.out.print(t.val + "->");
                t = t.next;
            }
            System.out.println();
        });
    }

    public ListNode[] splitListToParts(ListNode root, int k) {
        if (root == null) {
            return null;
        }

        int count = 0;
        ListNode tmp = root;
        while (tmp != null) {
            tmp = tmp.next;
            count++;
        }

        int length = count / k;
        length = length == 0 ? 1 : length;
        int other = count % k;

        ListNode[] result = new ListNode[length];
        ListNode current = root;
        for (int i = 0; i < length && current != null; i++) {
            int size = other-- > 0 ? k + 1 : k;
            result[i] = current;

            ListNode pre = null;
            for (int j = 0; j < size && current != null; j++) {
                pre = current;
                current = current.next;
            }

            pre.next = null;
        }

        return result;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
