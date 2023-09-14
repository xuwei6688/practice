package com.alg.stack;

import com.alg.baseStruct.TreeNode;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *  
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 **/
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));


        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int level = pair.level;

            //level是从0开始的，res.size() 也是从0开始，它俩相等，说明当前层不存在
            //举例：初始时level=0，size=0，res中当前层不存在。
            if (res.size() == level) {
                List<Integer> list = new ArrayList<>();
                list.add(node.val);
                res.add(list);
            }else{
                res.get(level).add(node.val);
            }

            if (node.left != null) queue.offer(new Pair(node.left, level + 1));
            if (node.right != null) queue.offer(new Pair(node.right, level + 1));

        }
        return res;
    }

    class Pair{
        TreeNode node;
        int level;

        public Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
