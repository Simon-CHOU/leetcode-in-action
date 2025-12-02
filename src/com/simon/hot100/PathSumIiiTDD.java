package com.simon.hot100;

import com.simon.util.TreeNode;

/**
 * PathSumIiiTDD
 * <p>
 * 解法：前缀和 + 哈希表
 * <p>
 * 使用 DFS 遍历二叉树，维护从根到当前节点的路径和，
 * 并通过哈希表统计路径和出现次数，判断是否存在满足条件的路径。
 */
class PathSumIiiTDD {

    /**
     * 计算路径总和 III 的解法
     *
     * @param root      二叉树根节点
     * @param targetSum 目标路径和
     * @return 满足条件的路径数量
     */
    public int pathSum(TreeNode root, int targetSum) {
        java.util.Map<Long, Integer> prefixMap = new java.util.HashMap<>();
        prefixMap.put(0L, 1); // 初始路径和为0出现一次
        return dfs(root, 0L, targetSum, prefixMap);
    }

    /**
     * 深度优先遍历辅助函数
     *
     * @param node      当前节点
     * @param currSum   当前路径和
     * @param targetSum 目标路径和
     * @param prefixMap 路径和哈希表
     * @return 满足条件的路径数量
     */
    private int dfs(TreeNode node, long currSum, int targetSum,
                    java.util.Map<Long, Integer> prefixMap) {
        if (node == null) {
            return 0;
        }

        currSum += node.val;
        int count = prefixMap.getOrDefault(currSum - targetSum, 0);

        prefixMap.put(currSum, prefixMap.getOrDefault(currSum, 0) + 1);

        count += dfs(node.left, currSum, targetSum, prefixMap);
        count += dfs(node.right, currSum, targetSum, prefixMap);

        // 回溯，恢复状态
        prefixMap.put(currSum, prefixMap.get(currSum) - 1);

        return count;
    }

    /**
     * 主函数：TDD测试入口
     */
    public static void main(String[] args) {
        PathSumIiiTDD solver = new PathSumIiiTDD();

        // 构造测试用例
        TreeNode root = new TreeNode(10,
                new TreeNode(5,
                        new TreeNode(3, new TreeNode(3), new TreeNode(-2)),
                        new TreeNode(2, null, new TreeNode(1))),
                new TreeNode(-3, null, new TreeNode(11)));

        int result = solver.pathSum(root, 8);

        if (result == 3) {
            System.out.println("PASS: Expected 3, got " + result);
        } else {
            System.out.println("FAIL: Expected 3, got " + result);
        }
    }
}