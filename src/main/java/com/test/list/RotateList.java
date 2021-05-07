package com.test.list;

/**
 * @author dengxiaolin
 * @since 2021/05/07
 */
public class RotateList {
    public static ListNode rotateList(ListNode listNode, int n) {
        if (listNode == null) {
            return null;
        }

        int length = getLength(listNode);

        int move = n % length;
        if (move == 0) {
            return listNode;
        }

        ListNode fast = listNode;
        ListNode fastPre = null;

        ListNode slow = listNode;
        ListNode slowPre = null;

        while (move > 1) {
            fastPre = fast;
            fast = fast.next;
            move--;
        }

        while (fast != null) {
            fastPre = fast;
            fast = fast.next;

            slowPre = slow;
            slow = slow.next;
        }

        fastPre.next = listNode;
        slowPre.next = null;

        return slow;

    }

    private static int getLength(ListNode listNode) {
        int length = 0;
        while (listNode != null) {
            listNode = listNode.next;
            length++;
        }

        return length;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);


        listNode = rotateList(listNode, 2);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
