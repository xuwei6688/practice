package com.xu;

import com.xu.list.ArrayList;
import com.xu.list.List;
import com.xu.tree.AVL;
import com.xu.tree.AVLTree;
import com.xu.trie.FileOperation;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Random;

public class AVLTest {
    @Test
    public void test_avl() {
        LinkedHashSet<Integer> list = new LinkedHashSet<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int value = random.nextInt(Integer.MAX_VALUE);
            list.add(value);
        }

        AVL<Integer> avl = new AVL<>();
//        AVLTree<Integer, Integer> avl = new AVLTree<>();
        list.forEach(e->{
            avl.add(e);
            if (!avl.isBalanced()) {
                System.out.println("---------");
                throw new IllegalArgumentException();
            }
        });

//        list.forEach(e->{
//            avl.remove(e);
//            if (!avl.isBalanced()) {
//                System.out.println("*********");
//                throw new IllegalArgumentException();
//            }
//        });

        while (avl.size() > 100) {
            avl.removeMin();
            if (!avl.isBalanced()) {
                throw new IllegalArgumentException();
            }
        }

    }
}
