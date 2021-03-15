package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String s = InputUtil.inputStr();
            System.out.println(solution.lengthOfLongestSubstring(s));
        }
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j <= length; j++) {
                String substring = s.substring(i, j);
                if (isNoRepeat(substring)) {
                    res = Math.max(res,substring.length());
                }
            }
        }
        return res;
    }

    boolean isNoRepeat(String str) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.substring(i,i+1));
        }
        int length = str.length();
        return set.size() == length;//相等说明无重复
    }
}