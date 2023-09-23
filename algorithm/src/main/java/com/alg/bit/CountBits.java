package com.alg.bit;

public class CountBits {
//    public int[] countBits(int n) {
//        int[] results = new int[n + 1];
//        for (int i = 0; i <= n; i++) {
//            results[i] = countBit(i);
//        }
//        return results;
//    }
//
//    private int countBit(int m) {
//        int result = 0;
//        while (m != 0) {
//            m = m & (m - 1);
//            result++;
//        }
//        return result;
//    }

//    public int[] countBits(int n) {
//        int[] counts = new int[n + 1];
//        counts[0] = 0;
//        if (n == 0) {
//            return counts;
//        }
//
//        for (int i = 1; i <= n; i++) {
//            counts[i] = counts[i & (i - 1)] + 1;
//        }
//        return counts;
//    }


    public int[] countBits(int n) {
        int[] results = new int[n + 1];
        results[0] = 0;

        for (int i = 0; i <= n; i++) {
            if ((i & 1) == 0) {
                results[i] = results[i >> 1];
            }else {
                results[i] = results[i - 1] + 1;
            }
        }
        return results;
    }
    public static void main(String[] args) {
        CountBits countBits = new CountBits();
        int []result = countBits.countBits(5);
        System.out.println(result);
    }
}
