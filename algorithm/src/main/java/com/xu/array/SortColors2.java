package com.xu.array;


public class SortColors2 {
    /**
     * 三路快排
     * 将数组分成三部分 [0,zero] 存 0 ；[zero + 1, i - 1] 存 1；[two, n - 1]存2
     * */
    public void sortColors(int[] nums) {
        int zero = -1; //[0,zero] == 0
        int two = nums.length;//[two,nums.length-1]==2

        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, --two);
            }else{
                swap(nums, i, ++zero);
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
