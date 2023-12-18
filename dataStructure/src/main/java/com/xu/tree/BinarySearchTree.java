package com.xu.tree;

import com.xu.list.List;

public interface BinarySearchTree<E extends Comparable<E>> {
    boolean isEmpty();

    int size();

    void add(E e);

    E removeMin();

    E removeMax();

    void remove(E e);

    boolean contains(E e);

    List<E> preOrder();

    List<E> levelOrder();

    E minimum();

    E maximum();

    boolean isBalanced();
}
