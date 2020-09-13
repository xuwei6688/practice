package com.xu.array;

import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 **/
public class FindKthLargest2 {
    public int findKthLargest(int[] nums, int k) {
       int l = 0, r = nums.length - 1;
        int target = nums.length - k;
        while (true) {
            int index = partition(nums, l, r);
            if (index == target) {
                return nums[index];
            } else if (target > index) {
                l = ++index;
            }else{
                r = --index;
            }
        }
    }

    private int partition(int[] nums, int l, int r) {
        swap(nums, l, new Random().nextInt(r - l + 1) + l);

        int v = nums[l];

        int j = l;
        //[l+1,j] 中的元素 < v [j+1,i) 中的元素 > v
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] < v) {
                swap(nums, ++j, i);
            }
        }
        swap(nums, l, j);
        return j;
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
