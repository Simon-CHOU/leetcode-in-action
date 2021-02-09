package com.simon;

import com.simon.util.*;

import java.util.LinkedList;
import java.util.Queue;

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
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.left != null) {
                if (isLeafNode(temp.left)) {
                    res += temp.left.val;
                } else {
                    queue.offer(temp.left);
                }
            }
            if (temp.right != null) {
                if (!isLeafNode(temp.right)) {
                    queue.offer(temp.right);
                }
            }
        }
        return res;
    }

    boolean isLeafNode(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
//[3,9,20,null,null,15,7]
//24