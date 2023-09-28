package com.alg.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 120. 三角形最小路径和
 */
public class MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] mini = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >=0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                mini[j] = triangle.get(i).get(j) + Math.min(mini[j], mini[j + 1]);
            }
        }

        return mini[0];
    }

    public static void main(String[] args) {
        //[[2],[3,4],[6,5,7],[4,1,8,3]]
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> l3 = new ArrayList<>();
        List<Integer> l4 = new ArrayList<>();

        l1.add(2);

        l2.add(3);
        l2.add(4);

        l3.add(6);
        l3.add(5);
        l3.add(7);

        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(l1);
        triangle.add(l2);
        triangle.add(l3);
        triangle.add(l4);

        MinimumTotal minimumTotal = new MinimumTotal();
        int resullt = minimumTotal.minimumTotal(triangle);
        System.out.println(resullt);

        List<Integer> list = new ArrayList<>();
        list.add(-10);
        List<List<Integer>> t2 = new ArrayList<>();
        t2.add(list);
        System.out.println(minimumTotal.minimumTotal(t2));
    }
}
