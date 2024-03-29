package com.alg.array;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 **/
public class TwoSum {
    //利用"对撞指针的思想"
    //两个索引分别从左右两侧向中间移动；
    //移动的过程中两个索引位置的数字和相加有三种可能：
    //1 相等 2 小于目标元素 3 大于目标元素
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                res[0] = ++l;
                res[1] = ++r;
                return res;
            } else if (sum > target) {
                r--;
            }else {
                l++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.twoSum(new int[]{2, 7, 11, 15},9);
    }
}
