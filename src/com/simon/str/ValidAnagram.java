package com.simon.str;

import java.util.Arrays;

class Solution242 {
//    public boolean isAnagram(String s, String t) { // 排序后相等
//        if (s.length() != t.length()) {
//            return false;
//        }
//        char[] str1 = s.toCharArray();
//        char[] str2 = t.toCharArray();
//        Arrays.sort(str1);
//        Arrays.sort(str2);
//        return Arrays.equals(str1, str2);
//    }

    public boolean isAnagram(String s, String t) { // 排序后相等
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}

public class ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        Solution242 solution242 = new Solution242();
        System.out.println(solution242.isAnagram(s, t));
    }
}
