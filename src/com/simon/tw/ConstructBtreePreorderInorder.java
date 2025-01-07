package com.simon.tw;

import com.simon.util.InputTreeUtil;
import com.simon.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class Solution105 { //https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;
        if (preLen != inLen) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>(preLen);
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preLen - 1, map, 0, inLen - 1);
    }

    /**
     * @param preorder 前序遍历序列
     * @param preorder 前序遍历 左界
     * @param preorder 前序遍历 右界
     * @param map      中序遍历序列，数值-下标对应关系
     * @param preorder 中序遍历 左界
     * @param preorder 中序遍历 右界
     */
    private TreeNode buildTree(int[] preorder, int preLeft, int preRight,
                               Map<Integer, Integer> map, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);
        root.left = buildTree(preorder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, inRight - 1);
        root.right = buildTree(preorder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight);
        return root;
    }

}

public class ConstructBtreePreorderInorder {
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        Solution105 solution105 = new Solution105();
        TreeNode root = solution105.buildTree(preorder, inorder);
        InputTreeUtil.displayBTreeVertically(root);
    }
}
