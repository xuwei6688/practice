package com.alg.recursion;

import java.util.HashMap;
import java.util.Map;

public class MyPow {
//    public double myPow(double x, int n) {
//        long N = n;
//        return N >= 0 ? pow(x, N) : 1 / pow(x, -N);
//    }
//
//    public double pow(double x, long n) {
//        if (n == 0) {
//            return 1;
//        }
//
//        double ret = pow(x, n / 2);
//        return n % 2 == 0 ? ret * ret : ret * ret * x;
//    }


    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? pow(x, N) : 1 / pow(x, -N);
    }

    public double pow(double x, long n) {
        double result = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                result *= x;
            }
            x *= x;
            n = n >> 1;
        }
        return result;
    }
}