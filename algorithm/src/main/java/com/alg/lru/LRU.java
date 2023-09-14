package com.alg.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xuwei
 * @Date 2020/11/18
 * @Version V1.0
 **/
public class LRU {
    class DLinkNode{
        int key;
        int value;
        DLinkNode pre;
        DLinkNode next;

        public DLinkNode() {
        }

        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private int size;
    private Map<Integer, DLinkNode> cache;
    private DLinkNode head;
    private DLinkNode tail;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.head = new DLinkNode();
        this.tail = new DLinkNode();
        this.size = 0;

        this.head.next = tail;
        this.tail.pre = head;
    }

    //删除节点
    private void removeNode(DLinkNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    //将节点插入链表头部
    private void addHead(DLinkNode node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    //删除尾结点
    private DLinkNode popTail() {
        DLinkNode node = tail.pre;
        removeNode(node);
        return node;
    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addHead(node);
    }

    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            node = new DLinkNode(key, value);
            addHead(node);
            cache.put(key, node);

            size++;

            if (size > capacity) {
                DLinkNode tail = popTail();
                cache.remove(tail.key);
            }

        }else {
            node.value = value;
            moveToHead(node);
        }
    }

    public int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DLinkNode node = head.next;
        while (node != null) {
            sb.append(node.value);
            sb.append(",");
            node = node.next;
        }
        return sb.toString();
    }

}
