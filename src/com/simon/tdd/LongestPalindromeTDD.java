
package com.simon.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 409: 最长回文串 (Longest Palindrome)
 * <p>
 * 问题描述：给定一个包含大小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 核心思路：使用贪心算法 + 字符频次统计
 * <p>
 * 算法原理：
 * 1. 统计每个字符的出现频次
 * 2. 对于每个字符，如果出现偶数次，全部可以用于构造回文串
 * 3. 对于每个字符，如果出现奇数次，可以用 (count-1) 个字符构造回文串
 * 4. 如果存在奇数次的字符，可以选择一个放在回文串中心
 * <p>
 * 数据结构：HashMap用于字符频次统计
 * 算法类型：贪心算法
 * <p>
 * 时间复杂度：O(n) - 遍历字符串一次 + 遍历HashMap一次
 * 空间复杂度：O(1) - HashMap最多存储52个字符(26个大写+26个小写)
 *
 * @author Simon
 * @version 1.0
 */
public class LongestPalindromeTDD {
    
    /**
     * 计算可以构造的最长回文串长度
     * <p>
     * 算法步骤：
     * 1. 使用HashMap统计每个字符的出现频次
     * 2. 遍历频次统计，累加所有偶数频次
     * 3. 对于奇数频次，累加(频次-1)，并标记存在奇数频次字符
     * 4. 如果存在奇数频次字符，结果+1(作为回文串中心)
     *
     * @param s 输入字符串，包含大小写字母
     * @return 可以构造的最长回文串长度
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // Step 1: 统计字符频次
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Step 2: 贪心策略计算最长回文串长度
        int palindromeLength = 0;
        boolean hasOddCount = false;
        
        for (int count : charCount.values()) {
            if (count % 2 == 0) {
                // 偶数频次：全部字符都可以用于构造回文串
                palindromeLength += count;
            } else {
                // 奇数频次：使用(count-1)个字符，剩余1个字符可能作为中心
                palindromeLength += count - 1;
                hasOddCount = true;
            }
        }
        
        // Step 3: 如果存在奇数频次字符，可以选择一个作为回文串中心
        if (hasOddCount) {
            palindromeLength += 1;
        }
        
        return palindromeLength;
    }
    
    /**
     * TDD测试方法 - 使用main方法创建测试用例
     * <p>
     * 测试用例覆盖：
     * 1. 基本功能测试
     * 2. 边界条件测试
     * 3. 特殊情况测试
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        LongestPalindromeTDD solution = new LongestPalindromeTDD();
        
        System.out.println("=== LeetCode 409: 最长回文串 TDD测试 ===");
        System.out.println();
        
        // 测试用例1: 基本功能测试
        testCase(solution, "abccccdd", 7, "基本功能测试 - 混合大小写字母");
        
        // 测试用例2: 单个字符
        testCase(solution, "a", 1, "边界测试 - 单个字符");
        
        // 测试用例3: 空字符串
        testCase(solution, "", 0, "边界测试 - 空字符串");
        
        // 测试用例4: 所有字符都是偶数频次
        testCase(solution, "aabbcc", 6, "特殊情况 - 所有字符偶数频次");
        
        // 测试用例5: 所有字符都是奇数频次
        testCase(solution, "abcde", 1, "特殊情况 - 所有字符奇数频次");
        
        // 测试用例6: 大小写混合
        testCase(solution, "AaBbCc", 1, "功能测试 - 大小写敏感");
        
        // 测试用例7: 复杂情况
        testCase(solution, "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendure", 73, "复杂测试 - 长字符串");
        
        System.out.println();
        System.out.println("=== 算法执行过程ASCII图解 ===");
        demonstrateAlgorithm(solution, "abccccdd");
        
        System.out.println();
        System.out.println("=== 时间复杂度分析 ===");
        analyzeTimeComplexity();
    }
    
    /**
     * 执行单个测试用例并输出结果
     *
     * @param solution 解决方案实例
     * @param input 输入字符串
     * @param expected 期望结果
     * @param description 测试描述
     */
    private static void testCase(LongestPalindromeTDD solution, String input, int expected, String description) {
        int actual = solution.longestPalindrome(input);
        boolean passed = actual == expected;
        
        System.out.printf("测试: %s%n", description);
        System.out.printf("输入: \"%s\"%n", input);
        System.out.printf("期望: %d, 实际: %d%n", expected, actual);
        System.out.printf("结果: %s%n", passed ? "PASS ✓" : "FAIL ✗");
        System.out.println();
    }
    
    /**
     * 使用ASCII字符画演示算法执行过程
     *
     * @param solution 解决方案实例
     * @param example 示例字符串
     */
    private static void demonstrateAlgorithm(LongestPalindromeTDD solution, String example) {
        System.out.printf("示例输入: \"%s\"%n", example);
        System.out.println();
        
        // Step 1: 字符频次统计过程
        System.out.println("Step 1: 字符频次统计");
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│  字符串遍历过程:                    │");
        System.out.println("└─────────────────────────────────────┘");
        
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < example.length(); i++) {
            char c = example.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            
            System.out.printf("  处理字符 '%c' -> ", c);
            System.out.print("{ ");
            for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
                System.out.printf("%c:%d ", entry.getKey(), entry.getValue());
            }
            System.out.println("}");
        }
        
        System.out.println();
        
        // Step 2: 贪心算法计算过程
        System.out.println("Step 2: 贪心算法计算最长回文串长度");
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│  频次分析:                          │");
        System.out.println("└─────────────────────────────────────┘");
        
        int palindromeLength = 0;
        boolean hasOddCount = false;
        
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            
            if (count % 2 == 0) {
                palindromeLength += count;
                System.out.printf("  字符 '%c': 频次=%d (偶数) -> 全部使用, 累计长度=%d%n", c, count, palindromeLength);
            } else {
                palindromeLength += count - 1;
                hasOddCount = true;
                System.out.printf("  字符 '%c': 频次=%d (奇数) -> 使用%d个, 累计长度=%d%n", c, count, count-1, palindromeLength);
            }
        }
        
        if (hasOddCount) {
            palindromeLength += 1;
            System.out.printf("  存在奇数频次字符 -> 选择1个作为中心, 最终长度=%d%n", palindromeLength);
        }
        
        System.out.println();
        
        // Step 3: 回文串构造示意
        System.out.println("Step 3: 回文串构造示意");
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│  可能的回文串结构:                  │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("  左半部分 + [中心字符] + 右半部分");
        System.out.println("  例如: dccaccd (长度=7)");
        System.out.println("        ↑     ↑");
        System.out.println("      左半部   右半部(左半部的镜像)");
        System.out.println();
        
        System.out.printf("最终结果: %d%n", palindromeLength);
    }
    
    /**
     * 时间复杂度分析方法
     * <p>
     * 提供详细的时间复杂度和空间复杂度分析，包含数学证明过程
     */
    private static void analyzeTimeComplexity() {
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│                    时间复杂度分析                           │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        System.out.println();
        
        System.out.println("【算法步骤分解】");
        System.out.println("Step 1: 字符频次统计 - 遍历输入字符串");
        System.out.println("  • 操作: for (char c : s.toCharArray())");
        System.out.println("  • 时间复杂度: O(n) - 其中n为字符串长度");
        System.out.println("  • 证明: 需要访问字符串中的每个字符一次");
        System.out.println();
        
        System.out.println("Step 2: HashMap操作分析");
        System.out.println("  • 操作: charCount.put() 和 charCount.getOrDefault()");
        System.out.println("  • 单次操作时间复杂度: O(1) - HashMap平均情况");
        System.out.println("  • 总操作次数: n次 (每个字符一次)");
        System.out.println("  • 总时间复杂度: O(n) × O(1) = O(n)");
        System.out.println();
        
        System.out.println("Step 3: 频次统计遍历");
        System.out.println("  • 操作: for (int count : charCount.values())");
        System.out.println("  • 时间复杂度: O(k) - 其中k为不同字符的数量");
        System.out.println("  • 约束条件: k ≤ min(n, 52) (最多52个不同字符)");
        System.out.println("  • 实际复杂度: O(1) - 因为k是常数(≤52)");
        System.out.println();
        
        System.out.println("【总时间复杂度推导】");
        System.out.println("T(n) = T_统计(n) + T_HashMap(n) + T_遍历(k)");
        System.out.println("     = O(n) + O(n) + O(k)");
        System.out.println("     = O(n) + O(n) + O(1)  [因为k≤52为常数]");
        System.out.println("     = O(n)");
        System.out.println();
        
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│                    空间复杂度分析                           │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        System.out.println();
        
        System.out.println("【空间使用分析】");
        System.out.println("1. HashMap存储空间:");
        System.out.println("   • 最多存储52个键值对 (26个小写字母 + 26个大写字母)");
        System.out.println("   • 每个键值对: Character(1字节) + Integer(4字节) ≈ 5字节");
        System.out.println("   • 总空间: 52 × 5字节 = 260字节 = O(1)");
        System.out.println();
        
        System.out.println("2. 其他变量:");
        System.out.println("   • palindromeLength: int (4字节)");
        System.out.println("   • hasOddCount: boolean (1字节)");
        System.out.println("   • 循环变量: O(1)");
        System.out.println();
        
        System.out.println("【总空间复杂度】");
        System.out.println("S(n) = O(1) - 空间使用与输入规模无关");
        System.out.println();
        
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│                      算法优势分析                           │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");
        System.out.println();
        
        System.out.println("✓ 时间效率: O(n) - 线性时间，最优解");
        System.out.println("✓ 空间效率: O(1) - 常数空间，空间最优");
        System.out.println("✓ 算法稳定性: 不依赖输入数据分布");
        System.out.println("✓ 实现简洁: 贪心策略，逻辑清晰");
        System.out.println();
        
        System.out.println("【与其他解法对比】");
        System.out.println("• 暴力解法: O(n!) - 生成所有排列 (不可行)");
        System.out.println("• 动态规划: O(n²) - 过度设计 (不必要)");
        System.out.println("• 贪心算法: O(n) - 当前解法 (最优)");
    }
}
