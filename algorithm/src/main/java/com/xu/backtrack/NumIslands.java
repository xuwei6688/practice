package com.xu.backtrack;

/**
 * 200. 岛屿数量
 * leetcode：https://leetcode.cn/problems/number-of-islands/
 * 题解：https://www.yuque.com/wozaiyinshen/vtwlns/nmli1gn2dwxobmet
 */
public class NumIslands {


    private void dfs(char[][] grid, int startX, int startY) {
        if (startX < 0 || startY < 0 || startX < grid[0].length && startY < grid.length) {
            return;
        }

        if (grid[startY][startX] != '1') {
            return;
        }
        grid[startY][startX] = '0';

        dfs(grid, startX, startY - 1);
        dfs(grid, startX + 1, startY);
        dfs(grid, startX, startY + 1);
        dfs(grid, startX - 1, startY);
    }


    public int numIslands(char[][] grid) {
        int islandsNum = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return islandsNum;
        }


        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == '1') {
                    islandsNum = islandsNum + 1;
                    dfs(grid, x, y);
                }
            }
        }
        return islandsNum;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };

        NumIslands numIslands = new NumIslands();
        int num = numIslands.numIslands(grid);
        System.out.println(num);


        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        int num2 = numIslands.numIslands(grid2);
        System.out.println(num2);

    }
}
