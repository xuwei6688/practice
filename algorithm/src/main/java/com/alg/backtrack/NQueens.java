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
    private boolean []col;//存纵列的放置情况
    private boolean []disa1;//存左下到右上的放置情况
    private boolean []disa2;
    private List<List<String>> result = new ArrayList<>();//存放结果


    /**
     * @param index 当前遍历到第几行
     * @param n  n*n的横纵
     * @param rows rows[1]=4 说明第二行第四列存放皇后
     */
    private void dfs(int index, int n, int[] rows) {
        if (index == n) {
            result.add(generate(rows));
            return;
        }

        for (int i = 0; i < rows.length; i++) {
            //同一列、两个斜着的纵列都没有放置过
            if (!col[i] && !disa1[index + i] && !disa2[i - index + n - 1]) {
                rows[index] = i;

                col[i] = true;
                disa1[index + i] = true;
                disa2[i - index + n - 1] = true;

                dfs(index+1, n, rows);

                col[i] = false;
                disa1[index + i] = false;
                disa2[i - index + n - 1] = false;
            }
        }
    }

    private List<String> generate(int[] rows) {
        List<String> result = new ArrayList<>();
        for (int j : rows) {
            char[] row = new char[rows.length];
            Arrays.fill(row, '.');
            row[j] = 'Q';
            result.add(new String(row));
        }
        return result;
    }
    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        disa1 = new boolean[2 * n - 1];
        disa2 = new boolean[2 * n - 1];

        dfs(0, n, new int[n]);
        return result;
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
