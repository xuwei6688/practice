package com.xu.recursion;

import com.xu.baseStruct.TreeNode;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * 类似问题：100、101
 **/
public class InvertTree {
    public TreeNode invertTree(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode right = node.right;
        node.right = invertTree(node.left);
        node.left = invertTree(right);
        return node;
    }
}
