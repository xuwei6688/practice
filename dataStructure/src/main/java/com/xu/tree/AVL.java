package com.xu.tree;

import com.xu.list.ArrayList;
import com.xu.list.List;

import java.util.LinkedList;
import java.util.Queue;

public class AVL<E extends Comparable<E>> implements BinarySearchTree<E>{
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


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }

        if (node == null) {
            size++;
            return new Node(e, null, null);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
            return node;
        }else {
            node.right = add(node.right, e);
            return node;
        }
    }

    @Override
    public E removeMin() {
        if (root == null) {
            return null;
        }
        E e = minimum();
        root = removeMin(root);
        return e;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
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

    @Override
    public E maximum() {
        if (root == null) {
            return null;
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    @Override
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            } else {
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                return successor;
            }
        }
    }

    @Override
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        }else if (e.compareTo(node.e) > 0){
            return contains(node.right, e);
        }
        return true;
    }

    @Override
    public List<E> preOrder() {
        List<E> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node node, List<E> result) {
        if (node == null) {
            return;
        }
        result.add(node.e);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

    @Override
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
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return result;
    }

    @Override
    public E minimum() {
        if (root == null) {
            return null;
        }

        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }
}
