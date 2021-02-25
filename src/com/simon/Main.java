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
            System.out.println(solution.binaryTreePaths(s));
        }
    }


}

class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {return paths;}
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(String.valueOf(root.val));
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    pathQueue.offer(new StringBuilder(path).append("->").append(node.left.val).toString());
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    pathQueue.offer(new StringBuilder(path).append("->").append(node.right.val).toString());
                }
            }
        }
        return paths;
    }
}
//[1,2,3,null,5]