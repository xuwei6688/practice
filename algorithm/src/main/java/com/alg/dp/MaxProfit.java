package com.alg.dp;

public class MaxProfit {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            //卖
            if (i > 0) {
                if (prices[i - 1] < prices[i]) {
                    totalProfit += prices[i] - prices[i - 1];
                }
            }
        }
        return totalProfit;
    }
}
