package com.alg.unionfind;

import org.junit.Assert;

public class FindCircleNum {
    int n;
    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;

        UnionFind unionFind = new UnionFind(200);

        int[][] d = {{0, 1}, {1, 0}};

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.count();
    }

    class UnionFind {
        private int[] roots;

        public UnionFind(int n) {
            roots = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = n;
            }
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
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            roots[qRoot] = pRoot;
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
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        FindCircleNum findCircleNum = new FindCircleNum();
        int result = findCircleNum.findCircleNum(isConnected);
        Assert.assertEquals(2, result);

        int[][] isConnected2 = {{1,0,0},{0,1,0},{0,0,1}};
        result = findCircleNum.findCircleNum(isConnected2);
        Assert.assertEquals(3, result);

        int[][] isConnected3 = {
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };
        result = findCircleNum.findCircleNum(isConnected3);
        Assert.assertEquals(1, result);
    }
}
