package com.xu.tree;

import com.xu.list.*;

import java.util.Queue;
import java.util.LinkedList;

/**
 * 二分搜索树
 * @Author xuwei
 * @Date 2019-09-14
 * @Version V1.0
 **/
public class BST<E extends Comparable<E>> implements BinarySearchTree<E>{
   private class Node{
        E e;
        Node left;
        Node right;

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (e == null) {
            throw new IllegalStateException();
        }
        if (node == null) {
            size++;
            return new Node(e, null, null);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }else {
            node.right = add(node.right, e);
        }
        return node;
    }

    public E minimum() {
        if (root == null) {
            return null;
        }
        return minimum(root).e;
    }

    public E removeMin() {
        if (root == null) {
            return null;
        }
        E minimum = minimum();
        root =removeMin(root);
        return minimum;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public List<E> preOrder() {
        List<E> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    private void preOrder(Node node, List<E> list) {
        if (node == null) {
            return;
        }
        list.add(node.e);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    public List<E> levelOrder() {
        List<E> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            result.add(node.e);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return result;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        //没找到值为 e 的节点
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }else {
            if (node.left == null) {
                size--;
                return node.right;
            }
            if (node.right == null) {
                size--;
                return node.left;
            }
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            return successor;
        }
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public E removeMax() {
        if (root == null) {
            return null;
        }
        E e = maximum();
        root = removeMax(root);
        return e;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    @Override
    public E maximum() {
        if (root == null) {
            return null;
        }
        return maximum(root).e;
    }
}