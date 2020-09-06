package com.xu.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xuwei
 * @Date 2020/9/6
 * @Version V1.0
 **/
public class Trie {
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

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    public int size() {
        return size;
    }

    //向Trie中添加一个新单词
    public void add(String word) {
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

    public boolean contains(String world) {
        Node cur = root;
        for (int i = 0; i < world.length(); i++) {
            char c = world.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }
}
