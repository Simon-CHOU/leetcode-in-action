package com.simon.interview;
import java.util.*;

/**
 按照指定字母顺序对字符
 串进行排序 输入:
 待排序的字符串 ["abc","aca","la","acb","ra"]
 字符的优先级 ["b","a","c","r","l"]  ，左边的的字符优先级更高
 输出:["abc","acb","aca","ra","la"]
 */
public class StrSortUseUtil {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("abc", "aca", "la", "acb", "ra");
        String priority = "bacrl";

        // 自定义比较器
        Comparator<String> customComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
                    int index1 = priority.indexOf(s1.charAt(i));
                    int index2 = priority.indexOf(s2.charAt(i));
                    if (index1 != index2) {
                        return index1 - index2;
                    }
                }
                return s1.length() - s2.length();
            }
        };

        // 使用自定义比较器进行排序
        Collections.sort(words, customComparator);

        // 输出结果
        System.out.println(words); //[abc, acb, aca, ra, la]
    }
}
