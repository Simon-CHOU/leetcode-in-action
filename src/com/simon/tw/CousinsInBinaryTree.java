package com.simon.tw;

import com.simon.util.InputTreeUtil;
import com.simon.util.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution993 {

    int x;
    TreeNode xParent;
    int xDepth;
    boolean xFound = false;

    int y;
    TreeNode yParent;
    int yDepth;
    boolean yFound = false;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }

    public void dfs(TreeNode node, int depth, TreeNode parent) {
        if (node == null) {
            return;
        }
        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
//            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
//            yFound = true;
        }

//        if (xFound && yFound) {
//            return;
//        }
        dfs(node.left, depth + 1, node);
//        if (xFound && yFound) {
//            return;
//        }
        dfs(node.right, depth + 1, node);
    }
}

public class CousinsInBinaryTree {
    public static void main(String[] args) {
//        com.simon.util.TreeNode tree = InputTreeUtil.createTree(new Integer[]{1, 2, 3, null, 4, null, 5});

        TreeNode btree = createBinaryTree(new Integer[]{1, 2, 3, null, 4, null, 5});
        Solution993 solution993 = new Solution993();
        System.out.println(solution993.isCousins(btree, 5, 4));
    }
// 构建树
    public static TreeNode createBinaryTree(Integer[] root) {
        if (root == null || root.length == 0) {
            return null;
        }

        return buildTree(root, 0);
    }

    private static TreeNode buildTree(Integer[] nodes, int index) {
        if (index >= nodes.length || nodes[index] == null) {
            return null;
        }

        TreeNode node = new TreeNode(nodes[index]);
        node.left = buildTree(nodes, 2 * index + 1);
        node.right = buildTree(nodes, 2 * index + 2);

        return node;
    }
}
