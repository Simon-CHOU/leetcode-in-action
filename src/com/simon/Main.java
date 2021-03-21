package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
//            String s = InputUtil.inputStr();
//            System.out.println(solution.lengthOfLongestSubstring(s));
//            System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
            System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        }
    }
}

class Solution {


//    public int lengthOfLongestSubstring(String s) {
//        int n = s.length(), ans = 0;
//        Map<Character, Integer> map = new HashMap<>();
//        for (int end = 0, start = 0; end < n; end++) {
//            char alpha = s.charAt(end);
//            if (map.containsKey(alpha)) {
//                start = Math.max(map.get(alpha), start);
//            } ans = Math.max(ans, end - start + 1); map.put(s.charAt(end), end + 1); } return ans; }


    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();//key=字符，value=字符起始位置
        int maxLen = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);//当前字符
            if (map.containsKey(curChar)) {//遇到重复字符
                 left = Math.max(left, map.get(curChar) + 1);//左边界【右移一位】或【到重复字符出现的右边一位】
            }
            map.put(curChar, i);
            int len = i - left + 1;//当前子串的长度
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }

//public int lengthOfLongestSubstring(String s) {
//    // 哈希集合，记录每个字符是否出现过
//    Set<Character> occ = new HashSet<Character>();
//    int n = s.length();
//    // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
//    int rk = -1, ans = 0;
//    for (int i = 0; i < n; ++i) {
//        if (i != 0) {
//            // 左指针向右移动一格，移除一个字符
//            occ.remove(s.charAt(i - 1));
//        }
//        while (rk + 1 < n &&
//                !occ.contains(s.charAt(rk + 1))) {
//            // 不断地移动右指针
//            occ.add(s.charAt(rk + 1));
//            ++rk;
//        }
//        // 第 i 到 rk 个字符是一个极长的无重复字符子串
//        ans = Math.max(ans, rk - i + 1);
//    }
//    return ans;
//}
}