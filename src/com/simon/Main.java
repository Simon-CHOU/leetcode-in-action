package com.simon;

import com.simon.util.*;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode treeNode = InputTreeUtil.inputBtree();
            System.out.println(DisplayTreeUtil.maxDepth(treeNode));
//            System.out.println(solution.isSymmetric(treeNode));
        }
    }


}


class Solution {
    public boolean isSymmetric(TreeNode root) {
        return checkBFS(root, root);
    }

    boolean checkDFS(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && checkDFS(p.left, q.right) && checkDFS(p.right, q.left);
    }

    boolean checkBFS(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }
            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }
}