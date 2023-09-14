package com.alg.dp;

/**
 * 动态规划：自低向上
 */
public class Fib2 {

    public int fib(int n) {
        Integer[] memory = new Integer[n + 1];

        memory[0] = 0;
        memory[1] = 1;

        for (int i = 2; i <= n; i++) {
            memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n];
    }

    public static void main(String[] args) {
        Fib2 fib = new Fib2();
        long start = System.currentTimeMillis();
        int result = fib.fib(43);
        long total = System.currentTimeMillis() - start;
        System.out.println(result);
        System.out.println("耗时：" + total);

    }
}
