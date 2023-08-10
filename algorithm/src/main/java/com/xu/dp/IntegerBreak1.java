package com.xu.dp;

import java.util.Arrays;

/**
 * 343. 整数拆分
 * leetcode：https://leetcode.cn/problems/integer-break/
 * 题解：https://www.yuque.com/wozaiyinshen/vtwlns/acszv4dfk31r4elg
 */
public class IntegerBreak1 {

    private int []memo;

    private int breakInteger(int n) {
        if (n == 1) {
            return 1;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int maxSum = -1;
        for (int i = 1; i <= n - 1; i++) {
            maxSum = max(maxSum, i * breakInteger(n - i), i * (n - i));
        }
        memo[n] = maxSum;
        return maxSum;
    }

    public int integerBreak(int n) {
        memo = new int[n + 2];
        Arrays.fill(memo, -1);
        return breakInteger(n);
    }

    private static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        IntegerBreak1 integerBreak1 = new IntegerBreak1();
        int result = integerBreak1.integerBreak(10);
        System.out.println(result);
    }
}
