package com.alg.dp;

import java.util.Arrays;

public class LengthOfLIS {
//    public int lengthOfLIS(int[] nums) {
//        int[] lis = new int[nums.length];
//        Arrays.fill(lis, 1);
//
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[j] < nums[i]) {
//                    lis[i] = Math.max(lis[i], lis[j] + 1);
//                }
//            }
//        }
//
//        int result = lis[0];
//        for (int i = 1; i < lis.length; i++) {
//            result = Math.max(result, lis[i]);
//        }
//        return result;
//    }


    public int lengthOfLIS(int[] nums) {
        int[] order = new int[nums.length];
        int tail = 0;

        for (int num : nums) {
            int l = 0;
            int r = tail;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (order[mid] < num) {
                    l = mid + 1;
                }else {
                    r = mid;
                }
            }
            order[l] = num;
            if (tail == r) {
                tail++;
            }
        }
        return tail;
    }
}
