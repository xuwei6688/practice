package com.xu.list;

public class ArrayList<E> implements List<E> {
    private Object[] elements;
    private int size;
    private int capacity;

    private static final int DEFAULT_CAPACITY = 10;
    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must > 0");
        }
        this.elements = new Object[capacity];
        this.size = 0;
        this.capacity =  capacity;
    }

    public ArrayList(E[] arr) {
        this.elements = new Object[arr.length];
        this.capacity = arr.length;
        this.size = 0;
        add(arr);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public boolean add(E e) {
         add(size, e);
         return true;
    }

    @Override
    public boolean remove(Object o) {
         remove(indexOf(o));
         return true;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index must >= 0 and <= size");
        }

        //检查容量
        if (size == capacity) {
            resize(capacity * 2);
        }

        if (isEmpty()) {
            elements[0] = e;
            size++;
            return;
        }

        for (int i = size -1; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[size] = e;
        size++;
    }


    public void resize(int newCapacity) {
        Object[] newElements = new Object[newCapacity];

        System.arraycopy(elements, 0, newElements, 0, size());

        elements = newElements;
        capacity = newCapacity;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E)elements[index];
    }

    public int indexOf(Object e) {
        if (e == null) {
            throw new IllegalArgumentException("e must be not null!");
        }
        for (int i = 0; i < size(); i++) {
            if (e.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    public void add(E ...elements) {
        for (E e : elements) {
            add(e);
        }
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must >= 0 and < size");
        }
        E e = (E)elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;

        //如果实际使用容量是总容量的四分之一，那么缩减容量为原来的二分之一，这样可以预留出四分之一的容量，防止复杂度震荡。
        if (size == getCapacity() / 4) {
            resize(getCapacity() / 2);
        }
        return e;
    }

    public void set(E e, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index mus >= 0 and < size");
        }
        elements[index] = e;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private int getCapacity() {
        return elements.length;
    }
}
