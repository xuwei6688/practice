package com.xu.heap;

import java.util.Arrays;

public class MinIndexHeap <E extends Comparable<E>> {
    private final Integer[] indexes;
    private final E[] data;
    private final Integer[] reverse;

    private int count;

    public MinIndexHeap(int capacity) {
        indexes = new Integer[capacity + 1];
        data = (E[])new Comparable[capacity + 1];
        reverse =  new Integer[capacity + 1];//约定reverse数组默认值为0
        Arrays.fill(reverse, 0);

        count = 0;
    }

    private int parentIndex(int index) {
        //1是顶点， 没有父节点
        if (index <= 1) {
            throw new IllegalArgumentException();
        }

        return index / 2;
    }

    private int leftChildIndex(int index) {
        return 2 * index;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 1;
    }

    //元素个数
    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void add(E e) {
        insert(count, e);
    }


    public void insert(int index, E e) {
        index++;
        data[index] = e;
        indexes[count + 1] = index;//这行代码很关键😳，我们要让indexes数组元素是连续的
        reverse[index] = count + 1;

        count++;
        siftUp(count);
    }

    public E extractMin() {
        E result = findMin();

        indexes[1] = indexes[count];
        reverse[indexes[count]] = 0;//count位置元素被删除，因此对应0
        reverse[indexes[1]] = 1;

        siftDown(1);
        count--;

        return result;
    }

    public int extractMinIndex() {
        Integer result = indexes[1];

        indexes[1] = indexes[count];
        reverse[indexes[count]] = 0;//count位置元素被删除，因此对应0
        reverse[indexes[1]] = 1;

        siftDown(1);
        count--;

        return --result;
    }

    public E findMin() {
        return data[indexes[1]];
    }

    public void change(int i, E e) {
        i++;//因为接口对外index是从0开始，而我们内部实现是从1开始记录，因此需要i++
        data[i] = e;

        if (!contain(i)) {
            throw new IllegalArgumentException("index i haven't data");
        }

        int j = reverse[i];
        siftUp(j);
        siftDown(j);
    }

    private boolean contain(int i) {
        return reverse[i] != 0;
    }

    private E getDataByIndex(int index) {
        if (index < 1) {
            throw new IllegalArgumentException();
        }
        return data[indexes[index]];
    }

    private void siftUp(int k) {
        while (k > 1 && data[indexes[k]].compareTo(data[indexes[k / 2]]) < 0) {
            swap(k, parentIndex(k));

            reverse[indexes[k]] = k;
            reverse[indexes[parentIndex(k)]] = parentIndex(k);
            k = parentIndex(k);
        }
    }


    private boolean less(int i, int j) {
        if (indexes[i] == 0 || data[indexes[i]] == null) return false;
        if (indexes[j] == 0 || data[indexes[j]] == null) return true;
        return data[indexes[i]].compareTo(data[indexes[j]]) < 0;
    }

    private void siftDown(int index) {
        while (leftChildIndex(index) <= count) {
            int j = leftChildIndex(index);

            if (j + 1 <= count && getDataByIndex(j + 1).compareTo(getDataByIndex(j)) < 0) {
                j++;
            }

            if (getDataByIndex(index).compareTo(getDataByIndex(j)) < 0) {
                break;
            }

            swap(index, j);
            reverse[indexes[index]] = index;
            reverse[indexes[j]] = j;
            index = j;
        }
    }


    private void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= indexes.length || j >= indexes.length) {
            throw new IllegalArgumentException();
        }

        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;
    }
}
