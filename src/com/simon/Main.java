package com.simon;

import com.simon.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode treeNode = InputTreeUtil.inputBtree();
//            solution.invertTree(treeNode);
            InputTreeUtil.displayBTreeVertically(treeNode);
//            int i = InputUtil.inputIntMatrix();
//            System.out.println(solution.checkStraightLine(ints, i));
        }
    }


}


class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}