package com.alg.dp;

public class MaxProduct {
    public int maxProduct(int[] nums) {
        int result = nums[0];
        int min = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = min;
                min = max;
                max = tmp;
            }

            min = Math.min(min * nums[i], nums[i]);
            max = Math.max(max * nums[i], nums[i]);

            result = Math.max(max, result);
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 3, -2, 4};
//        MaxProduct maxProduct = new MaxProduct();
//        int reult = maxProduct.maxProduct(nums);
//        System.out.println(reult);

        int[] nums = {-2, };
        MaxProduct maxProduct = new MaxProduct();
        int reult = maxProduct.maxProduct(nums);
        System.out.println(reult);
    }
}
