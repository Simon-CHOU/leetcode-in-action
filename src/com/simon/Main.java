package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            String str = InputUtil.inputStr();
            solution.isValid(str);

        }
    }
}

class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }
        List<String> stack = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.add(String.valueOf(c));
            } else if ((c == ')' && stack.size() > 0 && stack.get(stack.size() - 1).equals("(")) ||
                    (c == ']' && stack.size() > 0 && stack.get(stack.size() - 1).equals("[")) ||
                    (c == '}' && stack.size() > 0 && stack.get(stack.size() - 1).equals("{"))) {
                stack = stack.subList(0, stack.size() - 1);
                //&& stack.size()>0  is for input: "]"
            } else {
                return false;
            }
        }
        return stack.size() == 0;
    }
}