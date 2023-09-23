package com.alg.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords2 {
    class TrieNode{
        private boolean isEnd;
        private TrieNode []next;

        public TrieNode() {
            this(false);
        }

        public TrieNode(boolean isEnd) {
            this.isEnd = isEnd;
            this.next = new TrieNode[26];
        }
    }

    private TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new TrieNode();
            }
            cur = cur.next[c - 'a'];
        }
        if (!cur.isEnd) {
            cur.isEnd = true;
        }
    }

    private boolean existPrefix(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next[c - 'a'] == null) {
               return false;
            }
            cur = cur.next[c - 'a'];
        }
        return true;
    }

    private final Set<String> wordSet = new HashSet<>();
    private final List<String> result = new ArrayList<>();
    private boolean[][] visited;
    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            insert(word);
        }
        for (String word:words) wordSet.add(word);
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
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visited[x][y] || !existPrefix(sb.toString() + board[x][y])) {
            return;
        }
        visited[x][y] = true;
        sb.append(board[x][y]);

        if (wordSet.contains(sb.toString())) {
            result.add(sb.toString());
            wordSet.remove(sb.toString());
        }

        for (int[] d : dirs) {
            int nX = x + d[0];
            int nY = y + d[1];
            dfs(board, nX, nY, sb);
        }

        visited[x][y] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {



        char[][] board = {{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}};
        String[] words = {"abcdefg", "gfedcbaaa", "eaabcdgfa", "befa", "dgc", "ade"};
        FindWords2 findWords = new FindWords2();


        List<String> result = findWords.findWords(board, words);
        System.out.println(result);
    }
}
