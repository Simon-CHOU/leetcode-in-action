package com.simon.hot100;
/**
 * DammingDistanceTDD.java
 *
 * <p>Clean Code 约定：
 * <p>- 单一职责：hammingDistance 只负责计算汉明距离
 * <p>- 可读性优先：明确命名与简洁逻辑
 * <p>- 封装细节：提供标准库实现与手写实现，主逻辑可自由切换
 */
public class DammingDistanceTDD {

    /**
     * 计算两个非负整数的汉明距离：它们的二进制表示在不同位的数量。
     *
     * <p>定义：hammingDistance(x, y) = bitCount(x XOR y)
     * <p>实现说明：
     * <p>- 首选标准库：Integer.bitCount 提供高效的位计数
     * <p>- 若需手写：可使用 countBitsKernighan(diff)
     *
     * @param x 非负整数
     * @param y 非负整数
     * @return 不同位的数量（汉明距离）
     */
    public int hammingDistance(int x, int y) {
        int diff = x ^ y;
        return Integer.bitCount(diff);
        // 如果希望手写算法：
        // return countBitsKernighan(diff);
    }

    /**
     * 使用 Brian Kernighan 算法统计整数的置位数（1 的个数）。
     *
     * <p>思路：每次执行 n = n & (n - 1) 会清除 n 中最低位的 1。
     * <p>循环次数即为 1 的总数。
     *
     * @param n 非负整数
     * @return 置位数（1 的数量）
     */
    public int countBitsKernighan(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    /**
     * <p>主方法：以 TDD 风格运行若干测试用例并输出 PASS/FAIL。
     * <p>不引入任何测试框架，使用简单断言与日志。
     */
    public static void main(String[] args) {
        DammingDistanceTDD solver = new DammingDistanceTDD();

        // 测试用例集合
        TestCase[] cases = new TestCase[] {
                new TestCase(1, 4, 2, "Basic example: 1 vs 4"),
                new TestCase(3, 1, 1, "Single bit diff: 3 vs 1"),
                new TestCase(0, 0, 0, "Zeros: 0 vs 0"),
                new TestCase(7, 7, 0, "Same numbers: 7 vs 7"),
                new TestCase(8, 0, 1, "Power of two vs 0: 8 vs 0"),
                new TestCase(15, 0, 4, "All lower 4 bits: 15 vs 0"),
                new TestCase(31, 14, 3, "Mixed bits: 31 vs 14"),
                new TestCase(Integer.MAX_VALUE, 0, 31, "Max int vs 0"),
                new TestCase(0b101010, 0b010101, 6, "Alternating bits")
        };

        int pass = 0;
        for (TestCase tc : cases) {
            int actual = solver.hammingDistance(tc.x, tc.y);
            boolean ok = (actual == tc.expected);
            if (ok) {
                pass++;
                System.out.println("[PASS] " + tc.name
                        + " | x=" + tc.x + ", y=" + tc.y
                        + " => expected=" + tc.expected + ", actual=" + actual);
            } else {
                System.out.println("[FAIL] " + tc.name
                        + " | x=" + tc.x + ", y=" + tc.y
                        + " => expected=" + tc.expected + ", actual=" + actual);
            }
        }
        System.out.println("Summary: PASS " + pass + " / " + cases.length);
    }

    /**
     * 简单测试用例数据结构。
     *
     * <p>包含：输入 x、y，期望值 expected，以及用例名称 name。
     */
    private static class TestCase {
        final int x;
        final int y;
        final int expected;
        final String name;

        TestCase(int x, int y, int expected, String name) {
            this.x = x;
            this.y = y;
            this.expected = expected;
            this.name = name;
        }
    }
}