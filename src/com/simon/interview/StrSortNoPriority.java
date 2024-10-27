package com.simon.interview;

/**
 按照指定字母顺序对字符
 串进行排序 输入:
 待排序的字符串 ["abc","aca","la","acb","ra"]
 输出:["abc","acb","aca","ra","la"]
 */
public class StrSortNoPriority {
    public static void main(String[] args) {
        String[] input = {"abc", "aca", "la", "acb", "ra"};
        String[] sorted = bubbleSort(input);
        for (String str : sorted) {
            System.out.print(str + " ");
        }//abc aca acb la ra
    }

    public static String[] bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    // 交换元素
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
