package com.simon.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符<p>
 * 
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。<p>
 * 
 * 解题思路：<p>
 * 1. 使用哈希表统计每个字符出现的次数<p>
 * 2. 遍历字符串，找到第一个出现次数为1的字符，返回其索引<p>
 * 
 * 算法演示：<p>
 * 字符串 s = "leetcode"<p>
 * 索引:     0 1 2 3 4 5 6 7<p>
 * 字符:     l e e t c o d e<p>
 * <p>
 * 第一次遍历 - 统计字符频次:<p>
 * HashMap: {l=1, e=3, t=1, c=1, o=1, d=1}<p>
 * <p>
 * 第二次遍历 - 查找第一个唯一字符:<p>
 * 索引 0: 字符 'l', 出现次数 1 → 找到第一个唯一字符，返回索引 0<p>
 */
public class FirstUniqueCharacterInAStringTDD {
    //https://leetcode.cn/problems/first-unique-character-in-a-string/description/?envType=study-plan-v2&envId=selected-coding-interview

    /**
     * 查找字符串中第一个不重复的字符的索引
     * 
     * @param s 输入字符串
     * @return 第一个不重复字符的索引，如果不存在则返回-1
     */
    public int firstUniqChar(String s) {
        // 使用哈希表统计每个字符出现的次数
        Map<Character, Integer> charCount = new HashMap<>();
        
        // 第一次遍历：统计每个字符出现的次数
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // 第二次遍历：找到第一个出现次数为1的字符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charCount.get(c) == 1) {
                return i;
            }
        }
        
        // 如果没有找到唯一字符，返回-1
        return -1;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        FirstUniqueCharacterInAStringTDD solution = new FirstUniqueCharacterInAStringTDD();
        
        // 测试用例1: "leetcode" → 期望输出: 0
        int result1 = solution.firstUniqChar("leetcode");
        System.out.println("测试用例1 - 输入: \"leetcode\" 结果: " + result1 + " (期望: 0) " + 
                          (result1 == 0 ? "PASS" : "FAIL"));
        
        // 测试用例2: "loveleetcode" → 期望输出: 2
        int result2 = solution.firstUniqChar("loveleetcode");
        System.out.println("测试用例2 - 输入: \"loveleetcode\" 结果: " + result2 + " (期望: 2) " + 
                          (result2 == 2 ? "PASS" : "FAIL"));
        
        // 测试用例3: "aabb" → 期望输出: -1
        int result3 = solution.firstUniqChar("aabb");
        System.out.println("测试用例3 - 输入: \"aabb\" 结果: " + result3 + " (期望: -1) " + 
                          (result3 == -1 ? "PASS" : "FAIL"));
        
        // 测试用例4: "abcdef" → 期望输出: 0
        int result4 = solution.firstUniqChar("abcdef");
        System.out.println("测试用例4 - 输入: \"abcdef\" 结果: " + result4 + " (期望: 0) " + 
                          (result4 == 0 ? "PASS" : "FAIL"));
        
        // 测试用例5: "aabcbc" → 期望输出: -1
        int result5 = solution.firstUniqChar("aabcbc");
        System.out.println("测试用例5 - 输入: \"aabcbc\" 结果: " + result5 + " (期望: -1) " + 
                          (result5 == -1 ? "PASS" : "FAIL"));
    }
}