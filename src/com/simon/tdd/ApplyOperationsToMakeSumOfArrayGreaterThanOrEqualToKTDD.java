package com.simon.tdd;

/**
 * 3091. Apply Operations to Make Sum of Array Greater Than or Equal to K
 * <p>题意：初始数组 nums = [1]。你可以执行两类操作任意次：其一，选择任意元素加 1；其二，复制任意元素并追加到数组末尾。目标是在最少操作次数内使数组元素之和 \u2265 k。
 * <p>核心思路（数学最优解）：先把某个元素增大到 a+1（做 a 次加一），再复制该元素 b 次，最终数组中会有 b+1 个值为 a+1 的元素，总和为 (a+1)*(b+1)。因此我们要在 (a+1)*(b+1) \u2265 k 的约束下最小化 a+b。令 x=a+1、y=b+1，则目标最小化 x+y-2。在连续值放松下，xy \u2265 k 时 x+y 最小由 x=y=\u221a{k} 达到，离散到整数得到分段公式：
 * <p>设 s=floor(\u221a{k})，若 s*s==k，则最少操作数为 2*s-2；若 s*(s+1) \u2265 k，则为 2*s-1；否则为 2*s。
 * <p>复杂度：O(1) 时间，O(1) 空间。
 * <p>实现风格：Clean Code；同时在 main 方法中使用简易 TDD（打印 PASS/FAIL）。
 */
public class ApplyOperationsToMakeSumOfArrayGreaterThanOrEqualToKTDD {

    /**
     * 计算使得最终数组元素之和 \u2265 k 的最少操作次数（数学最优解）。
     * <p>思路：令 s=floor(\u221a{k})，分三种情况返回对应的最少操作数：
     * <p>1) 完全平方：k==s*s，返回 2*s-2；
     * <p>2) 介于 s*s 与 s*(s+1) 之间：返回 2*s-1；
     * <p>3) 其他更大：返回 2*s。
     *
     * @param k 目标和（正整数）
     * @return 最少操作次数
     */
    public int minOperations(int k) {
        if (k <= 1) {
            return 0; // 初始和为 1，若 k<=1 无需任何操作
        }
        int s = (int) Math.floor(Math.sqrt(k));
        if (s * s == k) {
            return 2 * s - 2;
        } else if (s * (s + 1) >= k) {
            return 2 * s - 1;
        } else {
            return 2 * s;
        }
    }

    /**
     * 简易断言工具：打印 PASS/FAIL，方便 TDD 风格校验。
     * <p>不引入 JUnit，仅使用控制台输出，避免额外依赖。
     *
     * @param caseName 用例名称
     * @param expected 期望值
     * @param actual   实际值
     */
    private static void assertEquals(String caseName, int expected, int actual) {
        if (expected == actual) {
            System.out.println("[PASS] " + caseName + " expected=" + expected + " actual=" + actual);
        } else {
            System.out.println("[FAIL] " + caseName + " expected=" + expected + " actual=" + actual);
        }
    }

    /**
     * 使用 main 方法进行 TDD：构造若干测试用例并输出 PASS/FAIL。
     * <p>这些用例覆盖典型边界与代表性场景：k=1（无需操作）、小值、完全平方、介于平方与相邻乘积之间的值等。
     */
    public static void main(String[] args) {
        ApplyOperationsToMakeSumOfArrayGreaterThanOrEqualToKTDD solver = new ApplyOperationsToMakeSumOfArrayGreaterThanOrEqualToKTDD();

        // 基本边界
        assertEquals("k=1", 0, solver.minOperations(1));
        assertEquals("k=2", 1, solver.minOperations(2)); // 复制一次：总和 2
        assertEquals("k=3", 2, solver.minOperations(3));

        // 完全平方与相邻区间
        assertEquals("k=4", 2, solver.minOperations(4)); // s=2, 2*2-2=2
        assertEquals("k=5", 3, solver.minOperations(5)); // s=2, 2*2-1=3
        assertEquals("k=9", 4, solver.minOperations(9)); // s=3, 2*3-2=4

        // 题目示例与扩展
        assertEquals("k=11", 5, solver.minOperations(11)); // 先加到 4（3 次），再复制两次：sum=12
        assertEquals("k=12", 5, solver.minOperations(12)); // s=3, 3*4=12, 2*3-1=5
        assertEquals("k=13", 6, solver.minOperations(13)); // s=3, 3*4=12<13, 2*3=6

        // 压力边界（题目上限）
        assertEquals("k=100000", 631, solver.minOperations(100000)); // s=316, 316*317=100172 \u2265 k
    }
}
