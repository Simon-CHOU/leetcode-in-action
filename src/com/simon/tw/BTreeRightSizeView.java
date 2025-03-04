package com.simon.tw;

import com.simon.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 定义二叉树节点类
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//
//    TreeNode(int x) {
//        val = x;
//    }
//
//    TreeNode(int val, TreeNode left, TreeNode right) {
//        this.val = val;
//        this.left = left;
//        this.right = right;
//    }
//}

class RightSideView {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }
}

public class BTreeRightSizeView {
    public static void main(String[] args) {
//        给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返
//        回从右侧所能看到的节点值。
        //https://leetcode.cn/problems/binary-tree-right-side-view/
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.right = new TreeNode(5);
//        root.right.right = new TreeNode(4);
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        null,
                        new TreeNode(5)
                ),
                new TreeNode(3,
                        null,
                        new TreeNode(4)
                )
        );
        // [1,2,3,null,5,null,4]
        List<Integer> result = RightSideView.rightSideView(root);
        System.out.println(result); // 1, 3,4
    }
}
