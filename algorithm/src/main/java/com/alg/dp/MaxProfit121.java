package com.alg.dp;

import org.junit.Assert;

public class MaxProfit121 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfile = 0;

        for (int price : prices) {
            maxProfile = Math.max(maxProfile, price - minPrice);

            minPrice = Math.min(minPrice, price);
        }
        return maxProfile;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        MaxProfit121 maxProfit121 = new MaxProfit121();
        int result = maxProfit121.maxProfit(prices);
        Assert.assertEquals(5, result);


        int[] prices2 = {7, 6, 4, 3, 1};
        result = maxProfit121.maxProfit(prices2);
        Assert.assertEquals(0, result);
    }
}
