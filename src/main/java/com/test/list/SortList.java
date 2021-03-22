package com.test.list;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * *
 * * Example 1:
 * * Input: 4->2->1->3
 * * Output: 1->2->3->4
 * *
 * * Example 2:
 * * Input: -1->5->3->4->0
 * * Output: -1->0->3->4->5
 * 思路:单项链表的排序无非就是，归并排序，利用排序链表的合并,时间复杂度是o(n*log n)
 * 1. 利用快慢指针找到中间节点，不断拆分
 *
 * @author dengxiaolin
 * @since 2020/12/01
 */
public class SortList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(3);

        ListNode header = sort(listNode);

        while (header != null) {
            System.out.println(header.val);
            header = header.next;
        }

    }

    private static ListNode sort(ListNode header) {
        if (header == null) {
            return null;
        }

        ListNode fast = header;
        ListNode slow = header;
        ListNode slowPre = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;

            slowPre = slow;
            slow = slow.next;

        }

        // 拆分成两个list
        if (slowPre != null) {
            slowPre.next = null;
            return merge(sort(header), sort(slow));
        }
        else {
            // 只有一个节点
            return slow;
        }
    }

    private static ListNode merge(ListNode listNode1, ListNode listNode2) {
        if (listNode1 == null) {
            return listNode2;
        }

        if (listNode2 == null) {
            return listNode1;
        }

        // 利用新增头结点，和二维表扩展边界有异曲同工之妙
        ListNode tmpHeader = new ListNode(-1);
        ListNode currentNode = tmpHeader;

        while (listNode1 != null && listNode2 != null) {
            if (listNode1.val > listNode2.val) {
                currentNode.next = listNode2;
                listNode2 = listNode2.next;
            }
            else {
                currentNode.next = listNode1;
                listNode1 = listNode1.next;
            }

            currentNode = currentNode.next;
        }

        currentNode.next = listNode1 == null ? listNode2 : listNode1;

        return tmpHeader.next;
    }
}
