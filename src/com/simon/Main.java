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
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}