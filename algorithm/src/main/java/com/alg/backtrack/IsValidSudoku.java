package com.alg.backtrack;

public class IsValidSudoku {
    boolean[][] row = new boolean[9][9];//行，row[i][num]=true表示第i行num这个数出现过
    boolean[][] col = new boolean[9][9];//列，col[i][num]=true表示第i列num这个数出现过
    boolean[][][] block = new boolean[3][3][9];

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 3; i++) {
            block[i] = new boolean[3][3];
            for (int j = 0; j < 3; j++) {
                block[i][j] = new boolean[9];
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;

                int num = board[i][j] - '1';

                if (row[i][num] || col[j][num] || block[i / 3][j / 3][num]) {
                    System.out.printf("(%d,%d)%n", i, j);
                    return false;
                }

                row[i][num] = true;
                col[j][num] = true;
                block[i / 3][j / 3][num] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char [][]board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        IsValidSudoku isValidSudoku = new IsValidSudoku();
        boolean result = isValidSudoku.isValidSudoku(board);
        System.out.printf(String.valueOf(result));
    }
}
