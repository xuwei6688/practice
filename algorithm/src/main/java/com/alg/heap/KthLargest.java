package com.alg.heap;

import java.util.PriorityQueue;

class KthLargest {
    private final PriorityQueue<Integer> priorityQueue;
    private final int capacity;

    public KthLargest(int k, int[] nums) {
        capacity = k;
        priorityQueue = new PriorityQueue<>(capacity);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {

        priorityQueue.add(val);

        while (priorityQueue.size() > capacity) {
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        //[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
        int[] nums = {4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(3, nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
    }
}
