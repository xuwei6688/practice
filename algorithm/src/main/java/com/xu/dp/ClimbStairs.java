package com.xu.dp;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        //index i 存爬到 i 阶有多少种方法
        int[] memory = new int[n + 1];


        memory[1] = 1;
        if (n >= 2) {
            memory[2] =2;
        }


        for (int i = 3; i <=n ; i++) {
            //要爬上i阶台阶有两种方法：
            //1.从i-1阶台阶爬上来
            //2.从i-2阶台阶爬上来
            //只需相加即可
            memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n];
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        climbStairs.climbStairs(1);
    }
}
