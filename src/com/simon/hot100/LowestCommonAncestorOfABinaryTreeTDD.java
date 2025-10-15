package com.simon.hot100;

import com.simon.util.TreeNode;

/**
 * 236. 二叉树的最近公共祖先（Lowest Common Ancestor of a Binary Tree）
 * <p>题意：给定二叉树根节点 {@code root} 与两个不同节点 {@code p}、{@code q}（都在树中且值唯一），返回它们的最近公共祖先。
 * <p>最近公共祖先（LCA）定义：在同时作为 {@code p} 和 {@code q} 的祖先的所有节点中，距离这两个节点最近的一个。
 * <p>解法概述：使用后序遍历的递归。遇到空、或命中 {@code p}/{@code q} 时直接返回当前节点；递归处理左右子树，若左右返回值均非空，当前节点为 LCA；否则返回非空一侧。
 */
public class LowestCommonAncestorOfABinaryTreeTDD {

    /**
     * 在任意二叉树中查找两节点的最近公共祖先（非 BST 专属）。
     * <p>核心思路：后序递归，函数返回“在当前子树中找到的目标节点或最近公共祖先”的指针。
     * <p>返回规则：
     * <p>1）若 {@code root} 为 {@code null} 或等于 {@code p}/{@code q}，返回 {@code root}；
     * <p>2）递归求左右子树的结果 {@code left}/{@code right}；
     * <p>3）若 {@code left} 与 {@code right} 都非空，说明 {@code p}/{@code q} 分别在两侧，最近公共祖先即为 {@code root}；
     * <p>4）否则返回非空一侧（表示在该侧子树中找到了目标或祖先）。
     * <p>时间复杂度：O(N)，空间复杂度：O(H)，其中 N 为节点数、H 为树高（递归栈）。
     *
     * @param root 根节点
     * @param p    节点 p（保证存在）
     * @param q    节点 q（保证存在）
     * @return 最近公共祖先节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * 使用 main 方法进行 TDD 风格的测试，不引入 JUnit，日志打印 PASS/FAIL。
     * <p>测试覆盖：
     * <p>- 示例用例：p=5,q=1，LCA=3；p=5,q=4，LCA=5；
     * <p>- 其它用例：p=6,q=4，LCA=5；p=7,q=8，LCA=3；p=3,q=4，LCA=3；p=3,q=3，LCA=3。
     */
    public static void main(String[] args) {
        LowestCommonAncestorOfABinaryTreeTDD solution = new LowestCommonAncestorOfABinaryTreeTDD();

        // 手动构建示例二叉树（LeetCode 236 示例）：
        //            3
        //          /   \
        //         5     1
        //        / \   / \
        //       6   2 0   8
        //          / \
        //         7   4
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);

        n3.left = n5;  n3.right = n1;
        n5.left = n6;  n5.right = n2;
        n1.left = n0;  n1.right = n8;
        n2.left = n7;  n2.right = n4;

        runTest(solution, n3, n5, n1, n3, 1, "p=5,q=1 期望LCA=3");
        runTest(solution, n3, n5, n4, n5, 2, "p=5,q=4 期望LCA=5");
        runTest(solution, n3, n6, n4, n5, 3, "p=6,q=4 期望LCA=5");
        runTest(solution, n3, n7, n8, n3, 4, "p=7,q=8 期望LCA=3");
        runTest(solution, n3, n3, n4, n3, 5, "p=3,q=4 期望LCA=3（祖先可为自身）");
        runTest(solution, n3, n3, n3, n3, 6, "p=3,q=3 期望LCA=3（两个节点相同）");
    }

    /**
     * 运行单个测试用例，打印 PASS/FAIL。
     * <p>通过引用对比（==）验证最近公共祖先是否与期望节点对象一致。
     *
     * @param solution  解法实例
     * @param root      测试树根节点
     * @param p         测试节点 p
     * @param q         测试节点 q
     * @param expected  期望的最近公共祖先节点对象
     * @param caseId    测试用例编号
     * @param caseTitle 测试用例标题描述
     */
    private static void runTest(LowestCommonAncestorOfABinaryTreeTDD solution,
                                TreeNode root,
                                TreeNode p,
                                TreeNode q,
                                TreeNode expected,
                                int caseId,
                                String caseTitle) {
        TreeNode actual = solution.lowestCommonAncestor(root, p, q);
        boolean pass = actual == expected;
        System.out.println("Case " + caseId + " | " + caseTitle + " | expected="
                + (expected == null ? "null" : expected.val)
                + ", actual=" + (actual == null ? "null" : actual.val)
                + " -> " + (pass ? "PASS" : "FAIL"));
    }
}