package com.alg.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 * 题目描述：https://leetcode.cn/problems/n-queens/
 * 题解：https://www.yuque.com/wozaiyinshen/vtwlns/wdws0sgtuffy53gs
 */
public class NQueens {

    private List<List<String>> res = new ArrayList<>();
    boolean []col;
    boolean []dias1;
    boolean []dias2;

    /**
     * n皇后，第index行，皇后存放在row[index]列
     */
    private void dfs(int n, int index, int[] row) {
        if (index == n) {
            res.add(generate(row));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!col[i] && !dias1[index + i] && !dias2[i - index + n - 1]) {
                row[index] = i;
                col[i] = true;
                dias1[index + i] = true;
                dias2[i - index + n - 1] = true;

                dfs(n, index+1, row);

                col[i] = false;
                dias1[index + i] = false;
                dias2[i - index + n - 1] = false;
                row[index] = 0;
            }
        }
    }

    private List<String> generate(int[] rows) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            char[] row = new char[rows.length];
            Arrays.fill(row, '.');
            row[rows[i]] = 'Q';
            result.add(new String(row));
        }
        return result;
    }

    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        dias1 = new boolean[2 * n - 1];
        dias2 = new boolean[2 * n - 1];

        dfs(n, 0, new int[n]);
        return res;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> result = nQueens.solveNQueens(8);
        for (List<String> list : result) {
            for (String row : list) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
