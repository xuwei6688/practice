package com.alg.array;


public class MoveZeroes2 {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int pos = 0; //[0,pos) 区间的数字都不为0
        for (int num : nums) {
            if (num != 0) nums[pos++] = num;
        }
        for (int i = pos; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
