package com.xu.innerClass;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 **/
public class Test {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(3);
        queue.add(1);
        queue.add(3);
        queue.add(2);
        System.out.println(queue);
    }

    private static int resizeStamp(int n) {
//        System.out.println(Integer.toBinaryString(28));
//        System.out.println(Integer.numberOfLeadingZeros(8));
//        System.out.println(1 << 15);
//        System.out.println(Integer.toBinaryString(1 << 15));
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }
}
