package com.simon;

import com.simon.util.InputUtil;
import com.simon.util.LinkedList;
import com.simon.util.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String str = InputUtil.inputStr();
            System.out.println(solution.lengthOfLastWord(str));
        }
    }


}


class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' ') {
                break;
            } else {
                count++;
            }
        }
        return count;
    }
}
