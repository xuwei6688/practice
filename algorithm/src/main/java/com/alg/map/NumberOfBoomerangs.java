package com.alg.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 *
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 *
 * 示例:
 * 输入:
 * [[0,0],[1,0],[2,0]]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 **/
public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        //key-距离 value-到达中枢点为该距离的点的数量
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int distinct = getDistinct(points[i], points[j]);
                map.put(distinct, map.getOrDefault(distinct, 0) + 1);
            }
            for (int count : map.values()) {
                res += count * (count - 1);
            }
            map.clear();
        }
        return res;
    }

    private int getDistinct(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }

    public static void main(String[] args) {
        NumberOfBoomerangs numberOfBoomerangs = new NumberOfBoomerangs();
        int i = numberOfBoomerangs.numberOfBoomerangs(new int[][]{{1, 1}, {2, 2}, {3, 3}});
//        int i = numberOfBoomerangs.numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}});
        System.out.println(i);
    }
}
