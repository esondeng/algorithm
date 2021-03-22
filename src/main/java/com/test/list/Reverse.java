package com.test.list;

/**
 * @author dengxiaolin
 * @since 2020/12/03
 */
public class Reverse {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);

        Reverse reverse = new Reverse();
        listNode = reverse.reverse(listNode);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    /**
     * 三指针法
     *
     * @param listNode
     * @return
     */
    public ListNode reverse(ListNode listNode) {
        if (listNode == null) {
            return null;
        }

        ListNode pre = null;
        ListNode current = listNode;
        ListNode next = current.next;

        while (current != null) {
            current.next = pre;

            pre = current;
            current = next;
            if (next != null) {
                next = next.next;
            }
        }

        return pre;
    }
}
