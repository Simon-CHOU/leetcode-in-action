
package com.simon.hot100;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 * 139. 单词拆分 - Word Break<p>
 * 动态规划解法：判断字符串s能否被拆分为字典中的单词<p>
 * 状态转移方程：dp[i] = OR_{j < i}(dp[j] && wordDict.contains(s.substring(j, i)))
 */
public class WordBreakTDD {

    /**
     * 判断字符串是否可以被拆分为字典中的单词
     * @param s 待拆分的字符串
     * @param wordDict 单词字典
     * @return 如果可以拆分返回true，否则返回false
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) {
            return false;
        }
        
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // 空字符串基础情况
        
        // 遍历字符串的每个位置
        for (int i = 1; i <= n; i++) {
            // 检查所有可能的分割点j
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 找到一种有效拆分即可
                }
            }
        }
        
        return dp[n];
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testCase6();
        testCase7();
    }

    // 测试用例1：基本可拆分情况
    private static void testCase1() {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        boolean expected = true;
        boolean result = wordBreak(s, wordDict);
        System.out.println("TEST CASE 1: " + (result == expected ? "PASS" : "FAIL"));
    }

    // 测试用例2：基本不可拆分情况
    private static void testCase2() {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean expected = false;
        boolean result = wordBreak(s, wordDict);
        System.out.println("TEST CASE 2: " + (result == expected ? "PASS" : "FAIL"));
    }

    // 测试用例3：多个单词组合
    private static void testCase3() {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");
        boolean expected = true;
        boolean result = wordBreak(s, wordDict);
        System.out.println("TEST CASE 3: " + (result == expected ? "PASS" : "FAIL"));
    }

    // 测试用例4：空字符串
    private static void testCase4() {
        String s = "";
        List<String> wordDict = Arrays.asList("a", "b");
        boolean expected = true;
        boolean result = wordBreak(s, wordDict);
        System.out.println("TEST CASE 4: " + (result == expected ? "PASS" : "FAIL"));
    }

    // 测试用例5：字典为空
    private static void testCase5() {
        String s = "abc";
        List<String> wordDict = List.of();
        boolean expected = false;
        boolean result = wordBreak(s, wordDict);
        System.out.println("TEST CASE 5: " + (result == expected ? "PASS" : "FAIL"));
    }

    // 测试用例6：边界情况 - 单个字符
    private static void testCase6() {
        String s = "a";
        List<String> wordDict = List.of("a");
        boolean expected = true;
        boolean result = wordBreak(s, wordDict);
        System.out.println("TEST CASE 6: " + (result == expected ? "PASS" : "FAIL"));
    }

    // 测试用例7：边界情况 - 字典中有长单词
    private static void testCase7() {
        String s = "abcd";
        List<String> wordDict = Arrays.asList("a", "ab", "abc", "abcd");
        boolean expected = true;
        boolean result = wordBreak(s, wordDict);
        System.out.println("TEST CASE 7: " + (result == expected ? "PASS" : "FAIL"));
    }
}