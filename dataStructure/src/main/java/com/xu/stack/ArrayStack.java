package com.xu.stack;

import com.xu.list.ArrayList;
import java.util.EmptyStackException;

/**
 * 使用 ArrayList 实现栈，为了降低时间复杂度，使用 ArrayList 的尾部作为栈顶
 **/
public class ArrayStack<E> implements Stack<E> {
    private ArrayList<E> list;

    public ArrayStack(int capacity) {
        list = new ArrayList<>(capacity);
    }

    @Override
    public void push(E item) {
        list.add(item);
    }

    @Override
    public E pop() {
        if (list.size() == 0) {
            throw new EmptyStackException();
        }
        return list.remove(size() - 1);
    }

    @Override
    public E peek() {
        if (list.size() == 0) {
            throw new EmptyStackException();
        }
        return list.get(size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }
}
