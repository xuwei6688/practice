package com.alg.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class FindWords {
    private final Set<String> wordSet = new HashSet<>();
    private final List<String> result = new ArrayList<>();
    private boolean[][] visited;
    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        Set<Character> cSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                cSet.add(board[i][j]);
            }
        }

        for(String word:words) {
            boolean needPut = true;
            for (int i = 0; i < word.length(); i++) {
                if (!cSet.contains(word.charAt(i))) {
                    needPut = false;
                    break;
                }
            }
            if (needPut) {
                wordSet.add(word);
            }
        }
        visited = new boolean[board.length][board[0].length];


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, sb);
            }
        }
        return result;
    }


    private void dfs(char[][] board, int x, int y, StringBuilder sb) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visited[x][y] || sb.length() == 10) {
            return;
        }

        sb.append(board[x][y]);
        visited[x][y] = true;

        if (wordSet.contains(sb.toString())) {
            result.add(sb.toString());
            wordSet.remove(sb.toString());
        }

        for (int[] d : dirs) {
           int nX = x + d[0];
           int nY = y + d[1];
           dfs(board, nX, nY, sb);
        }

        sb.deleteCharAt(sb.length() - 1);
        visited[x][y] = false;
    }

    public static void main(String[] args) {
//        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
//        String[] words = {"oath","pea","eat","rain"};
        char[][] board = {{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}};
        String[] words = {"abcdefg", "gfedcbaaa", "eaabcdgfa", "befa", "dgc", "ade"};
        FindWords findWords = new FindWords();
        List<String> result = findWords.findWords(board, words);
        System.out.println(result);
    }
}
