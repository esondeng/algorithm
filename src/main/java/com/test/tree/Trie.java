package com.test.tree;

/**
 * 单词查找树
 * Implement a trie with insert, search, and startsWith methods.
 * *
 * * Example:
 * * Trie trie = new Trie();
 * * trie.insert("apple");
 * * trie.search("apple");   // returns true
 * * trie.search("app");     // returns false
 * * trie.startsWith("app"); // returns true
 * * trie.insert("app");
 * * trie.search("app");     // returns true
 * *
 * * Note:
 * * You may assume that all inputs are consist of lowercase letters a-z.
 * * All inputs are guaranteed to be non-empty strings.
 *
 * @author dengxiaolin
 * @since 2020/12/04
 */
public class Trie {

    private Node root = new Node(' ');

    public void insert(String s) {
        Node current = root;
        for (char val : s.toCharArray()) {
            current.children[val - 'a'] = new Node(val);
            current = current.children[val - 'a'];
        }

        current.isWord = true;
    }

    public boolean search(String s) {
        Node node = getNode(s);
        return node != null && node.isWord;
    }

    public boolean startsWith(String s) {
        return getNode(s) != null;
    }


    private Node getNode(String s) {
        Node current = root;
        for (char val : s.toCharArray()) {
            Node node = current.children[val - 'a'];
            if (node == null) {
                return null;
            }
            current = node;
        }

        return current;
    }

    private static class Node {
        char val;
        /**
         * 26个英文字母
         */
        Node[] children = new Node[26];

        boolean isWord;

        public Node(char val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));

        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
