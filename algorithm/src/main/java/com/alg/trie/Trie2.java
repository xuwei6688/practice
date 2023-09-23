package com.alg.trie;

import java.util.HashMap;
import java.util.Map;

class Trie2 {
    class Node{
        private boolean isWord;
        private Node []next;

        public Node() {
            this(false);
        }

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new Node[26];
        }
    }

    private Node root;
    private int size;

    public Trie2() {
        this.root = new Node(false);
        this.size = 0;
    }

    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next[c-'a'] == null) {
                cur.next[c-'a'] = new Node();
            }
            cur = cur.next[c-'a'];
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next[c-'a'] == null) {
                return false;
            }
            cur = cur.next[c-'a'];
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next[c-'a'] == null) {
                return false;
            }
            cur = cur.next[c-'a'];
        }
        return true;
    }
}
