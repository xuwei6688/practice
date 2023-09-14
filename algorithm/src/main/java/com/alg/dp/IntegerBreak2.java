package com.alg.dp;

import java.util.Arrays;

/**
 * 动态规划解法
 */
public class IntegerBreak2 {
    private int [] memo;

    public int integerBreak(int n) {
        memo = new int[n + 2];
        Arrays.fill(memo, -1);

        memo[1] = 1;

        //自底向上，依次计算 2...n的整数拆分最大乘积
        for (int i = 2; i <= n; i++) {
            //计算整数 i 拆分的最大乘积，考虑整数i至少会拆分出来j
            int maxNum = -1;
            for (int j = 1; j <= i - 1; j++) {
                maxNum = max(maxNum, j * (i - j), j * memo[i - j]);
            }
            memo[i] = maxNum;
        }
        return memo[n];
    }

    private static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
}
