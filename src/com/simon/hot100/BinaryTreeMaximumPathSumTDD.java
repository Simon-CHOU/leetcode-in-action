package com.simon.hot100;

import com.simon.util.TreeNode;

// BinaryTreeMaximumPathSumTDD.java
/**
 * <p>LeetCode 124. 二叉树中的最大路径和
 * <p>解题思路：后序DFS，维护全局最大值和子树单向贡献值。
 * <p>时间复杂度：O(N)，空间复杂度：O(H)（H 为树高）。
 */
public class BinaryTreeMaximumPathSumTDD {

    /** 全局最大路径和 */
    private int globalMax;

    /**
     * <p>返回二叉树中的最大路径和。
     * <p>路径定义为：从任意节点出发到任意节点，且父-子连接方向不变。
     *
     * @param root 根节点
     * @return 最大路径和
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        globalMax = Integer.MIN_VALUE;
        dfs(root);
        return globalMax;
    }

    /**
     * <p>后序遍历：计算以当前节点为根的子树的最大单向贡献值。
     * <p>单向贡献值：从当前节点出发，向下选择左或右子树的最大路径和（至少包含当前节点）。
     * <p>在递归过程中，用「左+中+右」形状的封闭路径更新全局答案。
     *
     * @param node 当前节点
     * @return 子树的最大单向贡献值（非负，负数则视为 0）
     */
    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // 递归计算左右子树的单向贡献，负数直接舍去（用 0 截断）
        int leftGain = Math.max(dfs(node.left), 0);
        int rightGain = Math.max(dfs(node.right), 0);

        // 封闭路径：左右可以同时使用，形成「拐弯」路径
        int closedPath = node.val + leftGain + rightGain;
        globalMax = Math.max(globalMax, closedPath);

        // 开放路径：只能选一边返回给父节点
        return node.val + Math.max(leftGain, rightGain);
    }

    // ------------------------------
    // TDD 测试驱动：在 main 中构造用例并断言
    // ------------------------------
    public static void main(String[] args) {
        BinaryTreeMaximumPathSumTDD solver = new BinaryTreeMaximumPathSumTDD();

        // 测试用例 1: [1,2,3]
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3)
        );
        int ans1 = solver.maxPathSum(root1);
        System.out.println("Case 1: " + (ans1 == 6 ? "PASS" : "FAIL") + " (expected=6, got=" + ans1 + ")");

        // 测试用例 2: [-10,9,20,null,null,15,7] -> 42
        TreeNode root2 = new TreeNode(-10,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)
                )
        );
        int ans2 = solver.maxPathSum(root2);
        System.out.println("Case 2: " + (ans2 == 42 ? "PASS" : "FAIL") + " (expected=42, got=" + ans2 + ")");

        // 测试用例 3: 全负数 [-3,-2,-1]
        TreeNode root3 = new TreeNode(-3,
                new TreeNode(-2),
                new TreeNode(-1)
        );
        int ans3 = solver.maxPathSum(root3);
        System.out.println("Case 3: " + (ans3 == -1 ? "PASS" : "FAIL") + " (expected=-1, got=" + ans3 + ")");

        // 测试用例 4: 单边链 [5,4,null,3,null,2]
        TreeNode root4 = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(3,
                                new TreeNode(2),
                                null
                        ),
                        null
                ),
                null
        );
        int ans4 = solver.maxPathSum(root4);
        System.out.println("Case 4: " + (ans4 == 14 ? "PASS" : "FAIL") + " (expected=14, got=" + ans4 + ")");
    }
}