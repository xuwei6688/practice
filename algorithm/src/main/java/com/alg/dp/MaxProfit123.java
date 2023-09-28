package com.alg.dp;

import org.junit.Assert;

public class MaxProfit123 {
//    public int maxProfit(int[] prices) {
//        if (prices.length < 2) return 0;
//
//        int K = 2;
//        int[][][] profit = new int[prices.length][2][K + 1];
//
//        for (int k = 0; k <= K; k++) {
//            profit[0][0][k] = 0;
//            profit[0][1][k] = -prices[0];
//        }
//
//        for (int i = 1; i < prices.length; i++) {
//            for (int k = 1; k <= K; k++) {
//                profit[i][0][k] = Math.max(profit[i - 1][0][k], profit[i - 1][1][k] + prices[i]);
//                profit[i][1][k] = Math.max(profit[i - 1][1][k], profit[i - 1][0][k - 1] - prices[i]);
//            }
//        }
//
//        return profit[prices.length - 1][0][K];
//    }

    public int maxProfit(int[] prices) {
        int sell1 = 0;
        int sell2 = 0;
        int buy1 = Integer.MIN_VALUE;//第一笔股票花出去的钱
        int buy2 = Integer.MIN_VALUE;//第二笔股票花出去的钱
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);//第一个股票花最少的钱买入
            sell1 = Math.max(sell1, prices[i] + buy1);//第一笔股票在利润最高点卖出
            buy2 = Math.max(buy2, sell1 - prices[i]);//第二个股票在最低点买入，剩余的总利润最高
            sell2 = Math.max(sell2, prices[i] + buy2);
        }
        return sell2;
    }



    public static void main(String[] args) {
//        int[] prices1 = {3, 3, 5, 0, 0, 3, 1, 4};
//
//        MaxProfit123 maxProfit123 = new MaxProfit123();
//        int result = maxProfit123.maxProfit(prices1);
//        Assert.assertEquals(6, result);

        int[] prices1 = {1, 2, 3, 4, 5};

        MaxProfit123 maxProfit123 = new MaxProfit123();
        int result = maxProfit123.maxProfit(prices1);
        Assert.assertEquals(4, result);
    }
}
