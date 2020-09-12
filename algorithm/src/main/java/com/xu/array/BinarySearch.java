package com.xu.array;

/**
 * 二分查找法
 * @Author xuwei
 * @Date 2020/9/12 0012
 * @Version V1.0
 **/
public class BinarySearch {

    public  int search(int[] array, int target) {
        //在[l,r]区间内搜索target
        int l = 0;
        int r = array.length -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
