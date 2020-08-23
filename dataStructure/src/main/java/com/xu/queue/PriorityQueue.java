package com.xu.queue;

import com.xu.heap.MaxHeap;

/**
 * @Author xuwei
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        maxHeap.add(e);
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return maxHeap.extractMax();
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return maxHeap.findMax();
    }
}
