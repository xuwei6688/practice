package com.alg.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    private void dfs(int n, int k, List<List<Integer>> result, int start, List<Integer> tempList) {
        if (k == tempList.size()) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i <= n - (k - tempList.size()) + 1; i++) {
            tempList.add(i);
            dfs(n, k, result, i + 1, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        if (n <= 0 || k <= 0 || n < k) {
            return result;
        }

        dfs(n, k, result, 1, new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        List<List<Integer>> result = combinations.combine(4, 2);
        System.out.println(result);
    }
}
