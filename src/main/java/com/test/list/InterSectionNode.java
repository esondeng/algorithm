package com.test.list;

/**
 * 获取两条单向链表的交叉节点
 *
 * @author dengxiaolin
 * @since 2020/11/24
 */
public class InterSectionNode {

    public ListNode getInterSectionNode(ListNode listNode1, ListNode listNode2) {
        if (listNode1 == null || listNode2 == null) {
            return null;
        }

        int count1 = count(listNode1);
        int count2 = count(listNode2);

        int diff = 0;
        if (count1 > count2) {
            diff = count1 - count2;
        }
        else {
            diff = count2 - count1;
            ListNode temp = listNode1;
            listNode1 = listNode2;
            listNode2 = temp;
        }

        while (diff-- > 0) {
            listNode1 = listNode1.next;
        }

        while (listNode1 != listNode2) {
            listNode1 = listNode1.next;
            listNode2 = listNode2.next;
        }

        return listNode1;
    }

    private int count(ListNode listNode1) {
        int count = 0;
        while (listNode1 != null) {
            count++;
            listNode1 = listNode1.next;
        }

        return count;
    }
}
