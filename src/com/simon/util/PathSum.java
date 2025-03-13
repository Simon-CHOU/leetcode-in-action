package com.simon.util;

import java.util.LinkedList;
import java.util.Queue;

class Solution112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);  // 不要忘了先 offer
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == targetSum) {
                    return true;
                }
                continue; // 不要漏掉continue
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;

    }

    public boolean hasPathSumDFS(TreeNode root, int targetSum) { // 20250312 recall
        if (root == null) return false;
        targetSum -= root.val; // 要写在if-e
        if (root.left == null && root.right == null && targetSum == 0) {
            return true;
        }
//        targetSum -= root.val;
        // hasPathSum(root.left, targetSum);
        // hasPathSum(root.right, targetSum);

        return hasPathSum(root.left, targetSum) ||
                hasPathSum(root.right, targetSum);
    }
}

public class PathSum {
    public static void main(String[] args) {
        TreeNode node = InputTreeUtil.createTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        Solution112 solution112 = new Solution112();
        System.out.println(solution112.hasPathSum(node, 22));
    }
}
