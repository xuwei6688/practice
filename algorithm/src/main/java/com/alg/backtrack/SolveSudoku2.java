package com.alg.backtrack;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class SolveSudoku2 {
    // 三个布尔数组 表明 行, 列, 还有 3*3 的方格的数字是否被使用过
   private final boolean[][] rowUsed = new boolean[9][9];
   private final boolean[][] colUsed = new boolean[9][9];
   private boolean[][][] girdUsed = new boolean[3][3][9];


    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        // 初始化
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int k = board[i][j] - '1';
                    rowUsed[i][k] = true;
                    colUsed[j][k] = true;
                    girdUsed[i / 3][j / 3][k] = true;
                }
            }
        }

        dfs(board, 0, 0);
    }

    public boolean dfs(char[][] board, int row, int col) {
        if (col == board.length) {
            col = 0;
            row++;
            if (row == board.length) {
                return true;
            }
        }

        if (board[row][col] != '.') {
           return dfs(board, row, col + 1);
        }

        for (char num = '1'; num <= '9'; num++) {
            int k = num - '1';
            boolean isValid = false;
            try {
                isValid = !rowUsed[row][k] && !colUsed[col][k] && !girdUsed[row / 3][col / 3][k];
            } catch (Exception e) {
                System.out.printf("%d, %d, %d\n, ", row, col, k);
            }
            if (isValid) {
                board[row][col] = num;
                rowUsed[row][k] = true;
                colUsed[col][k] = true;
                girdUsed[row / 3][col / 3][k] = true;

                if (dfs(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.';
                rowUsed[row][k] = false;
                colUsed[col][k] = false;
                girdUsed[row / 3][col / 3][k] = false;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        char [][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        SolveSudoku2 solveSudoku = new SolveSudoku2();
        solveSudoku.solveSudoku(board);

        IsValidSudoku isValidSudoku = new IsValidSudoku();
        boolean result = isValidSudoku.isValidSudoku(board);
        Assert.assertTrue(result);
    }
}
