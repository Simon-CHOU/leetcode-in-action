package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            TreeNode treeNode = InputTreeUtil.inputBtree();
            System.out.println(solution.findSecondMinimumValue(treeNode));
        }
    }


}


class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        long secondSmall = traversal(root, root.val, Long.MAX_VALUE);
        return (int) (secondSmall == Long.MAX_VALUE ? -1 : secondSmall);
    }

    long traversal(TreeNode root, long num, long cur) {
        if (root == null) return cur;
        if (root.val > num) {
            cur = Math.min(cur, root.val);
        }
        return Math.min(traversal(root.left, num, cur),
                traversal(root.right, num, cur));
    }
}
/*
输入：root = [2,2,5,null,null,5,7]
输出：5

输入：root = [2,2,2147483647]
输出：2147483647 (integer 最大值)
 */