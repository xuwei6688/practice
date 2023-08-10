package com.xu.backtrack;

import java.util.Arrays;

/**
 * 79.单词搜索
 * 题目描述：ttps://leetcode.cn/problems/word-search/
 * 题解：https://www.yuque.com/wozaiyinshen/vtwlns/fohsfxlxy7bo81g1
 */
public class WordSearch {

    /**
     * 以board中(startX,startY)为起点，如果能够搜索到word中[index,word.length-1]对应字符，返回true
     */
    private boolean dfs(char[][] board, String word, int index, int startX, int startY, boolean[][] visited) {
        if (!inArena(startX, startY, board[0].length, board.length) || visited[startY][startX]) {
            return false;
        }

        if (board[startY][startX] != word.charAt(index)) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        visited[startY][startX] = true;
        if (dfs(board, word, index + 1, startX, startY - 1, visited)
                || dfs(board, word, index + 1, startX + 1, startY, visited)
                || dfs(board, word, index + 1, startX, startY + 1, visited)
                || dfs(board, word, index + 1, startX - 1, startY, visited)) {
            return true;
        }

        visited[startY][startX] = false;
        return false;
    }

    private boolean inArena(int startX, int startY, int maxX, int maxY) {
        return startX >= 0 && startY >= 0 && startX < maxX && startY < maxY;
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                boolean existed = dfs(board, word, 0, x, y, visited);
                if (existed) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";

        WordSearch wordSearch = new WordSearch();
        boolean exist = wordSearch.exist(board, word);
        System.out.println(exist);
    }
}
