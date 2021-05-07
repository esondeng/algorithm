package com.test.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * Follow up:
 * Could you do get and put in O(1) time complexity?
 * <p>
 * 思路：要想o(1)的时间复杂度，利用map+链表
 * 在get时，把当前的key放到链表的最前面
 *
 * @author dengxiaolin
 * @since 2021/05/06
 */
public class LruCache {
    int capcity;
    int count = 0;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map = new HashMap<>();

    public LruCache(int capcity) {
        this.capcity = capcity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);

        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    private void delete(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void moveToHead(Node node) {
        node.next = head.next;
        head.next.pre = node;

        head.next = node;
        node.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }

        delete(node);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node valueNode = map.get(key);
            valueNode.setValue(value);

            delete(valueNode);
            moveToHead(valueNode);
        }
        else {
            if (count == capcity) {
                Node deleteNode = tail.pre;
                delete(deleteNode);
                map.remove(deleteNode.key);

                Node valueNode = new Node(key, value);
                moveToHead(valueNode);
                map.put(key, valueNode);
            }
            else {
                Node valueNode = new Node(key, value);
                moveToHead(valueNode);
                map.put(key, valueNode);
                count++;
            }
        }
    }

    private static class Node {
        private int key;
        private int value;

        private Node pre;
        private Node next;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Node node = (Node) o;
            return key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
