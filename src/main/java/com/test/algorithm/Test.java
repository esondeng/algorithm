package com.test.algorithm;

import java.util.BitSet;

/**
 * @author dengxiaolin
 * @since 2020/08/25
 */
public class Test {

    private static Item insert(int val, Item item) {
        Item insertNode = new Item(val);
        if (item == null) {
            return insertNode;
        }

        Item pre = null;
        Item next = item;

        while (next != null) {
            if (next.val < val) {
                pre = next;
                next = next.next;
            }
            else {
                break;
            }
        }

        if (pre == null) {
            Item head = insertNode;
            head.next = next;

            return head;
        }

        pre.next = insertNode;
        insertNode.next = next;

        return item;


    }


    private static void print(Item item) {
        while (item != null) {
            System.out.print(item.val + " ");
            item = item.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        Item item = new Item(1);
//
//        print(item);
//        item = insert(0, item);
//        print(item);
        BitSet bidSet = new BitSet();
        bidSet.set(0);
        System.out.println(bidSet.get(0));

        bidSet.set(0);
        System.out.println(bidSet.get(0));

        System.out.println(1L << 3);
    }

    private static class Item {
        private int val;

        public Item(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Item getNext() {
            return next;
        }

        public void setNext(Item next) {
            this.next = next;
        }

        private Item next;


    }
}
