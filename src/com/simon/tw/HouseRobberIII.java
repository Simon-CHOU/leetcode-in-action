package com.simon.tw;

import java.util.LinkedList;
import java.util.Queue;

class Solution337 {
    public int rob(TreeNode root) {

        int[] dfs = dfs(root);
        return Math.max(dfs[0], dfs[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int rob = left[1] + right[1] + root.val;
        int notRob = Math.max(left[0], left[1]) + Math.max(right[1], right[0]);

        return new int[]{rob, notRob};
    }


    public TreeNode createBinaryTree(Integer[] nodes) {
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

public class HouseRobberIII {
    public static void main(String[] args) {
        Solution337 solution337 = new Solution337();
        Integer[] nodes = {1, 2, 3, 4, null, null, 5};
        TreeNode root = solution337.createBinaryTree(nodes);
        int rob = solution337.rob(root);
        System.out.println(rob);
    }
}
