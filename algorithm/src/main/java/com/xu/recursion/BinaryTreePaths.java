package com.xu.recursion;

import com.xu.baseStruct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 **/
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        }

        if (node.left == null && node.right == null) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(node.val));
            return list;
        }

        List<String> list;
        if (node.left == null) {
            list = binaryTreePaths(node.right);
        } else if (node.right == null) {
            list = binaryTreePaths(node.left);
        }else{
            list = binaryTreePaths(node.left);
            list.addAll(binaryTreePaths(node.right));
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, node.val + "->" + list.get(i));
        }
        return list;
    }
}
