package com.xu.tree;

import com.xu.list.ArrayList;
import com.xu.list.List;

/**
 * @Author xuwei
 * @Date 2020/8/22
 * @Version V1.0
 **/
public class BST<E extends Comparable<E>> {
    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
       root =  add(root, e);
    }

    private Node add(Node node, E e) {
        if (e == null) {
            throw new IllegalArgumentException("e must not be null");
        }
        //1.说明递归到头了，元素 e 在二叉树中不存在
        if (node == null) {
            node = new Node(e, null, null);
            size++;
            return node;
        }

       if (e.compareTo(node.e) < 0) {
            node.left =  add(node.left, e);
        }else{
            node.right = add(node.right, e);
        }
        return node;
    }

    public List<E> preOrder() {
        List<E> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    /**
     * 前序遍历顺序：根 -> 左 -> 右
     * 利用递归，先将根节点存到list中，然后再对左子树调用 preOrder，最后再对右子树调用 preOrder
     */
    private void preOrder(Node node, List<E> list) {
        if (node == null) {
            return;
        }
        list.add(node.e);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    public boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.equals(node.e)) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        }else{
            return contains(node.right, e);
        }
    }

    /**
     * 删除最小节点
     */
    public E removeMin() {
        E minimum = minimum();
        root = removeMin(root);
        return minimum;
    }

    /**
     * 删除以 node 为根节点的子树中最小元素，返回删除后的子树根节点
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }

        return removeMin(node.left);
    }

    /**
     * 删除最大点
     */
    public E removeMax() {
        E maximum = maximum();
        root = removeMax(root);
        return maximum;
    }

    /**
     * 删除以 node 为根节点的子树中最大元素，返回删除后的子树根节点
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        return removeMax(node.right);
    }

    /**
     * 删除任意元素
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除以 node 为根节点的子树中的元素 e,并返回删除后的子树的跟节点
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) >0) {
            node.right = remove(node.right, e);
            return node;
        } else{
            //真正删除元素
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            }else{
                //待删除节点的左右子树都不为空
                //1.找到待删除节点 d 的后继节点 s（右子树中最小的节点或左子树中最大的节点）
                //2.s.right=removeMin(d.right);
                //3.s.left=d.left;

                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                return successor;
            }
        }
    }


    public E minimum(){
        return minimum(root).e;
    }

    public E maximum() {
        return maximum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }




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
}
