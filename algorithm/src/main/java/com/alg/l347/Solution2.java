package com.alg.l347;

import java.util.*;

/**
 * @Author xuwei
 * @Date 2020/8/23
 * @Version V1.0
 **/
public class Solution2 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            }else {
                map.put(nums[i], map.get(nums[i])+ 1);
            }
        }


        //桶排序，将频率作为数组下标，值为元素
        List<Integer>[] list = new List[nums.length + 1];
        for (Integer key : map.keySet()) {
            Integer index = map.get(key);
            if (list[index] == null) {
                list[index] = new ArrayList<>();
            }
            list[index].add(key);
        }

        int[] res = new int[k];
        int index = 0;
        //从数组末尾遍历
        for (int i = list.length - 1; i >=0 && index < k; i--) {
            if(list[i] == null) continue;
            for (Integer j : list[i]) {
                res[index++] = j;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution2 l347 = new Solution2();
        int[] nums = {1, 1, 1, 2, 2, 3};
        l347.topKFrequent(nums, 2);
    }
}
