package com.alg.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords3 {
    class TrieNode{
        private String word;
        private TrieNode []next;

        public TrieNode() {
            this(null);
        }

        public TrieNode(String word) {
            this.word = word;
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
        if (cur.word == null) {
            cur.word = word;
        }
    }


    private final Set<String> result = new HashSet<>();
    private boolean[][] visited;
    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            insert(word);
        }

        visited = new boolean[board.length][board[0].length];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                TrieNode trieNode = root.next[board[i][j] - 'a'];
                if (trieNode != null) {
                    dfs(board, i, j, trieNode);
                }
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, int x, int y, TrieNode trieNode) {
        visited[x][y] = true;
        if (trieNode.word != null) {
            result.add(trieNode.word);
        }

        for (int[] d : dirs) {
            int nX = x + d[0];
            int nY = y + d[1];
            if (nX < 0 || nY < 0 || nX >= board.length || nY >= board[0].length || visited[nX][nY]) {
                continue;
            }
            char c = board[nX][nY];
            if (trieNode.next[c - 'a'] != null) {
                dfs(board, nX, nY, trieNode.next[c - 'a']);
            }
        }

        visited[x][y] = false;
    }

    public static void main(String[] args) {
        char[][] board = {{'a', 'b', 'c'}, {'a', 'e', 'd'}, {'a', 'f', 'g'}};
        String[] words = {"abcdefg", "gfedcbaaa", "eaabcdgfa", "befa", "dgc", "ade"};
//        String[] words = {"a"};
        FindWords3 findWords = new FindWords3();


        List<String> result = findWords.findWords(board, words);
        System.out.println(result);
    }
}
