package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            char[] arr = InputUtil.inputCharArr();
            solution.reverseString(arr);
        }
    }
}

class Solution {
    public void reverseString(char[] s) {
        int length = s.length;
        for (int left = 0, right = length - 1 - left; left < right; left++, right--) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
//        for (int i = 0; i < s.length / 2; i++) {
//            char temp = s[i];
//            s[i] = s[s.length - 1 - i];
//            s[s.length - 1 - i] = temp;
//        }
//        System.out.println(s);
    }
}