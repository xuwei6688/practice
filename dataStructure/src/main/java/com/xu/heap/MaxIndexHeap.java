package com.xu.heap;

import com.xu.list.ArrayList;

/**
 * 索引堆
 *
 * 约定，对外部表现为索引从0开始
 */
public class MaxIndexHeap<E extends Comparable<E>> {
    private final ArrayList<Integer> indexes;
    private final ArrayList<E> data;
    private int count;

    public MaxIndexHeap(int capacity) {
        indexes = new ArrayList<>(capacity + 1);
        data = new ArrayList<>(capacity + 1);
        //从index=1开始记录数据
        indexes.add(0);
        data.add((E) null);
        count = 0;
    }

    private int parentIndex(int index) {
        //1是顶点， 没有父节点
        if (index <= 1 || index > count) {
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

    public void add(E e) {
        data.add(count + 1, e);
        indexes.add(count + 1, count + 1);

        count++;
        siftUp(count);
    }

    public E extractMax() {
        E result = findMax();

        indexes.set(indexes.get(count),1);

        siftDown(1);
        count--;

        return result;
    }

    public E findMax() {
        return data.get(indexes.get(1));
    }

    public void change(int i, E e) {
        i++;
        data.set(e, i);

        for (int j=1; j <= count; j++) {
            if (indexes.get(j) == i) {
                siftUp(j);
                siftDown(j);
                return;
            }
        }
    }

    private E getDataByIndex(int index) {
        if (index < 1 || index > count) {
            throw new IllegalArgumentException();
        }
        return data.get(indexes.get(index));
    }

    private void siftUp(int index) {
        while (index > 1 && getDataByIndex(index).compareTo(getDataByIndex(parentIndex(index))) > 0) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    private void siftDown(int index) {
        while (leftChildIndex(index) <= count) {
            int j = leftChildIndex(index);

            if (j + 1 <= count && getDataByIndex(j + 1).compareTo(getDataByIndex(j)) > 0) {
                j++;
            }

            if (getDataByIndex(index).compareTo(getDataByIndex(j)) > 0) {
                break;
            }

            swap(index, j);
            index = j;
        }
    }


    private void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= indexes.size() || j >= indexes.size()) {
            throw new IllegalArgumentException();
        }

        int temp = indexes.get(i);
        indexes.set(indexes.get(j), i);
        indexes.set(temp, j);
    }
}
