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
    List<Integer> arr = new ArrayList<>();

    public int findSecondMinimumValue(TreeNode root) {
        traversal(root);
        List<Integer> collect = arr.stream().sorted().collect(Collectors.toList());
        if (collect.size() < 2) {
            return -1;
        }
        Integer secondSmall = collect.get(1);
        return secondSmall;
    }

    void traversal(TreeNode root) {
        if (root == null) return;
        int val = root.val;
        if (!arr.contains(val)) {
            arr.add(val);
        }
        traversal(root.left);
        traversal(root.right);

    }
}