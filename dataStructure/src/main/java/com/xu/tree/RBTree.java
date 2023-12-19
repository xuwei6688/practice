package com.xu.tree;



import com.xu.list.ArrayList;
import com.xu.list.List;

import java.util.LinkedList;
import java.util.Queue;

public class RBTree<E extends Comparable<E>> implements BinarySearchTree<E>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        E e;
        Node left;
        Node right;
        boolean color;

        public Node(E e) {
            this(e, null, null);
        }

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
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
        root.color = BLACK; //根节点为黑色的
    }

    private Node add(Node node, E e) {
        if (e == null) {
            throw new IllegalStateException();
        }
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }else {
            node.right = add(node.right, e);
        }

        //当前节点是 2 节点，并且插入节点在当前节点右侧 --》左旋
        //还有种情况是插入到了3节点中间，也会进入这里先左旋一下子
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        //当前节点的左孩子和左孩子的左孩子都是红色，说明插入节点插入到了 3 节点的左侧 --》右旋
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        //当前节点的左孩子和右孩子都是红色的 --》翻转颜色
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
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
       return null;
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
        return null;
    }

    private Node removeMax(Node node) {
        return null;
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

    @Override
    public boolean isBalanced() {
        return false;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //  将节点 x 插入到一个 2 节点上，形成 3 节点，节点 x 在根节点右侧的情况
    //      node                        x
    //     /    \       左旋转         /   \
    //    T1     x    --------->    node   T3
    //         /   \                /   \
    //        T2    T3             T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //       node                     x
    //      /   \      右旋转        /   \
    //      x    T2  --------->    y    node
    //    /   \                        /   \
    //   y     T1                     T1    T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public List<E> inOrder() {
        List<E> result = new ArrayList<>();

        inOrder(root, result);
        return result;
    }

    private void inOrder(Node node, List<E> result) {
        if (node == null) {
            return;
        }
        inOrder(node.left, result);
        result.add(node.e);
        inOrder(node.right, result);
    }
}
