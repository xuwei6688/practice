package com.xu.stack;

import java.util.EmptyStackException;


public interface Stack<E> {
    void push(E item);

    /**
     * 弹出栈顶元素
     * @throws EmptyStackException  if this stack is empty.
     */
    E pop();

    /**
     * 看一下栈顶元素）
     * @throws  EmptyStackException  if this stack is empty.
     */
    E peek();

    boolean isEmpty();

    int size();
}
