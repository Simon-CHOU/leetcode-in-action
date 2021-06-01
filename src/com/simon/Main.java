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
    public boolean isPalindrome(String s) {
        int length = s.length();
        int left = 0, right = length - 1;
        while (left < right) {
            //先跳过非字母数字，边循环边跳过，没有预处理，是最快的。
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            ++left;
            --right;
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