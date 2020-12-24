package com.xu.string;

/**
 * 344. 反转字符串
 **/
public class Reverse {
    public void reverseString(char[] s) {
        for(int i = 0; i < s.length / 2; i++){
            char t = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = t;
        }
    }

    public static void main(String[] args) {

    }
}
