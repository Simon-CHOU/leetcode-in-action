package com.simon.tdd;

import com.simon.util.TreeNode;

/**
 * LCR 143. 子结构判断
 * <p>
 * 给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有
 * 相同的结构和节点值。
 * 注意，空树不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值。
 */
public class LCR143TDD {
    /**
     * 判断 tree2 是否是 tree1 的子结构
     *
     * @param A tree1 的根节点
     * @param B tree2 的根节点
     * @return 如果 tree2 是 tree1 的子结构则返回 true，否则返回 false
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 如果 A 或 B 为空，则不符合子结构条件
        if (A == null || B == null) {
            return false;
        }

        // 三种情况满足其一即表示 B 是 A 的子结构：
        // 1. 从当前节点开始匹配
        // 2. B 是 A 的左子树的子结构
        // 3. B 是 A 的右子树的子结构
        return isSameStructure(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);  //!!这里三个调用顺序和方法不一样
    }

    /**
     * 判断从节点 A 开始的子树是否包含从节点 B 开始的结构
     *
     * @param A tree1 中的节点
     * @param B tree2 中的节点
     * @return 如果匹配成功返回 true，否则返回 false
     */
        private boolean isSameStructure(TreeNode A, TreeNode B) {
        // 如果 B 为空，说明 B 已经完全匹配
        if (B == null) {
            return true;
        }

        // 如果 A 为空但 B 不为空，说明不匹配
        if (A == null) {
            return false;
        }

        // 如果当前节点值不相等，说明不匹配
        if (A.val != B.val) {
            return false;
        }

        // 递归检查左右子树
        return isSameStructure(A.left, B.left) && isSameStructure(A.right, B.right);
    }

    public static void main(String[] args) {
        LCR143TDD solution = new LCR143TDD();

        // 测试用例1: [1,7,5], [6,1] -> false
        TreeNode tree1_1 = new TreeNode(1);
        tree1_1.left = new TreeNode(7);
        tree1_1.right = new TreeNode(5);

        TreeNode tree2_1 = new TreeNode(6);
        tree2_1.left = new TreeNode(1);

        boolean result1 = solution.isSubStructure(tree1_1, tree2_1);
        System.out.println("测试用例1 - 结果: " + result1 + " (期望: false) " + (result1 == false ? "PASS" : "FAIL"));

        // 测试用例2: [3,6,7,1,8], [6,1] -> true
        TreeNode tree1_2 = new TreeNode(3);
        tree1_2.left = new TreeNode(6);
        tree1_2.right = new TreeNode(7);
        tree1_2.left.left = new TreeNode(1);
        tree1_2.left.right = new TreeNode(8);

        TreeNode tree2_2 = new TreeNode(6);
        tree2_2.left = new TreeNode(1);

        boolean result2 = solution.isSubStructure(tree1_2, tree2_2);
        System.out.println("测试用例2 - 结果: " + result2 + " (期望: true) " + (result2 == true ? "PASS" : "FAIL"));

        // 测试用例3: 空树情况
        boolean result3 = solution.isSubStructure(tree1_2, null);
        System.out.println("测试用例3 - 结果: " + result3 + " (期望: false) " + (result3 == false ? "PASS" : "FAIL"));

        // 测试用例4: 完全匹配
        TreeNode tree1_4 = new TreeNode(1);
        tree1_4.left = new TreeNode(2);
        tree1_4.right = new TreeNode(3);

        TreeNode tree2_4 = new TreeNode(1);
        tree2_4.left = new TreeNode(2);
        tree2_4.right = new TreeNode(3);

        boolean result4 = solution.isSubStructure(tree1_4, tree2_4);
        System.out.println("测试用例4 - 结果: " + result4 + " (期望: true) " + (result4 == true ? "PASS" : "FAIL"));

        // 测试用例5: 部分匹配
        TreeNode tree1_5 = new TreeNode(4);
        tree1_5.left = new TreeNode(2);
        tree1_5.right = new TreeNode(3);
        tree1_5.left.left = new TreeNode(4);
        tree1_5.left.right = new TreeNode(5);
        tree1_5.right.left = new TreeNode(6);
        tree1_5.right.right = new TreeNode(7);

        TreeNode tree2_5 = new TreeNode(2);
        tree2_5.left = new TreeNode(4);
        tree2_5.right = new TreeNode(5);

        boolean result5 = solution.isSubStructure(tree1_5, tree2_5);
        System.out.println("测试用例5 - 结果: " + result5 + " (期望: true) " + (result5 == true ? "PASS" : "FAIL"));

        // 测试用例6: [4,2,3,4,5,6,7,8,9]
        //[4,8,9]
        TreeNode tree1_6 = new TreeNode(4);
        tree1_6.left = new TreeNode(2);
        tree1_6.right = new TreeNode(3);
        tree1_6.left.left = new TreeNode(4);
        tree1_6.left.right = new TreeNode(5);
        tree1_6.right.left = new TreeNode(6);
        tree1_6.right.right = new TreeNode(7);
        tree1_6.left.left.left = new TreeNode(8);
        tree1_6.left.left.right = new TreeNode(9);

        TreeNode tree2_6 = new TreeNode(4);
        tree2_6.left = new TreeNode(8);
        tree2_6.right = new TreeNode(9);

        boolean result6 = solution.isSubStructure(tree1_6, tree2_6);
        System.out.println("测试用例6 - 结果: " + result6 + " (期望: true) " + (result6 == true ? "PASS" : "FAIL"));
    }
}
