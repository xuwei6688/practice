package com.alg.bit;

public class HammingWeight {
//    public int hammingWeight(int n) {
//        int result = 0;
//        while (n != 0) {
//            n = n & (n - 1);
//            result++;
//        }
//        return result;
//    }


    public int hammingWeight(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                result++;
            }
            n = n >> 1;
        }
        return result;
    }
}
