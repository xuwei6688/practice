package com.xu.stack;

import com.xu.baseStruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author xuwei
 * @Date 2020/9/20 0020
 * @Version V1.0
 **/
public class LevelOrder2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n =  queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i <n; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(list);
        }
        return res;
    }
}
