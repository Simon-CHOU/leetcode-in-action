package com.simon.tdd;
/**
 * LeetCode 392: 判断子序列 (Is Subsequence)
 * <p>
 * 【问题分析 - 金字塔原理】
 * 核心问题：判断字符串s是否为字符串t的子序列
 * 关键概念：子序列 = 保持相对顺序的字符序列（可以不连续）
 * 算法选择：双指针法 - 贪心策略的经典应用
 * <p>
 * 【数据结构与算法考察点】
 * 1. 双指针技术 (Two Pointers)
 * 2. 贪心算法思想 (Greedy Algorithm)
 * 3. 字符串匹配 (String Matching)
 * <p>
 * 【算法核心思想】
 * 使用两个指针分别指向s和t，当字符匹配时两个指针都前进，
 * 否则只有t的指针前进。最终判断s的指针是否走完。
 * <p>
 * 【时间复杂度】O(n) - n为字符串t的长度
 * 【空间复杂度】O(1) - 只使用常数额外空间
 *
 * @author Simon
 * @version 1.0
 */
public class IsSubsequenceTDD {
    
    /**
     * 判断字符串s是否为字符串t的子序列
     * <p>
     * 算法步骤：
     * 1. 初始化两个指针i=0(指向s), j=0(指向t)
     * 2. 遍历字符串t，当s[i] == t[j]时，i++
     * 3. 无论是否匹配，j都要++
     * 4. 最终判断i是否等于s.length()
     * <p>
     * ASCII算法执行示例：
     * s = "ace", t = "aec"
     * <p>
     * 初始状态:
     * s: a c e
     *    ↑
     *    i=0
     * t: a e c
     *    ↑
     *    j=0
     * <p>
     * 步骤1: s[0]='a' == t[0]='a' ✓
     * s: a c e
     *      ↑
     *      i=1
     * t: a e c
     *      ↑
     *      j=1
     * <p>
     * 步骤2: s[1]='c' != t[1]='e' ✗
     * s: a c e
     *      ↑
     *      i=1
     * t: a e c
     *        ↑
     *        j=2
     * <p>
     * 步骤3: s[1]='c' == t[2]='c' ✓
     * s: a c e
     *        ↑
     *        i=2
     * t: a e c
     *          ↑
     *          j=3
     * <p>
     * 结果: i=2 != s.length()=3, 返回false
     *
     * @param s 待检查的子序列字符串
     * @param t 目标字符串
     * @return 如果s是t的子序列返回true，否则返回false
     */
    public boolean isSubsequence(String s, String t) {
        // 边界条件处理
        if (s == null || t == null) {
            return false;
        }
        
        if (s.isEmpty()) {
            return true;
        }
        
        // 双指针实现
        int sPointer = 0;
        int tPointer = 0;
        
        while (tPointer < t.length() && sPointer < s.length()) {
            if (s.charAt(sPointer) == t.charAt(tPointer)) {
                sPointer++;
            }
            tPointer++;
        }
        
        return sPointer == s.length();
    }
    
    /**
     * 测试用例类 - 遵循TDD模式
     */
    private static class TestCase {
        String s;
        String t;
        boolean expected;
        String description;
        
        TestCase(String s, String t, boolean expected, String description) {
            this.s = s;
            this.t = t;
            this.expected = expected;
            this.description = description;
        }
    }
    
    /**
     * 执行单个测试用例
     * 
     * @param testCase 测试用例
     * @param solution 解决方案实例
     * @return 测试是否通过
     */
    private static boolean runTestCase(TestCase testCase, IsSubsequenceTDD solution) {
        boolean actual = solution.isSubsequence(testCase.s, testCase.t);
        boolean passed = actual == testCase.expected;
        
        System.out.printf("测试: %s\n", testCase.description);
        System.out.printf("输入: s=\"%s\", t=\"%s\"\n", testCase.s, testCase.t);
        System.out.printf("期望: %s, 实际: %s\n", testCase.expected, actual);
        System.out.printf("结果: %s\n\n", passed ? "PASS" : "FAIL");
        
        return passed;
    }
    
    /**
     * 主方法 - TDD测试驱动
     * <p>
     * 测试用例设计原则：
     * 1. 边界条件：空字符串、null值
     * 2. 正常情况：存在子序列、不存在子序列
     * 3. 特殊情况：相同字符串、单字符
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        IsSubsequenceTDD solution = new IsSubsequenceTDD();
        
        // 测试用例集合
        TestCase[] testCases = {
            new TestCase("abc", "aebdc", true, "基本正例 - 存在子序列"),
            new TestCase("axc", "ahbgdc", false, "基本反例 - 不存在子序列"),
            new TestCase("", "abc", true, "边界条件 - 空字符串s"),
            new TestCase("abc", "", false, "边界条件 - 空字符串t"),
            new TestCase("abc", "abc", true, "特殊情况 - 相同字符串"),
            new TestCase("a", "a", true, "特殊情况 - 单字符匹配"),
            new TestCase("a", "b", false, "特殊情况 - 单字符不匹配"),
            new TestCase("ace", "aec", false, "顺序错误 - 字符存在但顺序不对")
        };
        
        int passedCount = 0;
        int totalCount = testCases.length;
        
        System.out.println("=== LeetCode 392: 判断子序列 - TDD测试 ===");
        System.out.println();
        
        for (TestCase testCase : testCases) {
            if (runTestCase(testCase, solution)) {
                passedCount++;
            }
        }
        
        System.out.println("=== 测试总结 ===");
        System.out.printf("通过: %d/%d\n", passedCount, totalCount);
        System.out.printf("成功率: %.1f%%\n", (double) passedCount / totalCount * 100);
        
        if (passedCount == totalCount) {
            System.out.println("🎉 所有测试通过！解决方案正确。");
        } else {
            System.out.println("❌ 存在测试失败，请检查实现。");
        }
        
        // 时间复杂度分析
        System.out.println();
        System.out.println("=== 算法复杂度分析 ===");
        System.out.println("时间复杂度: O(n) - n为字符串t的长度");
        System.out.println("证明步骤:");
        System.out.println("1. 外层while循环最多执行t.length()次");
        System.out.println("2. 每次循环内部操作都是O(1)");
        System.out.println("3. 因此总时间复杂度为O(t.length()) = O(n)");
        System.out.println();
        System.out.println("空间复杂度: O(1) - 只使用常数额外空间");
        System.out.println("证明: 只使用了两个整型指针变量，与输入规模无关");
    }
}
