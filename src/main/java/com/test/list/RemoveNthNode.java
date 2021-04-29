package com.test.list;

/**
 * @author dengxiaolin
 * @since 2021/04/29
 */
public class RemoveNthNode {

    /**
     * Remove Nth Node From End of List
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
     * <p>
     * Follow up: Could you do this in one pass?
     * <p>
     * 假设 1 <=n <= ListNode的长度
     * <p>
     * 思路：快慢指针法
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = null;
        ListNode current = head;
        ListNode next = head.next;

        ListNode current1 = head;

        int i = 1;
        while (i < n) {
            current1 = current1.next;
            i++;
        }

        while (current1.next != null) {
            pre = current;
            current = next;
            if (next != null) {
                next = next.next;
            }

            current1 = current1.next;

        }

        if (pre == null) {
            return next;
        }

        pre.next = next;
        return head;

    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        removeNthFromEnd(listNode, 2);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
