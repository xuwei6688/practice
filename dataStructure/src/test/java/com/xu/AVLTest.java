package com.xu;

import com.xu.tree.AVLTree;
import com.xu.tree.RBTree;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class AVLTest {
    @Test
    public void test_avl() {
        LinkedHashSet<Integer> list = new LinkedHashSet<>();
        Random random = new Random();
        for (int i = 0; list.size() < 500000; i++) {
//            int value = random.nextInt(Integer.MAX_VALUE);
            list.add(i);
        }

        long startTime = System.nanoTime();
        AVLTree<Integer> avl = new AVLTree<>();
        list.forEach(avl::add);
        System.out.println(avl.size());

        for (Integer i : list) {
            if (!avl.contains(i)) {
                throw new IllegalArgumentException();
            }
        }
        long endTime = System.nanoTime();
        System.out.println("avl 树耗时：" + (endTime - startTime) / 1000000000.0 + "s");


        startTime = System.nanoTime();
        RBTree<Integer> tree = new RBTree<>();
        list.forEach(tree::add);
        System.out.println(tree.size());

        for (Integer i : list) {
            if (!tree.contains(i)) {
                throw new IllegalArgumentException();
            }
        }
        endTime = System.nanoTime();
        System.out.println("红黑树耗时：" + (endTime - startTime) / 1000000000.0 + "s");

    }
}
