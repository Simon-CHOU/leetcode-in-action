package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String s = InputUtil.inputStr();
            System.out.println(solution.myAtoi(s));
        }
    }
}

class Solution {
    public int myAtoi(String s) {
        long ans = 0;
        s = s.trim();//去掉空格，主要是前导空格
        if(s.length()<1){
            return (int)ans;// 特殊处理，空字符串
        }
////        int k;
//        while( s.length()>0 ) {
//            if (s.charAt(0) != '+'&&
//                    s.charAt(0) != '-'&& !Character.isDigit(s.charAt(0))) {
//                s = s.substring(1);//去掉开头数字 +-  "words and 987"
////                i++;
//            } else {
//                break;
//            }
//        }
        while( s.length()>0 ) {
            if (s.charAt(0) == '0') {
                s = s.substring(1);//去掉前导0
            } else {
                break;
            }
        }
        //处理符号
        int flag = 1;
        //检查数字的实际符号, e.g. "-+12", "   -43"
//        int len = s.length();
//        while (len > 0) {
            char firstCh = s.charAt(0);
//            if(!Character.isDigit(firstCh)&&firstCh!='-'&&firstCh!='+'){
//                break;
//            }
//            if (Character.isDigit(firstCh)) {
//                break;
//            }
//            if (firstCh == '-') {
//                flag = -1;
//                s = s.substring(1);
//            }
//            if (firstCh == '+') {
//                flag = 1;
//                s = s.substring(1);
//            }
//            len = s.length();
//        }
        if (firstCh == '-') {
            flag = -1;
            s = s.substring(1);
        }
        if (firstCh == '+') {
            flag = 1;
            s = s.substring(1);
        }


        //检查数字字符串末尾
        int end = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                end = i - 1;
                break;//非数字字符，丢弃字符串后续部分
            }
        }
        //转换数字
        for (int i = 0; i <= end; i++) {
            if (flag * ans >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;//校验范围。
            }
            if (flag * ans <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            ans = ans * 10 + s.charAt(i) - '0';
        }
        if(flag* ans>=Integer.MAX_VALUE){
            return  Integer.MAX_VALUE;
        }
        if(flag *ans<=Integer.MIN_VALUE){
            return  Integer.MIN_VALUE;
        }
        return (int) (flag * ans);
    }
}
///"+-12"  exp: 0
// words and 987  exp: 0
// "21474836460" exp: 2147483647
//"00000-42a1234"  exp: 0