package com.simon.tdd;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串问题解决方案
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * 使用滑动窗口算法配合哈希表实现，时间复杂度为O(n)，空间复杂度为O(min(m,n))，
 * 其中n是字符串的长度，m是字符集的大小。
 */
public class LongestSubstringWithoutRepeatingCharactersTDD {

    /**
     * 计算字符串中不含有重复字符的最长子串的长度
     * <p>
     * 使用滑动窗口算法：
     * 1. 维护一个窗口[left, right]，窗口内的字符都不重复
     * 2. 使用哈希表记录每个字符最近出现的位置
     * 3. 右边界不断扩展窗口
     * 4. 当遇到重复字符时，调整左边界以确保窗口内无重复字符
     * 5. 记录过程中的最大窗口长度
     *
     * @param s 输入字符串
     * @return 无重复字符的最长子串的长度
     */
    public int lengthOfLongestSubstring(String s) {
        // 处理边界情况
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 用于存储字符及其最近出现位置的映射
        Map<Character, Integer> charIndexMap = new HashMap<>();
        
        // 滑动窗口的左边界
        int left = 0;
        
        // 记录最长子串的长度
        int maxLength = 0;

        // 滑动窗口的右边界不断向右扩展
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 如果当前字符已经在窗口中出现过
            if (charIndexMap.containsKey(currentChar)) {
                // 更新左边界，确保窗口内无重复字符
                // 注意：left只能向右移动，不能向左移动
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }

            // 更新当前字符的位置
            charIndexMap.put(currentChar, right);

            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * 使用main方法进行测试，不引入JUnit框架
     * 测试用例包括各种边界情况和典型情况
     */
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharactersTDD solution = new LongestSubstringWithoutRepeatingCharactersTDD();

        // 测试用例1: 空字符串
        int result1 = solution.lengthOfLongestSubstring("");
        System.out.println("测试用例1 - 空字符串: 结果=" + result1 + " (期望=0) " + (result1 == 0 ? "PASS" : "FAIL"));

        // 测试用例2: 单个字符
        int result2 = solution.lengthOfLongestSubstring("a");
        System.out.println("测试用例2 - 单个字符: 结果=" + result2 + " (期望=1) " + (result2 == 1 ? "PASS" : "FAIL"));

        // 测试用例3: 所有字符相同
        int result3 = solution.lengthOfLongestSubstring("bbbb");
        System.out.println("测试用例3 - 所有字符相同: 结果=" + result3 + " (期望=1) " + (result3 == 1 ? "PASS" : "FAIL"));

        // 测试用例4: 无重复字符
        int result4 = solution.lengthOfLongestSubstring("abcde");
        System.out.println("测试用例4 - 无重复字符: 结果=" + result4 + " (期望=5) " + (result4 == 5 ? "PASS" : "FAIL"));

        // 测试用例5: 题目示例 "abcabcbb"
        int result5 = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println("测试用例5 - abcabcbb: 结果=" + result5 + " (期望=3) " + (result5 == 3 ? "PASS" : "FAIL"));

        // 测试用例6: 题目示例 "pwwkew"
        int result6 = solution.lengthOfLongestSubstring("pwwkew");
        System.out.println("测试用例6 - pwwkew: 结果=" + result6 + " (期望=3) " + (result6 == 3 ? "PASS" : "FAIL"));

        // 测试用例7: 重复字符不相邻 "abcdefgahijk"
        // 字符串分析:
        // a b c d e f g a h i j k
        // 0 1 2 3 4 5 6 7 8 9 10 11
        // 最长不重复子串为"bcdefgahijk"，长度为11
        int result7 = solution.lengthOfLongestSubstring("abcdefgahijk");
        System.out.println("测试用例7 - 重复字符不相邻: 结果=" + result7 + " (期望=11) " + (result7 == 11 ? "PASS" : "FAIL"));
    }
}