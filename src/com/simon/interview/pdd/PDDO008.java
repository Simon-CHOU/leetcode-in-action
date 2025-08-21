package com.simon.interview.pdd;

import java.util.Arrays;

/**
 * 按照指定字母顺序对字符串进行排序
 * <p>
 * 问题描述:
 * 输入:
 * 待排序的字符串 ["abc","aca","la","acb","ra"]
 * 字符的优先级 ["b","a","c","r","l"] ，左边的字符优先级更高
 * 输出:["abc","acb","aca","ra","la"]
 * <p>
 * 解题思路:
 * 1. 创建一个映射表，记录每个字符的优先级（索引越小优先级越高）
 * 2. 实现自定义比较器，比较两个字符串的大小
 * 3. 使用排序算法对字符串数组进行排序
 */
public class PDDO008 {
    
    /**
     * 根据指定的字母顺序对字符串数组进行排序
     * 
     * @param words 待排序的字符串数组
     * @param order 字符优先级顺序
     * @return 排序后的字符串数组
     */
    public static String[] sortStringsByOrder(String[] words, String[] order) {
        // 创建字符优先级映射表
        // 字符 -> 优先级索引（索引越小优先级越高）
        int[] charOrder = new int[26]; // 用于存储a-z的优先级
        for (int i = 0; i < order.length; i++) {
            charOrder[order[i].charAt(0) - 'a'] = i;
        }
        
        // 使用自定义比较逻辑进行排序
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words.length - 1 - i; j++) {
                if (isGreater(words[j], words[j + 1], charOrder)) {
                    // 交换元素
                    String temp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = temp;
                }
            }
        }
        
        return words;
    }
    
    /**
     * 比较两个字符串的大小
     * <p>
     * 算法执行过程示例:
     * 假设order=["b","a","c","r","l"]
     * 则字符优先级为: b(0) < a(1) < c(2) < r(3) < l(4)
     * <p>
     * 比较"abc"和"acb":
     * 第1个字符: a vs a -> 相等，继续比较
     * 第2个字符: b vs c -> b(0) < c(2)，所以"abc" < "acb"，返回false
     * <p>
     * 比较"ra"和"la":
     * 第1个字符: r vs l -> r(3) > l(4)，所以"ra" > "la"，返回true
     * 
     * @param s1 字符串1
     * @param s2 字符串2
     * @param charOrder 字符优先级映射表
     * @return 如果s1 > s2返回true，否则返回false
     */
    private static boolean isGreater(String s1, String s2, int[] charOrder) {
        int len1 = s1.length();
        int len2 = s2.length();
        
        // 逐字符比较
        for (int i = 0; i < Math.min(len1, len2); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            
            // 获取字符的优先级
            int order1 = charOrder[c1 - 'a'];
            int order2 = charOrder[c2 - 'a'];
            
            // 如果优先级不同，则可以判断结果
            if (order1 != order2) {
                return order1 > order2;
            }
            // 如果优先级相同，继续比较下一个字符
        }
        
        // 如果前面的字符都相同，则长度较长的字符串更大
        return len1 > len2;
    }
    
    public static void main(String[] args) {
        // 测试用例1
        String[] words1 = {"abc", "aca", "la", "acb", "ra"};
        String[] order1 = {"b", "a", "c", "r", "l"};
        String[] expected1 = {"abc", "acb", "aca", "ra", "la"};
        System.out.println("输入字符串: " + Arrays.toString(words1));
        System.out.println("字符优先级: " + Arrays.toString(order1));
        
        // 创建副本用于测试，避免修改原数组
        String[] testWords1 = Arrays.copyOf(words1, words1.length);
        String[] result1 = sortStringsByOrder(testWords1, order1);
        System.out.println("排序结果: " + Arrays.toString(result1));
        System.out.println("期望结果: " + Arrays.toString(expected1));
        System.out.println("测试结果: " + (Arrays.equals(result1, expected1) ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例2 - 验证长度比较逻辑
        String[] words2 = {"ab", "abc"};
        String[] order2 = {"a", "b", "c"};
        String[] expected2 = {"ab", "abc"};
        System.out.println("输入字符串: " + Arrays.toString(words2));
        System.out.println("字符优先级: " + Arrays.toString(order2));
        
        String[] testWords2 = Arrays.copyOf(words2, words2.length);
        String[] result2 = sortStringsByOrder(testWords2, order2);
        System.out.println("排序结果: " + Arrays.toString(result2));
        System.out.println("期望结果: " + Arrays.toString(expected2));
        System.out.println("测试结果: " + (Arrays.equals(result2, expected2) ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例3 - 相同字符串
        String[] words3 = {"a", "aa", "aaa"};
        String[] order3 = {"a"};
        String[] expected3 = {"a", "aa", "aaa"};
        System.out.println("输入字符串: " + Arrays.toString(words3));
        System.out.println("字符优先级: " + Arrays.toString(order3));
        
        String[] testWords3 = Arrays.copyOf(words3, words3.length);
        String[] result3 = sortStringsByOrder(testWords3, order3);
        System.out.println("排序结果: " + Arrays.toString(result3));
        System.out.println("期望结果: " + Arrays.toString(expected3));
        System.out.println("测试结果: " + (Arrays.equals(result3, expected3) ? "PASS" : "FAIL"));
    }
}