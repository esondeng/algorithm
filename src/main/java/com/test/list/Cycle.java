package com.test.list;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * *
 * * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 * *
 * * Note: Do not modify the linked list.
 * *
 * *
 * * Example 1:
 * * Input: head = [3,2,0,-4], pos = 1
 * * Output: true
 * * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * *
 * *      3 - 2 - 0 - -4
 * *          |        |
 * *          ----------
 * *
 * *
 * * Example 2:
 * * Input: head = [1,2], pos = 0
 * * Output: true
 * * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * *
 * *      1 - 2
 * *      |   |
 * *      -----
 * *
 * *
 * * Example 3:
 * * Input: head = [1], pos = -1
 * * Output: false
 * * Explanation: There is no cycle in the linked list.
 * *
 * *      1
 * *
 * *
 * * Follow-up:
 * * Can you solve it without using extra space?
 * 思路：双指针法
 *
 * @author dengxiaolin
 * @since 2020/12/01
 */
public class Cycle {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = listNode.next;

        Cycle cycle = new Cycle();
        System.out.println(cycle.getEntrance(listNode).val);
    }

    /**
     * 获取相遇节点
     *
     * @param header
     * @return
     */
    public ListNode getMeetNode(ListNode header) {
        if (header == null) {
            return null;
        }

        ListNode slow = header;
        ListNode fast = header;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return slow;
            }
        }

        return null;
    }

    /**
     * 获取环的入口节点
     *
     * @param header
     * @return
     */
    public ListNode getEntrance(ListNode header) {
        ListNode meetNode = getMeetNode(header);
        if (meetNode == null) {
            return null;
        }

        while (meetNode != header) {
            meetNode = meetNode.next;
            header = header.next;
        }

        return meetNode;
    }


}

