package com.alg.heap;

import java.util.*;

public class MaxSlidingWindow {

    /**
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     *  n=8 k=1     result=8
     *  n=8 k=2     result=7
     *  n=8 k=3     result=6
     */
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
//        int[] result = new int[nums.length - k + 1];
//
//        for (int i = 0; i < nums.length; i++) {
//            priorityQueue.add(nums[i]);
//            if (priorityQueue.size() == k) {
//                result[i - k + 1] = priorityQueue.peek();
//                priorityQueue.remove(nums[i - k + 1]);
//            }
//        }
//        return result;
//    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        Deque<Integer> deque = new ArrayDeque<>();
        int v = 0;
        for (int right = 0; right < nums.length; right++) {
            //维持滑动窗口大小为k
            while (!deque.isEmpty() && deque.peek() < right - k + 1) {
                deque.poll();
            }
            //维持单调递减的双端队列
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                deque.pollLast();
            }

            deque.offer(right);
            if (right >= k - 1) {
                result[v++] = nums[deque.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        int[] result = maxSlidingWindow.maxSlidingWindow(nums, 3);
        System.out.println(result);
    }
}
