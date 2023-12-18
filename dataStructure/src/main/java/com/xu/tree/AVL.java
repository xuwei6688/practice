package com.xu.tree;

import com.xu.list.ArrayList;
import com.xu.list.List;

import java.util.LinkedList;
import java.util.Queue;

public class AVL<E extends Comparable<E>> implements BinarySearchTree<E>{
    class Node{
        E e;
        Node left;
        Node right;
        int height;

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
            this.height = 0;
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
        }else {
            node.right = add(node.right, e);
        }
        //当前树高度 = 左右子树中最高的高度 + 1
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        return maintainBalance(node);
    }

    private Node maintainBalance(Node node) {
        int balanceFactor = getBalanceFactor(node);
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right ) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    @Override
    public E removeMin() {
        if (root == null) {
            return null;
        }
        E e = minimum();
        root = remove(root, e);
        return e;
    }

    @Override
    public E removeMax() {
        if (root == null) {
            return null;
        }
        E e = maximum();
        root = remove(root, e);
        return e;
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
        Node retNode = null;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            retNode = node;
        }else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            retNode = node;
        }else {
            if (node.left == null) {
                size--;
                retNode = node.right;
            } else if (node.right == null) {
                size--;
                retNode = node.left;
            } else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.e);
                successor.left = node.left;
                retNode = successor;
            }
        }
        if (retNode == null) {
            return null;
        }
        //当前树高度 = 左右子树中最高的高度 + 1
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        return maintainBalance(retNode);
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

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    @Override
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        //为什么还需要递归看子树呢？明明该节点的左右子树高度都相同
        //这是因为虽然左右子树高度相同，但是可能左右子树是两个链表，不一定是平衡二叉树
        return isBalanced(node.left) && isBalanced(node.right);
    }


    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        //右旋过程
        y.left= T3;
        x.right = y;

        //维护高度 维护高度的顺序很重要
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    // 对接点y进行左旋转操作，返回旋转后的新的根节点x
    //      y                               x
    //     / \                            /   \
    //    T1  x                          y      z
    //       /  \                      /   \   /  \
    //      T2   z                    T1   T2  T3   T4
    //          /  \
    //         T3   T4
    public Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        //左旋过程
        y.right = T2;
        x.left = y;

        //维护高度
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }
}
