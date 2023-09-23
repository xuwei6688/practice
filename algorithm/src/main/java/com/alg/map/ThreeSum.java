package com.alg.map;

import java.util.*;

public class ThreeSum {
//    public List<List<Integer>> threeSum(int[] nums) {
//        Arrays.sort(nums);
//        Set<List<Integer>> set = new HashSet<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            int left = i + 1;
//            int right = nums.length - 1;
//            while (left < right) {
//                int sum = nums[i] + nums[left] + nums[right];
//                if (sum == 0) {
//                    //结果放入set中保证了不重复
//                    set.add(Arrays.asList(nums[i], nums[left], nums[right]));
//                    left++;
//                    right--;
//                } else if (sum < 0) {
//                    left ++;
//                } else if (sum > 0) {
//                    right--;
//                }
//            }
//        }
//
//        return new ArrayList<>(set);
//    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                continue;
            }
            //越过相同元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //越过相同元素
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> list = threeSum.threeSum(nums);
        System.out.println(list);
    }

}
