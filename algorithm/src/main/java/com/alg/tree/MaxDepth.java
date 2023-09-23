package com.alg.tree;

import com.alg.baseStruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaxDepth {
//    public int maxDepth(TreeNode root) {
//        int maxDepth = 0;
//        if(root == null) return 0;
//
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//
//        while (!queue.isEmpty()) {
//            int n = queue.size();
//
//            for (int i = 0; i < n; i++) {
//                TreeNode node = queue.poll();
//
//                if (node.left!=null) queue.add(node.left);
//                if (node.right!=null) queue.add(node.right);
//            }
//            maxDepth++;
//        }
//        return maxDepth;
//    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
