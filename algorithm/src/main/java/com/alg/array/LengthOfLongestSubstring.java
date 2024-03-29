package com.alg.array;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 **/
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int[] cache = new int[123];
        int res = 0;
        int l = 0, r = -1;

        while (l < s.length()) {

            if (r < s.length() - 1 && cache[s.charAt(r + 1)] == 0) {
                cache[s.charAt(++r)]++;
            }else{
                cache[s.charAt(l++)]--;
            }
            res = Math.max(r - l + 1, res);
        }
        return res;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        l.lengthOfLongestSubstring("ibaz");

    }
}
