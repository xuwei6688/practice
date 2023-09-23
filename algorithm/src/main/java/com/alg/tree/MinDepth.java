package com.alg.tree;

import com.alg.baseStruct.TreeNode;

public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //1.根节点左右孩子都为空，最小深度就是1
        if (root.left == null && root.right == null) return 1;


        int left = minDepth(root.left);
        int right = minDepth(root.right);

        //2.根节点左右一个孩子为空，返回不为空那个孩子的深度
        if (root.left == null || root.right == null) {
            return root.left == null ? ++right : ++left;
        }
        //3.根节点左右孩子都不为空
        return Math.min(left,right) + 1;
    }
}
