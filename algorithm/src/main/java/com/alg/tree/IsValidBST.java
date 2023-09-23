package com.alg.tree;

import java.util.ArrayList;
import java.util.List;

public class IsValidBST {
    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    //    public boolean isValidBST(TreeNode root) {
//        if (root.left != null && root.left.val >= root.val) {
//            return false;
//        }
//        if (root.right != null && root.right.val <= root.val) {
//            return false;
//        }
//
//        if (root.left == null && root.right == null) {
//            return true;
//        } else if (root.left == null) {
//            return isValidBST(root.right);
//        } else if (root.right == null) {
//            return isValidBST(root.left);
//        }else {
//            return isValidBST(root.left) && isValidBST(root.right);
//        }
//    }
    private long pre = Long.MAX_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }

        if (pre >= root.val) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }
}
