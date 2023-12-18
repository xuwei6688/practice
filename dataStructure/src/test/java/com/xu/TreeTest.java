package com.xu;


import com.xu.list.*;
import com.xu.tree.AVL;
import com.xu.tree.BST;
import com.xu.tree.BinarySearchTree;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {
    public BinarySearchTree<Integer> createBST() {
//        return new BST<>();
        return new AVL<>();
    }

    @Test
    public void test1() {
        BinarySearchTree<Integer> bst = createBST();
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(2);
        bst.add(4);
        bst.add(8);

        List<Integer> list = bst.preOrder();
        List<Integer> preList = new ArrayList<>();
        preList.add(5,3,2,4,6,8);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), preList.get(i));
        }
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(8));
        assertFalse(bst.contains(10));
    }

    /**
     * 删除任意节点
     */
    @Test
    public void test2() {
        BinarySearchTree<Integer> bst = createBST();
        bst.add(41);
        bst.add(58);
        bst.add(50);
        bst.add(60);
        bst.add(42);
        bst.add(53);
        bst.add(59);
        bst.add(63);
        List<Integer> list = bst.preOrder();
        List<Integer> exceptsList = new ArrayList<>();
        exceptsList.add(41,58,50,42,53,60,59,63);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), exceptsList.get(i));
        }
        bst.remove(58);
        list = bst.preOrder();
        exceptsList = new ArrayList<>();
        exceptsList.add(41,59,50,42,53,60,63);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), exceptsList.get(i));
        }
        assertEquals(bst.size(), 7);
    }

    /**
     * 删除最小和最大节点
     */
    @Test
    public void test3() {
        BinarySearchTree<Integer> bst = createBST();
        bst.add(41);
        bst.add(58);
        bst.add(50);
        bst.add(60);
        bst.add(42);
        bst.add(53);
        bst.add(59);
        bst.add(63);
        List<Integer> list = bst.preOrder();
        List<Integer> exceptsList = new ArrayList<>();
        exceptsList.add(41,58,50,42,53,60,59,63);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), exceptsList.get(i));
        }
        bst.removeMin();
        bst.removeMax();
        list = bst.preOrder();
        exceptsList = new ArrayList<>();
        exceptsList.add(58,50,42,53,60,59);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), exceptsList.get(i));
        }
        assertEquals(bst.size(), 6);
    }

    @Test
    public void test4() {
        BinarySearchTree<Integer> bst = createBST();
        assertEquals(bst.size(), 0);
        Integer minimum = bst.minimum();
        assertNull(minimum);

        Integer min = bst.removeMin();
        assertNull(min);

        Integer max = bst.removeMax();
        assertNull(max);
    }

    @Test
    public void test_levelOrder() {
        BinarySearchTree<Integer> bst = createBST();
        bst.add(41);
        bst.add(58);
        bst.add(50);
        bst.add(60);
        bst.add(42);
        bst.add(53);
        bst.add(59);
        bst.add(63);
        List<Integer> list = bst.levelOrder();
        List<Integer> exceptsList = new ArrayList<>();
        exceptsList.add(41,58,50,60,42,53,59,63);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), exceptsList.get(i));
        }
    }

    @Test
    public void test_removeMin() {
        BinarySearchTree<Integer> bst = createBST();
        bst.add(41);
        bst.add(58);
        bst.add(50);
        bst.add(60);
        Integer result = bst.removeMin();
        assertEquals(41L, (int)result);

        result = bst.removeMin();
        assertEquals(50L, (int)result);

        result = bst.removeMin();
        assertEquals(58L, (int)result);

        result = bst.removeMin();
        assertEquals(60L, (int)result);

        result = bst.removeMin();
        assertNull(result);

    }
}
