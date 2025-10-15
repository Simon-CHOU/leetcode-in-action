package com.simon.hot100;

import com.simon.util.TreeNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
     * 使用广度优先搜索（BFS）实现最近公共祖先查找。
     * <p>思路：先用 BFS 从根节点出发，记录每个节点的父指针 {@code parent}；当 {@code p} 和 {@code q} 都被发现后，
     * 构造 {@code p} 的祖先集合，然后从 {@code q} 沿父指针向上移动，第一次命中祖先集合的节点即为 LCA。
     * <p>与递归版保持输入输出一致：若 {@code root} 为 {@code null}，返回 {@code null}；若 {@code p == q}，返回 {@code p}；
     * 若任一节点不在树中，返回 {@code null}。
     *
     * @param root 根节点
     * @param p    节点 p（期望存在于树中）
     * @param q    节点 q（期望存在于树中）
     * @return 最近公共祖先节点；若无（如节点不在树中）返回 {@code null}
     */
    public TreeNode lowestCommonAncestorBFS(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == null || q == null) {
            return null;
        }
        if (p == q) {
            return p;
        }

        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        parent.put(root, null);
        queue.offer(root);

        // BFS：直到同时找到 p 和 q 或遍历结束
        while (!queue.isEmpty() && !(parent.containsKey(p) && parent.containsKey(q))) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }

        // 若任一未找到，返回 null
        if (!parent.containsKey(p) || !parent.containsKey(q)) {
            return null;
        }

        // 收集 p 的祖先集合
        Set<TreeNode> ancestors = new HashSet<>();
        TreeNode cur = p;
        while (cur != null) {
            ancestors.add(cur);
            cur = parent.get(cur);
        }

        // 从 q 向上，首次命中 ancestors 的即为 LCA
        cur = q;
        while (!ancestors.contains(cur)) {
            cur = parent.get(cur);
        }
        return cur;
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

        // BFS 版本的测试用例（保证与递归版输出一致，并覆盖边界）
        runTestBFS(solution, n3, n5, n1, n3, 101, "BFS p=5,q=1 期望LCA=3");
        runTestBFS(solution, n3, n5, n4, n5, 102, "BFS p=5,q=4 期望LCA=5");
        runTestBFS(solution, n3, n6, n4, n5, 103, "BFS p=6,q=4 期望LCA=5");
        runTestBFS(solution, n3, n7, n8, n3, 104, "BFS p=7,q=8 期望LCA=3");
        runTestBFS(solution, n3, n3, n4, n3, 105, "BFS p=3,q=4 期望LCA=3（祖先为自身）");
        runTestBFS(solution, n3, n3, n3, n3, 106, "BFS p=3,q=3 期望LCA=3（两个节点相同）");

        // 额外边界测试：同子树不同分支
        runTestBFS(solution, n3, n7, n4, n2, 107, "BFS p=7,q=4 期望LCA=2（同父节点的两分支）");
        // 额外边界测试：同一父节点的左右子节点
        runTestBFS(solution, n3, n0, n8, n1, 108, "BFS p=0,q=8 期望LCA=1（兄弟节点）");
        // 额外边界测试：root 为 null
        runTestBFS(solution, null, n5, n1, null, 109, "BFS root=null 期望LCA=null");
        // 额外边界测试：节点不在树中
        TreeNode outside = new TreeNode(999);
        runTestBFS(solution, n3, n5, outside, null, 110, "BFS 节点不在树中 期望LCA=null");
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

    /**
     * 运行 BFS 版本的测试用例，打印 PASS/FAIL。
     * <p>与递归版一致，按引用对比（==）验证返回的最近公共祖先对象。
     *
     * @param solution  解法实例
     * @param root      测试树根节点
     * @param p         测试节点 p
     * @param q         测试节点 q
     * @param expected  期望的最近公共祖先节点对象
     * @param caseId    测试用例编号
     * @param caseTitle 测试用例标题描述
     */
    private static void runTestBFS(LowestCommonAncestorOfABinaryTreeTDD solution,
                                   TreeNode root,
                                   TreeNode p,
                                   TreeNode q,
                                   TreeNode expected,
                                   int caseId,
                                   String caseTitle) {
        TreeNode actual = solution.lowestCommonAncestorBFS(root, p, q);
        boolean pass = actual == expected;
        System.out.println("Case " + caseId + " | " + caseTitle + " | expected="
                + (expected == null ? "null" : expected.val)
                + ", actual=" + (actual == null ? "null" : actual.val)
                + " -> " + (pass ? "PASS" : "FAIL"));
    }
}