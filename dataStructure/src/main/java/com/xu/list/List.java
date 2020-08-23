package com.xu.list;


public interface List<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean add(E e);

    boolean remove(Object o);

    E get(int index);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    void add(E ... es);
}
