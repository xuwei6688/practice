package com.xu.sort;


import java.util.Random;

public class QuickSort {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l > r) {
            return;
        }
        int p =  partition(nums, l, r);
        quickSort(nums, l, p - 1);
        quickSort(nums, p + 1, r);
    }

    //对[l,r]做partition操作
    //返回一个索引位置j，使得[l,j-1] <nums[j] 并且 [j+1,r]>nums[j]
    private int partition(int[] nums, int l, int r) {
        swap(nums, l, new Random().nextInt(r - l + 1) + l);
        int v = nums[l];
        int j = l;
        //[l+1,j] < v 并且 [j+1,i) > v
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
