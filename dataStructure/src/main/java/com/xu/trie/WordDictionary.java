package com.xu.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 211题
 * 单词可能包含点“。” 点可以与任何字母匹配的地方
 **/
public class WordDictionary {
    class Node{
        boolean isWord;
        Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }
    private Node root;
    private int size;


    public int size() {
        return size;
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new Node();
        this.size = 0;
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return search(node.next.get(c), word, index + 1);
        }else{
            for (char nextChar : node.next.keySet()) {
                if (search(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}
