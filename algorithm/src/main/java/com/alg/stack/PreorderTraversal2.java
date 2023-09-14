package com.alg.stack;

import com.alg.baseStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xuwei
 * @Date 2020/9/20 0020
 * @Version V1.0
 **/
public class PreorderTraversal2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    private void preOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }
}
