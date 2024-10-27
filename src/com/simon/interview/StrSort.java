package com.simon.interview;

import java.util.Arrays;

/**
 按照指定字母顺序对字符
 串进行排序 输入:
 待排序的字符串 ["abc","aca","la","acb","ra"]
 字符的优先级 ["b","a","c","r","l"]  ，左边的的字符优先级更高
 输出:["abc","acb","aca","ra","la"]
 要求，1，不允许使用java容器排序工具方法和比较器
 */
public class StrSort {
    public static void main(String[] args) {
        String[] words = {"abc", "aca", "la", "acb", "ra"};
        String priority = "bacrl"; // 修改优先级顺序

        // 手动实现冒泡排序
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words.length - 1 - i; j++) {
                if (isGreater(words[j], words[j + 1], priority)) {
                    // 交换位置
                    String temp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = temp;
                }
            }
        }

        // 输出结果
        System.out.println(Arrays.toString(words));
    }

    // 比较两个字符串，根据优先级判断 s1 是否大于 s2
    private static boolean isGreater(String s1, String s2, String priority) {
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            int index1 = priority.indexOf(s1.charAt(i));
            int index2 = priority.indexOf(s2.charAt(i));
            if (index1!= index2) {
                return index1 > index2;
            }
        }
        return s1.length() > s2.length();
    }
}
