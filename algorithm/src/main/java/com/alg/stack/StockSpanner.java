package com.alg.stack;

import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
    private final Deque<int[]> stack = new ArrayDeque<>();
    private int curDay = -1;

    public StockSpanner() {
        stack.push(new int[]{-1, Integer.MAX_VALUE});//设置-1天价格为无限大，方便编程
    }

    public int next(int price) {
        while (price >= stack.peek()[1]) {
            stack.pop();
        }
        int result = ++curDay - stack.peek()[0];
        stack.push(new int[]{curDay, price});
        return result;
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));; // 返回 1
        stockSpanner.next(80);  // 返回 1
        stockSpanner.next(60);  // 返回 1
        stockSpanner.next(70);  // 返回 2
        stockSpanner.next(60);  // 返回 1
        stockSpanner.next(75);  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
        stockSpanner.next(85);  // 返回 6
    }
}
