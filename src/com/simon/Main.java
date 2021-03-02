package com.simon;

import com.simon.util.*;

import java.util.*;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode s = InputTreeUtil.inputBtree();
            int k = InputUtil.inputInt();
            System.out.println(solution.kthLargest(s, k));
        }
    }
}

class Solution {
    private int result;
    private int countK;

    public int kthLargest(TreeNode root, int k) {
        this.countK = k;
        dfs(root);
        return result;
    }

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);//倒序中序遍历，得到非递增序列
        if (--this.countK == 0) {//第k大，可以直接数出来
            this.result = root.val;
            return;
        }
        dfs(root.left);
    }
}