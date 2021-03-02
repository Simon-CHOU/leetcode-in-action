package com.simon;

import com.simon.util.*;
import com.sun.source.tree.Tree;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

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
    List<Integer> list = new ArrayList<>();

    public int kthLargest(TreeNode root, int k) {
        dfs(root);
        return list.get(list.size() -k);
    }

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}