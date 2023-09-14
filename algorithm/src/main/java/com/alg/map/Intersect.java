package com.alg.map;

import java.util.*;

/**
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 *
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 *
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 **/
public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            return intersect(nums2, nums1);
        }

        //存放nums1中每个元素出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : nums1) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        int i = 0;
        for (int n : nums2) {
            int count = map.getOrDefault(n, 0);
            if (count > 0) {
                nums2[i++] = n;
                map.put(n, map.get(n) -1);
            }
        }

        return Arrays.copyOfRange(nums2, 0, i);
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;//nums1的索引
        int j = 0;//nums2的索引
        int k = 0;//待返回数组的素银

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            }else{
                nums1[k++] = nums1[i++];
                j++;
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);
    }

    public static void main(String[] args) {
        Intersect intersect = new Intersect();
        int[] intersect1 = intersect.intersect2(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        int[] intersect2 = intersect.intersect2(new int[]{4,9,5}, new int[]{9,4,9,8,4});
        System.out.println("====");

    }
}
