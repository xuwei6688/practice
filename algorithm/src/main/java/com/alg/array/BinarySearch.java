package com.alg.array;

import org.junit.Assert;

/**
 * 二分查找法
 * @Author xuwei
 * @Date 2020/9/12 0012
 * @Version V1.0
 **/
public class BinarySearch {

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();

        int[] nums = {-1, 0, 3, 5, 9, 12};
        Assert.assertEquals(4, binarySearch.search(nums, 9));

        int[] nums2 = {-1,0,3,5,9,12};
        Assert.assertEquals(-1, binarySearch.search(nums2, 2));
    }
}
