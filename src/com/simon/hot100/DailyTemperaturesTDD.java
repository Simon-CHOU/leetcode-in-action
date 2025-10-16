package com.simon.hot100;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 739. 每日温度 题解（TDD + 单调栈）
 * <p>给定每日温度数组，返回每一天需要等待多少天，直到出现更高温度；若不存在更高温度，则该天返回 0。
 * <p>核心思路：使用「单调递减栈」存储未被更高温度匹配的索引。当遇到更高温度时，弹出栈顶索引并计算等待天数。
 * <p>实现特点：
 * <p>1) 线性时间复杂度 O(n)，每个索引最多入栈一次、出栈一次。
 * <p>2) 额外空间 O(n) 用于栈与结果数组。
 */
public class DailyTemperaturesTDD {

    /**
     * 计算每一天需要等待多少天才会出现更高温度。
     * <p>算法：维护单调递减栈（栈中存储温度数组的索引）。对每个新索引 i：
     * <p>若当前温度高于栈顶索引的温度，则弹栈并为该索引记录等待天数；重复直到栈为空或栈顶温度不小于当前温度。随后把 i 入栈。
     *
     * @param temperatures 输入温度数组；允许为 null。<p>当为 null 时返回长度为 0 的数组。
     * @return 每一天到更高温度的等待天数数组。<p>若没有更高温度则该位置为 0。
     * <p>复杂度：时间 O(n)；空间 O(n)。
     */
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null) {
            return new int[0];
        }

        int n = temperatures.length;
        int[] daysToWait = new int[n];
        Deque<Integer> indexStack = new ArrayDeque<>(Math.max(16, n));

        for (int dayIndex = 0; dayIndex < n; dayIndex++) {
            int todayTemperature = temperatures[dayIndex];

            // 将所有比今天温度低的索引出栈，并记录其等待天数
            while (!indexStack.isEmpty() && todayTemperature > temperatures[indexStack.peek()]) {
                int prevIndex = indexStack.pop();
                daysToWait[prevIndex] = dayIndex - prevIndex;
            }

            // 今天仍未匹配到更高温度，入栈等待未来更高温度来匹配
            indexStack.push(dayIndex);
        }

        return daysToWait;
    }

    /**
     * 使用 main 方法进行 TDD 式测试，输出 PASS/FAIL 日志与汇总。
     * <p>不引入任何测试框架，仅通过断言函数校验结果。
     *
     * @param args 命令行参数（未使用）。
     */
    public static void main(String[] args) {
        runAllTests();
    }

    /**
     * 运行所有测试用例并打印汇总。
     * <p>覆盖：递增、递减、相等、典型样例、空与 null 输入等场景。
     */
    private static void runAllTests() {
        DailyTemperaturesTDD solver = new DailyTemperaturesTDD();

        int total = 0;
        int passed = 0;

        total++;
        passed += checkIntArrayEquals(
                "典型样例 [73,74,75,71,69,72,76,73]",
                new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                solver.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})
        ) ? 1 : 0;

        total++;
        passed += checkIntArrayEquals(
                "严格递增 [30,31,32]",
                new int[]{1, 1, 0},
                solver.dailyTemperatures(new int[]{30, 31, 32})
        ) ? 1 : 0;

        total++;
        passed += checkIntArrayEquals(
                "严格递减 [30,29,28]",
                new int[]{0, 0, 0},
                solver.dailyTemperatures(new int[]{30, 29, 28})
        ) ? 1 : 0;

        total++;
        passed += checkIntArrayEquals(
                "相等温度 [70,70,70]",
                new int[]{0, 0, 0},
                solver.dailyTemperatures(new int[]{70, 70, 70})
        ) ? 1 : 0;

        total++;
        passed += checkIntArrayEquals(
                "空数组 []",
                new int[]{},
                solver.dailyTemperatures(new int[]{})
        ) ? 1 : 0;

        total++;
        passed += checkIntArrayEquals(
                "单元素 [70]",
                new int[]{0},
                solver.dailyTemperatures(new int[]{70})
        ) ? 1 : 0;

        total++;
        passed += checkIntArrayEquals(
                "递增长序列 [30,40,50,60]",
                new int[]{1, 1, 1, 0},
                solver.dailyTemperatures(new int[]{30, 40, 50, 60})
        ) ? 1 : 0;

        total++;
        passed += checkIntArrayEquals(
                "重复高温与低温混合",
                new int[]{1, 0, 0, 2, 1, 0, 1, 0, 0, 0, 0},
                solver.dailyTemperatures(new int[]{34, 80, 80, 34, 34, 80, 34, 80, 80, 80, 80})
        ) ? 1 : 0;

        total++;
        passed += checkIntArrayEquals(
                "null 输入",
                new int[]{},
                solver.dailyTemperatures(null)
        ) ? 1 : 0;

        int failed = total - passed;
        System.out.println("[SUMMARY] total=" + total + ", pass=" + passed + ", fail=" + failed);
    }

    /**
     * 校验两个整型数组是否一致并打印 PASS/FAIL。
     * <p>若一致打印 PASS，否则打印 FAIL 并展示期望与实际值。
     *
     * @param name     测试用例名称。
     * @param expected 期望结果数组。
     * @param actual   实际结果数组。
     * @return 是否通过。
     */
    private static boolean checkIntArrayEquals(String name, int[] expected, int[] actual) {
        boolean pass = Arrays.equals(expected, actual);
        System.out.println((pass ? "[PASS] " : "[FAIL] ")
                + name
                + " expected=" + Arrays.toString(expected)
                + " actual=" + Arrays.toString(actual));
        return pass;
    }
}
    