package com.alg.sort;

/**
 *  归并排序
 **/
public class MergeSort {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     *  对数组的[l,r]区间排序
     */
    private void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);

        merge(nums, l, r, mid);
    }

    private void merge(int[] nums, int l, int r, int mid) {
        int[] temp = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            temp[i - l] = nums[i];
        }

        int i = l; //左边数组的头元素
        int j = mid + 1; //右边数组的头元素
        for (int k = l; k <= r; k++) {
            //左边数组已经没有元素了
            if (i > mid) {
                nums[k] = temp[j - l];
                j++;
                //右边数组已经没有元素了
            } else if (j > r) {
                nums[k] = temp[i - l];
                i++;
            } else if (temp[i - l] < temp[j - l]) {
                nums[k] = temp[i - l];
                i++;
            }else{
                nums[k] = temp[j - l];
                j++;
            }
        }
    }
}
