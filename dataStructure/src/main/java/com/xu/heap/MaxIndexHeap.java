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
    private final ArrayList<Integer> reverse;

    private int count;

    public MaxIndexHeap(int capacity) {
        indexes = new ArrayList<>(capacity + 1);
        data = new ArrayList<>(capacity + 1);
        reverse = new ArrayList<>(capacity + 1);//约定reverse数组默认值为0

        //从index=1开始记录数据
        indexes.add(0);
        reverse.add(0);
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
        int i = count + 1;
        data.add(i, e);
        indexes.add(i, i);
        reverse.add(i, i);

        count++;
        siftUp(count);
    }

    public E extractMax() {
        E result = findMax();

        indexes.set(indexes.get(count), 1);
        reverse.set(0, indexes.get(count));//count位置元素被删除，因此对应0
        reverse.set(1, indexes.get(1));

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

        if (!contain(i)) {
            throw new IllegalArgumentException("index i haven't data");
        }

        int j = reverse.get(i);
        siftUp(j);
        siftDown(j);

//        for (int j=1; j <= count; j++) {
//            if (indexes.get(j) == i) {
//                siftUp(j);
//                siftDown(j);
//                return;
//            }
//        }
    }

    private boolean contain(int i) {
        return reverse.get(i) != 0;
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
            reverse.set(index, indexes.get(index));
            reverse.set(parentIndex(index), indexes.get(parentIndex(index)));
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
            reverse.set(index, indexes.get(index));
            reverse.set(j, indexes.get(j));
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
