package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String s = InputUtil.inputStr();
            String s1 = InputUtil.inputStr();
            System.out.println(solution.isAnagram(s, s1));
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

    //hash(仅ASCII字符) 次快
//    public boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) {
//            return false;
//        }
//        int[] table = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            table[s.charAt(i) - 'a']++;
//        }
//        for (int i = 0; i < t.length(); i++) {
//            table[t.charAt(i) - 'a']--;
//            if (table[t.charAt(i) - 'a'] < 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    //sort 快
//    public boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) {
//            return false;
//        }
//        char[] sChars = s.toCharArray();
//        char[] tChars = t.toCharArray();
//        Arrays.sort(sChars);
//        Arrays.sort(tChars);
//        if (Arrays.equals(sChars, tChars)) {
//            return true;
//        }
//        return false;
//    }
}