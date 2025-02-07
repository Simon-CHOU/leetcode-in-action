package com.simon.str;


class Solution1456 { //https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
    public int maxVowels(String S, int k) {
        char[] s = S.toCharArray();
        int ans = 0;
        int vowel = 0;
//        System.out.println("Starting maxVowels method with string: " + S + " and window size: " + k);
        for (int i = 0; i < s.length; i++) {
            // 1. 进入窗口
            if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u') {
                vowel++;
            }
//            System.out.println("Index: " + i + ", Character: " + s[i] + ", Vowel Count after entering window: " + vowel);

            if (i >= k) {
                char out = s[i - k];
                if (out == 'a' || out == 'e' || out == 'i' || out == 'o' || out == 'u') {
                    vowel--;
                }
//                System.out.println("Character leaving window: " + out + ", Vowel Count after leaving window: " + vowel);
            }

            if (i >= k - 1) {
                ans = Math.max(ans, vowel);
//                System.out.println("Current window: " + new String(s, Math.max(0, i - k + 1), Math.min(k, i + 1)) + ", Vowels in window: " + vowel + ", Max vowels so far: " + ans);
            }
        }
//        System.out.println("Finished maxVowels method. Max vowels: " + ans);
        return ans;
    }
}

public class MaxVowels {
    public static void main(String[] args) {
        Solution1456 solution1456 = new Solution1456();
        int result = solution1456.maxVowels("abciiidef", 3);
        System.out.println(result);
        assert result == 3 : "Expected 3, but got " + result;
        System.out.println("Test passed!");
    }
}
