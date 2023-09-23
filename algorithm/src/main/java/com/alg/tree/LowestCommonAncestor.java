package com.alg.tree;

public class LowestCommonAncestor {
    public static class TreeNode {
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

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    public static void main(String[] args) {

        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode7 = new TreeNode(7);

        TreeNode treeNode4 = new TreeNode(4, treeNode3, treeNode5);
        TreeNode treeNode8 = new TreeNode(8, treeNode7, treeNode9);
        TreeNode treeNode2 = new TreeNode(2, treeNode0, treeNode4);
        TreeNode treeNode6 = new TreeNode(6, treeNode2, treeNode8);


        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
        TreeNode treeNode = lowestCommonAncestor.lowestCommonAncestor(treeNode6, treeNode4, treeNode9);
        System.out.println(treeNode);

    }
}
