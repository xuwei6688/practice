package com.alg.backtrack;

import org.junit.Assert;

public class SolveSudoku {

    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        dfs(board);
    }

    public boolean dfs(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }

                for (char num = '1'; num <= '9'; num++) {
                    if (isValid(board, i, j, num)) {
                        board[i][j] = num;
                        if (dfs(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.' && board[row][i] == num) return false;//横坐标固定 row，看每一列，是否有num
            if (board[i][col] != '.' && board[i][col] == num) return false;
            //检查所属九宫格，board[3 * (row / 3)][3 * (col / 3)]定位到左上角的点，横坐标 i/3，每隔三个数换下一行，纵坐标 i%3 一列列遍历
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) return false;
        }
        return true;
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
        SolveSudoku solveSudoku = new SolveSudoku();
        solveSudoku.solveSudoku(board);

        IsValidSudoku isValidSudoku = new IsValidSudoku();
        boolean result = isValidSudoku.isValidSudoku(board);
        Assert.assertTrue(result);
    }
}
