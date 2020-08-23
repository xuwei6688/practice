package com.xu.list;


/**
 * @Author xuwei
 * @Date 2020/8/22
 * @Version V1.0
 **/
public class LinkedList<E> implements List<E>{
    private Node dummyHead;
    private int size;

    public LinkedList() {
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node node = dummyHead.next;
        while (node != null) {
            if (node.e.equals(o)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
         add(size, e);
         return true;
    }

    @Override
    public boolean remove(Object o) {
        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.e.equals(o)) {
                pre.next = pre.next.next;
                return true;
            }
            pre = pre.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must >= 0 and < size");
        }

        Node node = dummyHead.next;
        for (int i = 0; i < size ; i++) {
            node = node.next;
        }
        return node.e;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must >= 0 and <= size");
        }

        //遍历链表，找到待插入节点的前一个节点
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }


        pre.next =  new Node(element, pre.next);
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must >= 0 and < size");
        }

        //遍历链表，找到待删除节点的前一个节点
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        E e = pre.next.e;
        pre.next = pre.next.next;

        size--;
        return e;
    }

    @Override
    public void add(E... es) {
        for (E e : es) {
            add(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node node = dummyHead.next;
        while (node != null) {
            sb.append(node.e);
            node = node.next;
            if (node != null) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node node = dummyHead.next;
        while (node != null) {
            if (node.e.equals(o)) {
                return index;
            }
            index++;
            node= node.next;
        }
        return -1;
    }

    private class Node{
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", next=" + next +
                    '}';
        }
    }
}
