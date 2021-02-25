package com.simon;

import com.simon.util.*;

import java.util.*;
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
//    List<Integer> temp = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<>();
        constructPath(root, "", path);
        return path;
    }

    void constructPath(TreeNode root, String path, List<String> paths) {
        if (root == null) return;
        StringBuilder pathSB = new StringBuilder(path);
        pathSB.append(root.val);
        if (root.left == null && root.right == null) {
            paths.add(pathSB.toString());
        } else {
            pathSB.append("->");
            constructPath(root.left, pathSB.toString(), paths);
            constructPath(root.right, pathSB.toString(), paths);
        }
    }
//    public void dfs(TreeNode root) {
//        if (root == null) return;
//        temp.add(root.val);
//        if (root.left == null && root.right == null) {
//            ans.add(temp.stream().map(Object::toString).collect(Collectors.joining("->")));
//            temp.clear();//这样做会清楚掉保存到其他叶子结点共用的路径，temp 要带着一起递归
//        }
//        dfs(root.left);
//        dfs(root.right);
//    }
}
//[1,2,3,null,5]