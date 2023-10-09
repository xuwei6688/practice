package com.alg.heap;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class StockPrice {
    private int current = -1;
    //最高价格
    private final PriorityQueue<int[]> max = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
    //最低价格
    private final PriorityQueue<int[]> min = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

    private final Map<Integer, Integer> map = new HashMap<>();

    public StockPrice() {

    }

    public void update(int timestamp, int price) {
        int[] tp = {timestamp, price};
        map.put(timestamp, price);

        max.add(tp);
        min.add(tp);

        if (timestamp>current) current = timestamp;
    }

    public int current() {
        return map.get(current);
    }

    public int maximum() {
        while (true) {
            int[] peek = max.peek();
            if (peek[1] != map.get(peek[0])) {
                max.poll();
            }else {
                return peek[1];
            }
        }
    }

    public int minimum() {
        while (true) {
            int[] peek = min.peek();
            if (peek[1] != map.get(peek[0])) {
                min.poll();
            }else {
                return peek[1];
            }
        }
    }

    public static void main(String[] args) {
//        TP tp1 = new TP(1, 10);
//        TP tp2 = new TP(2, 5);
//        TP tp3 = new TP(3, 2);
//        TP tp4 = new TP(4, 8);
//
//        StockPrice stockPrice = new StockPrice();
//        PriorityQueue<TP> pqTimestamp = stockPrice.pqMinimum;
//        pqTimestamp.add(tp1);
//        pqTimestamp.add(tp2);
//        pqTimestamp.add(tp3);
//        pqTimestamp.add(tp4);
//        TP poll1 = pqTimestamp.poll();
//        TP poll2 = pqTimestamp.poll();
//        TP poll3 = pqTimestamp.poll();
//        TP poll4 = pqTimestamp.poll();
//        System.out.println("sss");

        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
        stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
        Assert.assertEquals(5, stockPrice.current());     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
        Assert.assertEquals(10, stockPrice.maximum());     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
        stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
        // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
        Assert.assertEquals(5, stockPrice.maximum());     // 返回 5 ，更正后最高价格为 5 。
        stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
        Assert.assertEquals(2, stockPrice.minimum());     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
    }
}
