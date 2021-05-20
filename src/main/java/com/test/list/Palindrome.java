package com.test.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 * <p>
 * Input: head = [1,2]
 * Output: false
 * <p>
 * 思路：1. 用list存，空间换时间
 * 2. 逆转一半之后比对
 *
 * @author dengxiaolin
 * @since 2021/05/20
 */
public class Palindrome {
    public static boolean isPalindrome(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int i = 0;
        int j = list.size() - 1;

        while (i < j) {
            if (list.get(i++).val != list.get(j--).val) {
                return false;
            }
        }

        return true;
    }


    public static boolean isPalindrome2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow = reverse(slow);

        while (head != null && slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }

        return true;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        if (head != null) {
            next = head.next;
        }

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

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(1);
        System.out.println(isPalindrome2(listNode));
    }
}
