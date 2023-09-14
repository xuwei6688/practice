package com.alg.dp;

/**
 * 记忆化搜索解决斐波那契数列
 * 记忆化搜索：自顶向下
 */
public class Fib {
    private Integer [] memory;

    public int fib(int n) {
        if (memory == null) {
            memory = new Integer[n + 1];
        }

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        if ( memory[n] == null) {
           memory[n] = fib(n - 1) + fib(n - 2);
        }
        return memory[n];
    }

    //n=2 result=1+0=1
    //n=3 result=fib(2)+1=2
    //n=4 result=fib(3)+fib(2)=3
    //n=5 result=fib(4)+fib(3)=5
    //n=6 result=fib(5)+fib(4)=8
    //n=6 result=fib(5)+fib(4)=8
    //n=43 result= 433494437
    public static void main(String[] args) {
        Fib fib = new Fib();
        long start = System.currentTimeMillis();
        int result = fib.fib(43);
        long total = System.currentTimeMillis() - start;
        System.out.println(result);
        System.out.println("耗时：" + total);

    }
}
