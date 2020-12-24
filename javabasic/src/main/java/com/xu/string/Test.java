package com.xu.string;

/**
 * @Author xuwei
 * @Date 2020/11/28
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        System.out.println(str1.intern());
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
