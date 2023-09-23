package com.alg.map;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
//    public int majorityElement(int[] nums) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            int count = map.getOrDefault(num, 0) + 1;
//            if (count >  nums.length /2) {
//                return num;
//            }
//            map.put(num, count);
//        }
//        return -1;
//    }

    public int majorityElement(int[] nums) {
        int num = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            } else if(--count < 0){
                num = nums[i];
                count = 1;
            }
        }
        return num;
    }
}
