package com.xu;

import com.xu.tree.RBTree;

import com.xu.list.*;

public class RBTreeTest {
    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();

        // 测试插入
        rbTree.add(10);
        rbTree.add(5);
        rbTree.add(15);
        rbTree.add(3);
        rbTree.add(7);

        // 预期中序遍历结果为 [3, 5, 7, 10, 15]
        List<Integer> inOrderResult = rbTree.inOrder();
        System.out.println("InOrder: " + inOrderResult);

        // 测试删除
        rbTree.remove(5);

        // 预期中序遍历结果为 [3, 7, 10, 15]
        inOrderResult = rbTree.inOrder();
        System.out.println("InOrder after removal: " + inOrderResult);

        // 测试搜索
        System.out.println("Contains 7: " + rbTree.contains(7)); // 预期输出 true
        System.out.println("Contains 5: " + rbTree.contains(5)); // 预期输出 false

        // 测试最小值和最大值
        System.out.println("Minimum: " + rbTree.minimum()); // 预期输出 3
        System.out.println("Maximum: " + rbTree.maximum()); // 预期输出 15
    }
}
