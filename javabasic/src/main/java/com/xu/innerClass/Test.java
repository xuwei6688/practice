package com.xu.innerClass;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 **/
public class Test {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();

    }

    private static int resizeStamp(int n) {
//        System.out.println(Integer.toBinaryString(28));
//        System.out.println(Integer.numberOfLeadingZeros(8));
//        System.out.println(1 << 15);
//        System.out.println(Integer.toBinaryString(1 << 15));
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }
}
