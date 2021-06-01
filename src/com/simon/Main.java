package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String s = InputUtil.inputStr();
            System.out.println(solution.isPalindrome(s));
        }
    }
}

class Solution {
    //hash(unicode) 慢，兼容性好
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {// 防用例： ab, a
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char key = t.charAt(i);
            Integer currentCount = map.getOrDefault(key, 0);
            if (currentCount == 0) {
                return false;
            }
            map.put(key, currentCount - 1);
        }
        return true;
    }

// 循环过滤
//    public boolean isPalindrome(String s) {
//        StringBuffer sgood = new StringBuffer();
//        //  先过滤
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (Character.isLetterOrDigit(c)) {
//                sgood.append(Character.toLowerCase(c));//比正则更快
//            }
//        }
//        int left = 0, right = sgood.length() - 1;
//        while (left<right){
//            if(sgood.charAt(left++) != sgood.charAt(right--)){
//                return false;
//            }
//        }
//        return true;
//    }

//    // reverse ，也慢
//    public boolean isPalindrome(String s) {
//        StringBuffer sgood = new StringBuffer();
//        //  先过滤
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (Character.isLetterOrDigit(c)) {
//                sgood.append(c);
//            }
//        }
//        //回文串与其倒序相同
//        StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
//        return sgood_rev.toString().toLowerCase().equals(sgood.toString().toLowerCase());//忽略字母的大小写
//    }

// 自解，太慢
//    public boolean isPalindrome(String s) {
//        //本题中，我们将空字符串定义为有效的回文串。空格不是，故不用[^a-zA-Z0-9 ]
//        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();//忽略字母的大小写。
//        int left = 0, right = s.length() - 1;
//        while (left<right){
//            if(s.charAt(left++) != s.charAt(right--)){
//                return false;
//            }
//        }
//        return true;
//    }
}