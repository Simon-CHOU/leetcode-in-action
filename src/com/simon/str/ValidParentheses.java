package com.simon.str;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Solution20 {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) return false;
        Map<Character, Character> pairs = new HashMap<>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i< n; i++) {
            char ch = s.charAt(i);
            if(pairs.containsKey(ch)) {
                if(stack.isEmpty() || stack.peek()!=pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}

public class ValidParentheses {


    public static void main(String[] args) {
        String s = "()[]{}";
        Solution20 solution20 = new Solution20();
        System.out.println(solution20.isValid(s));
        System.out.println(solution20.isValid("{{{]]]]"));// false
    }

}
