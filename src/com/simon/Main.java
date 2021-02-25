package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode s = InputTreeUtil.inputBtree();
            TreeNode t = InputTreeUtil.inputBtree();
            System.out.println(solution.isSameTree(s, t));
        }
    }


}

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }else if(p==null||q==null){
            return false;
        }//开头必须判空
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(p);
        q2.offer(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();
            if (node1.val != node2.val) {
                return false;
            }
            TreeNode node1left = node1.left;
            TreeNode node1right = node1.right;
            TreeNode node2left = node2.left;
            TreeNode node2right = node2.right;
            if (node1left == null ^ node2left == null) {
                return false;
            }
            if (node1right == null ^ node2right == null) {
                return false;
            }
            if (node1left != null) {
                q1.offer(node1left);
            }
            if (node1right != null) {
                q1.offer(node1right);
            }
            if (node2left != null) {
                q2.offer(node2left);
            }
            if (node2right != null) {
                q2.offer(node2right);
            }
        }
        return q1.isEmpty() && q2.isEmpty();
    }
}