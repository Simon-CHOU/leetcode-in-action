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
            System.out.println(solution.lowestCommonAncestor(s, t, q).val);
        }
    }
}

class Solution {
    //这两个节点放在一起遍历，我们就省去了存储路径需要的空间
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true){
            if(p.val < ancestor.val&&q.val<ancestor.val){
                ancestor = ancestor.left;
            }else if(p.val>ancestor.val && q.val>ancestor.val){
                ancestor = ancestor.right;
            }else{
                break;
            }
        }
        return ancestor;
    }
}