package com.simon;

import com.simon.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode treeNode = InputTreeUtil.inputBtree();
//            int i = InputUtil.inputIntMatrix();
            System.out.println(solution.minDepth(treeNode));
        }
    }


}


class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }
}