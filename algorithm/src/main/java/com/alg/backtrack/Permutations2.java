package com.alg.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Permutations2 {

    private void dfs(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!tempList.contains(nums[i])) {
                tempList.add(nums[i]);
                dfs(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), nums);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permutations2 permutations = new Permutations2();
        List<List<Integer>> result = permutations.permute(nums);
        System.out.println(result);
    }
}
