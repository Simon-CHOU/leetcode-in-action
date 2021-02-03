package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String str = InputUtil.inputStr();
            int k = InputUtil.inputInt();
            System.out.println(solution.characterReplacement(str, k));
        }
    }


}


class Solution {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        if (len < 2) {
            return len;
        }
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = 0;
        int res = 0;
        int maxCount = 0;
        int[] freq = new int[26];
        while (right < len) {
            freq[charArray[right] - 'A']++;
            maxCount = Math.max(maxCount, freq[charArray[right] - 'A']);
            right++;

            if (right - left > maxCount + k) {
                freq[charArray[left] - 'A']--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
/*
输入：s = "ABAB", k = 2
输出：4
输入：s = "AABABBA", k = 1
输出：4
 */