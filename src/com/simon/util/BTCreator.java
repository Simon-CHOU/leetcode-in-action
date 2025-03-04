package com.simon.util;

import java.util.LinkedList;
import java.util.Queue;

// 通过数组构建二叉树，公共方法
public class BTCreator {
    public static TreeNode createBinaryTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }

        // 创建一个队列用于存储节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 创建根节点
        TreeNode root = new TreeNode(nodes[0]);
        queue.offer(root);

        int i = 1; // 用于遍历数组
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();

            // 左子节点
            if (i < nodes.length && nodes[i] != null) {
                current.left = new TreeNode(nodes[i]);
                queue.offer(current.left);
            }
            i++;

            // 右子节点
            if (i < nodes.length && nodes[i] != null) {
                current.right = new TreeNode(nodes[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }
}
