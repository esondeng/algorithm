package com.test.list;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * Input:1->2->3->4->5->NULL
 * Output:1->3->5->2->4->NULL
 * <p>
 * Example 2:
 * Input:2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * <p>
 * Note:
 * <p>
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on …
 * 给定单链表，将所有奇数节点组合在一起，然后是偶数节点。 请注意，这里我们讨论的是节点编号，而不是节点中的值。
 * <p>
 * 你应该尝试到位。 该程序应该以O（1）空间复杂度和O（nodes）时间复杂度运行。
 * <p>
 * 注意：
 * <p>
 * 偶数组和奇数组内的相对顺序应保持与输入中的顺序相同。
 * 第一个节点被认为是奇数，第二个节点被认为是偶数等等…
 * <p>
 * 思路：双指针法,奇偶指针
 *
 * @author dengxiaolin
 * @since 2021/06/18
 */
public class OddEvenList {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);

        oddEvenList(root);

        while (root != null) {
            System.out.println(root.val);
            root = root.next;
        }
    }

    public static void oddEvenList(ListNode root) {
        if (root == null) {
            return;
        }

        ListNode oddNode = root;
        ListNode evenNode = root.next;
        ListNode tempEven = evenNode;

        while (evenNode != null && evenNode.next != null) {
            oddNode.next = evenNode.next;
            oddNode = evenNode.next;

            evenNode.next = oddNode.next;
            evenNode = oddNode.next;
        }

        oddNode.next = tempEven;
    }
}
