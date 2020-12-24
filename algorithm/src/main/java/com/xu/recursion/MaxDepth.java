package com.xu.recursion;

import com.xu.baseStruct.TreeNode;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 **/
public class MaxDepth {
    public int maxDepth(TreeNode root) {
       return findMaxDepth(root);
    }

    public int findMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lh = findMaxDepth(node.left);
        int rh = findMaxDepth(node.right);
        int max = lh >= rh ? lh : rh;
        return ++max;
    }
}
