package com.simon.interview.wdkj;

/**
 * 反转字符串实现类
 * <p>
 * 该类实现了将字符数组原地反转的功能，使用双指针技术实现O(1)空间复杂度的算法
 */
public class WDKJ028 { // 344反转字符串 https://leetcode.cn/problems/reverse-string/

    /**
     * 原地反转字符数组
     * <p>
     * 使用双指针技术，从数组两端向中间移动并交换元素，实现原地反转
     * 时间复杂度: O(n) - 需要遍历数组的一半元素
     * 空间复杂度: O(1) - 只使用常数级额外空间
     *
     * @param s 待反转的字符数组
     */
    public void reverseString(char[] s) {
        // 边界检查：空数组或单元素数组无需反转
        if (s == null || s.length <= 1) {
            return;
        }

        int left = 0;           // 左指针，从数组开始位置
        int right = s.length - 1;  // 右指针，从数组结束位置

        // 当左指针小于右指针时继续循环
        while (left < right) {
            // 交换左右指针指向的元素
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // 移动指针向中间靠拢
            left++;
            right--;
        }
    }

    /**
     * 主方法，用于测试reverseString方法的正确性
     * <p>
     * 包含多个测试用例，验证算法在各种情况下的正确性
     */
    public static void main(String[] args) {
        WDKJ028 solution = new WDKJ028();

        // 测试用例1: 普通字符串
        char[] test1 = {'h', 'e', 'l', 'l', 'o'};
        solution.reverseString(test1);
        boolean result1 = java.util.Arrays.equals(test1, new char[]{'o', 'l', 'l', 'e', 'h'});
        System.out.println("测试用例1 - 普通字符串: " + java.util.Arrays.toString(test1));
        System.out.println("结果: " + result1 + " (期望: true) " + (result1 ? "PASS" : "FAIL"));

        // 测试用例2: 偶数长度字符串
        char[] test2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        solution.reverseString(test2);
        boolean result2 = java.util.Arrays.equals(test2, new char[]{'h', 'a', 'n', 'n', 'a', 'H'});
        System.out.println("\n测试用例2 - 偶数长度字符串: " + java.util.Arrays.toString(test2));
        System.out.println("结果: " + result2 + " (期望: true) " + (result2 ? "PASS" : "FAIL"));

        // 测试用例3: 单个字符
        char[] test3 = {'A'};
        solution.reverseString(test3);
        boolean result3 = java.util.Arrays.equals(test3, new char[]{'A'});
        System.out.println("\n测试用例3 - 单个字符: " + java.util.Arrays.toString(test3));
        System.out.println("结果: " + result3 + " (期望: true) " + (result3 ? "PASS" : "FAIL"));

        // 测试用例4: 空数组
        char[] test4 = {};
        solution.reverseString(test4);
        boolean result4 = java.util.Arrays.equals(test4, new char[]{});
        System.out.println("\n测试用例4 - 空数组: " + java.util.Arrays.toString(test4));
        System.out.println("结果: " + result4 + " (期望: true) " + (result4 ? "PASS" : "FAIL"));

        // 测试用例5: 两个字符
        char[] test5 = {'A', 'B'};
        solution.reverseString(test5);
        boolean result5 = java.util.Arrays.equals(test5, new char[]{'B', 'A'});
        System.out.println("\n测试用例5 - 两个字符: " + java.util.Arrays.toString(test5));
        System.out.println("结果: " + result5 + " (期望: true) " + (result5 ? "PASS" : "FAIL"));
    }
}