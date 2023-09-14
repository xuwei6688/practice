package com.alg.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46.全排列
 * https://leetcode.cn/problems/permutations/
 */
public class Permutations {
    private boolean[] used ;
    private List<List<Integer>> result = new ArrayList<>();

    private void dfs(int[] nums, int index, List<Integer> p) {
        if (index == nums.length) {
            result.add(new ArrayList<>(p));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                p.add(nums[i]);
                used[i] = true;
                dfs(nums, index + 1, p);
                p.remove(p.size() - 1);
                used[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }

        used = new boolean[nums.length];
        Arrays.fill(used, false);

        dfs(nums, 0, new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permutations permutations = new Permutations();
        List<List<Integer>> result = permutations.permute(nums);
        System.out.println(result);
    }
}
