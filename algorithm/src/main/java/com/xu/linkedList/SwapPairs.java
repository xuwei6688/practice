package com.xu.linkedList;

import java.lang.ref.WeakReference;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *  你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 **/
public class SwapPairs {


    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.get();

        ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();
        threadLocal2.get();
        threadLocal.set(1);
        threadLocal2.set(3);
    }
}
