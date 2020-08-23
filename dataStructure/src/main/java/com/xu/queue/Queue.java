package com.xu.queue;


public interface Queue<E>  {
    /**
     *  增加一个元索，如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     */
    boolean add(E e);

    /**
     *添加一个元素并返回true，如果队列已满，则返回false
     */
    boolean offer(E e);

    /**
     * 移除并返回队列头部的元素，如果队列为空，则抛出一个NoSuchElementException异常
     */
    E remove();

    /**
     * 移除并返问队列头部的元素，如果队列为空，则返回null
     */
    E poll();

    /**
     * 返回队列头部的元素，如果队列为空，则抛出一个NoSuchElementException异常
     */
    E element();

    /**
     * 返回队列头部的元素，如果队列为空，则返回null
     */
    E peek();
}
