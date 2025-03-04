package com.simon.tw;

import com.simon.util.BTCreator;
import com.simon.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution113 {
    List<List<Integer>> ret = new LinkedList<>();
    Deque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }

    public void dfs(TreeNode root, int targetSum) {
        if (root == null) return;
        path.offerLast(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) { // 叶子结点
            ret.add(new LinkedList<>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        path.pollLast();
    }
}

public class PathSumII {
    public static void main(String[] args) {
        TreeNode binaryTree = BTCreator.createBinaryTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        Solution113 solution113 = new Solution113();
        System.out.println(solution113.pathSum(binaryTree, 22)); // [[5, 4, 11, 2], [5, 8, 4, 5]]



    }
}
