package com.alg.dp;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                //换i块钱的最少次数等于换 i-{conis} 块钱的最小次数 + 1
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i - coins[j]]  + 1, dp[i]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        CoinChange coinChange = new CoinChange();
        int result = coinChange.coinChange(coins, 11);
        Assert.assertEquals(3, result);
    }
}
