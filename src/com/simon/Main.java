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
            TreeNode t = InputTreeUtil.inputBtree();
            TreeNode q = InputTreeUtil.inputBtree();
            DisplayTreeUtil.levelOrderTraversal(solution.lowestCommonAncestor(s, t, q));
        }
    }
}

class Solution {
    List<TreeNode> path1 = new ArrayList<>();
    List<TreeNode> path2 = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p.val, path1);
        dfs(root, q.val, path2);

        Iterator<TreeNode> it1 = path1.iterator();
        Iterator<TreeNode> it2 = path2.iterator();
        TreeNode ans = root;
        while (it1.hasNext()&&it2.hasNext()){
            TreeNode val1 = it1.next();
            TreeNode val2 = it2.next();
            if(val1.val !=val2.val) {
                break;
            }
            ans = val1;
        }
//        System.out.println(ans.val);
        return ans;
    }

    void dfs(TreeNode node, int val, List<TreeNode> path) {
        if (node == null) return;
        if (node.val == val) {
            path.add(node);
            return;
        }
        if (node.val > val) {
            path.add(node);
            dfs(node.left, val, path);
        }
        if (node.val < val) {
            path.add(node);
            dfs(node.right, val,path);
        }
    }
}