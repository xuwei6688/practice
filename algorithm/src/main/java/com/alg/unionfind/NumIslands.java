package com.alg.unionfind;

import org.junit.Assert;

public class NumIslands {

    private int colNums;
    private int rowNums;
    //1、遍历所有的格子，遇到陆地，访问它右边和下边两个格子，如果是陆地，进行union操作，合并
    //2、最后所有的陆地都合并了，查下并查集中有几个根就是几个陆地
    //3、二维转格子索引转并查集索引 index = i * colsNum +j  gird(10,10)  (0,3)->(3) (1,0)->(10) (2,8)->(28)
    public int numIslands(char[][] grid) {
        UnionFind unionFind = new UnionFind(grid.length * grid[0].length);

        int[][] d = {{1, 0}, {0, 1}};//横纵坐标偏移量
        colNums = grid[0].length;
        rowNums = grid.length;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == '1') {
                    int index = getIndex(x, y);
                    unionFind.set(index, index);
                }
            }
        }

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == '1') {
                    //访问右边和下边的格子，如果也是陆地，合并到并查集中
                    for (int[] ints : d) {
                        int newX = x + ints[0];
                        int newY = y + ints[1];
                        if (isRange(newX, newY) && grid[newY][newX] == '1') {
                            unionFind.union(getIndex(x, y), getIndex(newX, newY));
                        }
                    }
                }
            }
        }
        return unionFind.count();
    }

    private boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < colNums && y < rowNums;
    }

    private int getIndex(int x, int y) {
        int res =  y * colNums + x;
        return  res;
    }


    class UnionFind {
        private int[] roots;

        public UnionFind(int n) {
            roots = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = -1;
            }
        }


        public void set(int index, int n) {
            roots[index] = n;
        }

        private int findRoot(int i) {
            int root = i;
            //找到root
            while (root != roots[root]) {
                root = roots[root];
            }
            //一直向上，将所有节点都直接指向root
            while (i != roots[i]) {
                int tmp = roots[i];
                roots[i] = root;
                i = tmp;
            }
            return root;
        }

        public boolean connected(int p, int q) {
            return findRoot(p) == findRoot(q);
        }

        public void union(int p, int q) {
            try {
                int pRoot = findRoot(p);
                int qRoot = findRoot(q);
                roots[qRoot] = pRoot;
            } catch (Exception e) {
                System.out.println(p + "" + q);
            }
        }

        public int count() {
            int count = 0;
            for (int i = 0; i < roots.length; i++) {
                if (roots[i] == i) {
                    count++;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        char [][]gird = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };

        NumIslands numIslands = new NumIslands();
        int result = numIslands.numIslands(gird);
        Assert.assertEquals(1, result);

       char [][]gird2 = {
               {'1','1','0','0','0'},
               {'1','1','0','0','0'},
               {'0','0','1','0','0'},
               {'0','0','0','1','1'}
       };

        int result2 = numIslands.numIslands(gird2);
        Assert.assertEquals(3, result2);
    }
}
