package com.alg.backtrack;

public class Exist {
    private int[][] ints = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean [][]used;


    public boolean exist(char[][] board, String word) {
        used = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char[][] board, String word, int startX, int startY, int index) {
        if (index == word.length()) {
            return true;
        }

        if (startX < 0 || startX >= board.length || startY < 0 || startY >= board[0].length) {
            return false;
        }

        if (used[startX][startY]) {
            return false;
        }

        char c = word.charAt(index);
        if (board[startX][startY] != c) {
            return false;
        }

        used[startX][startY] = true;

        if (search(board, word, startX, startY + 1, index + 1)
                || search(board, word, startX, startY - 1, index + 1)
                || search(board, word, startX + 1, startY, index + 1)
                || search(board, word, startX - 1, startY, index + 1)) {
            return true;
        }
        used[startX][startY] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        Exist exist = new Exist();
        boolean result = exist.exist(board, "SEE");
        System.out.println(result);
    }
}
