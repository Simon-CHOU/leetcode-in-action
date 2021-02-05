package com.simon;

import com.simon.util.*;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode treeNode = InputTreeUtil.inputBtree();
            System.out.println(solution.diameterOfBinaryTree(treeNode));
        }
    }


}


class Solution {
    int res;

    public int diameterOfBinaryTree(TreeNode root) {
        res = 1;
        depth(root);
        return res - 1;
    }

    int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int L = depth(root.left);
        int R = depth(root.right);
        res = Math.max(res, L + R + 1);
        return Math.max(L, R) + 1;
    }
}