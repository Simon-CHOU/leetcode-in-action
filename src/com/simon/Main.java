package com.simon;

import com.simon.util.*;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode treeNode = InputTreeUtil.inputBtree();
            System.out.println(solution.sumOfLeftLeaves(treeNode));
        }
    }


}


class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        int res = 0;
        if (root.left != null) {
            res += isLeafNode(root.left) ? root.left.val : dfs(root.left);
        }
        if (root.right != null && !isLeafNode(root.right)) {
            res += dfs(root.right);
        }
        return res;
    }

    boolean isLeafNode(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
//[3,9,20,null,null,15,7]
//24