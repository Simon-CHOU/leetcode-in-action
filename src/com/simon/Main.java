package com.simon;

import com.simon.util.*;

import javax.swing.text.EditorKit;
import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String s = InputUtil.inputStr();
            System.out.println(solution.firstUniqChar(s));
        }
    }
}

class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            Integer count = map.get(s.charAt(i));
            if (count == 1) {
                return i;
            }
        }
        return -1;
    }
}