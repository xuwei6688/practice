package com.alg.perDay;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 2022-10-9
 *
 * 2578. 最小和分割
 */
public class SplitNum {
    public int splitNum(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        Arrays.sort(nums);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                sb1.append(nums[i]);
            }else {
                sb2.append(nums[i]);
            }
        }
        return Integer.parseInt(sb1.toString()) + Integer.parseInt(sb2.toString());
    }

    public static void main(String[] args) {
        SplitNum splitNum = new SplitNum();
        Assert.assertEquals(59, splitNum.splitNum(4325));
        Assert.assertEquals(75, splitNum.splitNum(687));
    }
}
