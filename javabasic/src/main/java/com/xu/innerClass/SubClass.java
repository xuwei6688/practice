package com.xu.innerClass;

/**
 * @Author xuwei
 * @Date 2020/11/10
 * @Version V1.0
 **/
public class SubClass extends SuperClass {
    public static void main(String[] args) {
//        String a = "abc";
        String b = new String("abc");
        String a = "abc";
        System.out.println(a==b);
        System.out.println(a==b.intern());
    }
}
