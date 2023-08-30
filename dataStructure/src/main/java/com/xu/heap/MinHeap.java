package com.xu.heap;

import com.xu.list.ArrayList;

public class MinHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MinHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public int data() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parentIndex(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index0 doesn't have parent!");
        }
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private void swap(ArrayList<E> data, int i, int j) {
        if (i < 0 || j < 0 || i >= data.size() || j >= data.size()) {
            throw new IllegalArgumentException("i,j must > = and < size!");
        }
        E temp = data.get(i);
        data.set(data.get(j), i);
        data.set(temp, j);
    }

    /**
     * 比较 index 位置的元素和父元素大小，如果小于父元素，就将它俩交换位置
     */
    private void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parentIndex(index))) < 0) {
            swap(data, index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    /**
     * 将 index 位置处的元素下沉，直到它小于左右孩子的值
     */
    private void siftDown(int index) {
        //如果左孩子的索引没有超出数组中实际存放元素的范围，就继续比较
        while (leftChildIndex(index) < data.size()) {
            //j表示左右孩子中较小的那个的索引
            int j = leftChildIndex(index);

            //如果有右孩子，并且右孩子比左孩子小，那么将j赋值为右孩子的索引
            if (j + 1 < data.size() && data.get(j).compareTo(data.get(j + 1)) > 0) {
                j++;
            }

            //如果该元素已经比左右孩子中最大的那个小，跳出循环
            if (data.get(index).compareTo(data.get(j)) < 0) {
                break;
            }

            //将节点和它左右孩子中较小的交换
            swap(data, index, j);
            index = j;
        }
    }

    public E findMin() {
        return data.get(0);
    }

    public E extractMin() {
        E e = findMin();
        if (e == null) {
            return null;
        }
        //将数组末尾的元素移动到数组头（堆顶）
        data.set(data.get(data.size() - 1), 0);
        data.remove(data.size() - 1);

        //利用 Sift Down 操作，将堆顶的元素下沉到合适的位置
        siftDown(0);
        return e;
    }

    public void add(E e) {
        //先将元素添加到数组尾部，再用 Sift Up 操作将元素浮上去
        data.add(e);
        siftUp(data.size() - 1);
    }
}
