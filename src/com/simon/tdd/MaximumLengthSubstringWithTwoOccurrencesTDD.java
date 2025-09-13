package com.simon.tdd;

public class MaximumLengthSubstringWithTwoOccurrencesTDD {
    /**
     * 找到满足每个字符最多出现两次的最长子字符串的长度
     * <p>
     * 使用滑动窗口算法，通过左右指针维护一个有效区间，确保区间内每个字符的出现次数不超过2次。
     * <p>
     * 算法思路：
     * <p>
     * 1. 使用长度为26的数组记录小写字母出现次数（假设输入仅包含小写字母）
     * <p>
     * 2. 右指针不断扩展窗口，左指针在需要时收缩窗口
     * <p>
     * 3. 当某个字符出现次数超过2次时，移动左指针直到该字符出现次数不超过2次
     * <p>
     * 4. 每次更新最大窗口长度
     * <p>
     * @param s 输入字符串，假设仅包含小写字母
     * @return 满足条件的最长子字符串长度
     */
    public int maximumLengthSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // 使用长度26的数组记录小写字母出现次数（a=0, b=1, ..., z=25）
        int[] count = new int[26];
        int maxLength = 0;
        int left = 0;
        
        // 右指针遍历字符串
        for (int right = 0; right < s.length(); right++) {
            // 计算当前字符的索引
            int charIndex = s.charAt(right) - 'a';
            count[charIndex]++;
            
            // 当字符出现次数超过2时，移动左指针收缩窗口
            while (count[charIndex] > 2) {
                int leftCharIndex = s.charAt(left) - 'a';
                count[leftCharIndex]--;
                left++;
            }
            
            // 更新最大窗口长度
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        MaximumLengthSubstringWithTwoOccurrencesTDD solution = new MaximumLengthSubstringWithTwoOccurrencesTDD();
        
        // 测试用例1: 基本情况 - 每个字符最多出现两次
        String test1 = "aabba";
        int expected1 = 4; // "aabb" 或 "abba"
        int result1 = solution.maximumLengthSubstring(test1);
        System.out.println("测试用例1: \"" + test1 + "\" - 分析: a出现3次，所以最长子串是\"aabb\"或\"abba\"");
        System.out.println("期望结果: " + expected1 + ", 实际结果: " + result1);
        System.out.println("测试" + (result1 == expected1 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例2: 字符出现超过两次的情况
        String test2 = "aaaa";
        int expected2 = 2;
        int result2 = solution.maximumLengthSubstring(test2);
        System.out.println("测试用例2: \"" + test2 + "\"");
        System.out.println("期望结果: " + expected2 + ", 实际结果: " + result2);
        System.out.println("测试" + (result2 == expected2 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例3: 复杂情况 - 多个字符混合
        String test3 = "abcabcabc";
        int expected3 = 6; // "abcabc"
        int result3 = solution.maximumLengthSubstring(test3);
        System.out.println("测试用例3: \"" + test3 + "\"");
        System.out.println("期望结果: " + expected3 + ", 实际结果: " + result3);
        System.out.println("测试" + (result3 == expected3 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例4: 边界情况 - 空字符串
        String test4 = "";
        int expected4 = 0;
        int result4 = solution.maximumLengthSubstring(test4);
        System.out.println("测试用例4: \"" + test4 + "\" (空字符串)");
        System.out.println("期望结果: " + expected4 + ", 实际结果: " + result4);
        System.out.println("测试" + (result4 == expected4 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例5: 单个字符
        String test5 = "a";
        int expected5 = 1;
        int result5 = solution.maximumLengthSubstring(test5);
        System.out.println("测试用例5: \"" + test5 + "\"");
        System.out.println("期望结果: " + expected5 + ", 实际结果: " + result5);
        System.out.println("测试" + (result5 == expected5 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例6: 所有字符都不重复
        String test6 = "abcdef";
        int expected6 = 6;
        int result6 = solution.maximumLengthSubstring(test6);
        System.out.println("测试用例6: \"" + test6 + "\"");
        System.out.println("期望结果: " + expected6 + ", 实际结果: " + result6);
        System.out.println("测试" + (result6 == expected6 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例7: 复杂混合情况
        String test7 = "abcabcbb";
        int expected7 = 6; // "abcabc" - b出现3次，但前6个字符每个最多出现2次
        int result7 = solution.maximumLengthSubstring(test7);
        System.out.println("测试用例7: \"" + test7 + "\" - 分析: b出现3次，最长子串是\"abcabc\"");
        System.out.println("期望结果: " + expected7 + ", 实际结果: " + result7);
        System.out.println("测试" + (result7 == expected7 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 统计测试结果
        int totalTests = 7;
        int passedTests = 0;
        if (result1 == expected1) passedTests++;
        if (result2 == expected2) passedTests++;
        if (result3 == expected3) passedTests++;
        if (result4 == expected4) passedTests++;
        if (result5 == expected5) passedTests++;
        if (result6 == expected6) passedTests++;
        if (result7 == expected7) passedTests++;
        
        System.out.println("=== 测试总结 ===");
        System.out.println("总测试用例: " + totalTests);
        System.out.println("通过测试: " + passedTests);
        System.out.println("失败测试: " + (totalTests - passedTests));
        System.out.println("通过率: " + (passedTests * 100 / totalTests) + "%");
    }
}
