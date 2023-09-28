package com.alg.dp;

public class MaxProfit188 {
//    public int maxProfit(int K, int[] prices) {
//        int[][][] profit = new int[prices.length][K + 1][2];
//        if (prices.length < 2) {
//            return 0;
//        }
//
//        for (int k = 0; k <= K; k++) {
//            profit[0][k][0] = 0;
//            profit[0][k][1] = -prices[0];
//        }
//
//        for (int i = 1; i < prices.length; i++) {
//            for (int k = 1; k <= K; k++) {
//                profit[i][k][0] = Math.max(profit[i - 1][k][0], profit[i - 1][k][1] + prices[i]);
//                profit[i][k][1] = Math.max(profit[i - 1][k][1], profit[i - 1][k - 1][0] - prices[i]);
//            }
//        }
//
//        return profit[prices.length - 1][K][0];
//    }

    public int maxProfit(int K, int[] prices) {
        if (prices.length < 2) return 0;

        K = Math.min(K, prices.length / 2);
        int[][][] profit = new int[prices.length][2][K + 1];

        for (int k = 0; k <= K; k++) {
            profit[0][0][k] = 0;
            profit[0][1][k] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= K; k++) {
                profit[i][0][k] = Math.max(profit[i - 1][0][k], profit[i - 1][1][k] + prices[i]);
                profit[i][1][k] = Math.max(profit[i - 1][1][k], profit[i - 1][0][k - 1] - prices[i]);
            }
        }

        return profit[prices.length - 1][0][K];
    }
}
