package com.xu.array;

/**
 * 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 **/
public class MinSubArrayLen {
    public int minSubArrayLen(int s, int[] nums) {
        int l = 0, r = -1; //初始时滑动窗口大小为0
        int sum = 0;
        int res = nums.length + 1;
        //r小于数组最大长度，说明窗口还可以往右滑动
        while (l < nums.length) {
            //sum小于s，将窗口向右扩展
            if (r + 1 < nums.length && sum < s) {
                sum += nums[++r];
                //否则，将窗口缩小一位，看看有没有更小的子数组
            }else {
                sum -= nums[l++];
            }
            if (sum >= s) {
                res = Math.min(res, r - l + 1);
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }
}
