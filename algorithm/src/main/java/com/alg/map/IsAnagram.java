package com.alg.map;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram {
//    public boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) {
//            return false;
//        }
//
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            map.put(c, map.getOrDefault(c, 0) + 1);
//        }
//
//        for (int i = 0; i < t.length(); i++) {
//            char c = t.charAt(i);
//            Integer count = map.getOrDefault(c, 0);
//            count--;
//            if (count < 0) {
//                return false;
//            }else {
//                map.put(c, count);
//            }
//        }
//        return true;
//    }
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a'] ++;
        }

        for (int i = 0; i < t.length(); i++) {
            chars[t.charAt(i) - 'a'] --;
            if (chars[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        IsAnagram isAnagram = new IsAnagram();
        boolean result = isAnagram.isAnagram(s, t);
        System.out.println(result);
    }
}
