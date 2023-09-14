package com.alg.dp;

/**
 * 198. 打家劫舍
 */
public class Rob {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] s = new int[nums.length + 1];
        s[0] = nums[0];
        s[1] = Math.max(nums[0], nums[1]);

        //状态转移公式 S(n) = max(S(n-1), S(n-2)+H(n))
        for (int i = 2; i < nums.length; i++) {
            s[i] = Math.max(s[i - 1], s[i - 2] + nums[i]);
        }
        return s[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        Rob rob = new Rob();
        int result = rob.rob(nums);
        System.out.println(result);
    }
}
