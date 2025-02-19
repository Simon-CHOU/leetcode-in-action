package com.simon.tw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution103 { // https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) { // 20240218 复习
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans; // 记得判空，有空用例
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
//        ans.add(Arrays.asList(root.val)); //不需要提前添加
        boolean odd = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) { // 一定要统计层数后遍历。而不是直接左子树、右子树开始入队
                TreeNode poll = queue.poll();
                layer.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }

            //            if(odd) {
////                Collections.reverse(layer);
//                layer.reversed();
//                odd = !odd;
//            } // act [[3], [9, 20], [15, 7]] 反转不过来  exp  [[3],[20,9],[15,7]]

            odd = !odd; // 奇偶反转是每层比做，而不是放到下面的花括号中
            if (odd) {
//                layer = layer.reversed();
                Collections.reverse(layer);
            }
            ans.add(layer); // 这这里需要判空，不能把空list放到结果集中
        }
        return ans;
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

public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {

//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        int[] arr = new int[]{1, 2, 3, 4, null, null, 5};

        Solution103 solution103 = new Solution103();
        Integer[] nodes = {3,9,20,null,null,15,7};
        TreeNode root = solution103.createBinaryTree(nodes);
        List<List<Integer>> i = solution103.zigzagLevelOrder(root);
        System.out.println(i); // [[3],[20,9],[15,7]]

    }
}
