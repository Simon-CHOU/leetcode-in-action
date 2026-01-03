package com.simon.hot100;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesesTDD {
    public boolean isValid(String s) {
        //left in
        // right if match pop  else wrong
        Deque<Character> queue = new ArrayDeque<>();
        for(Character ch : s.toCharArray()) {
            if(isLeft(ch)) {
                queue.push(ch);
            } else {
                Character pop = queue.peek();
                if(pop == null) {
                    return false;
                }
                else if(isMatch(pop, ch)) {
                    queue.pop();
                } else {
                    return false;
                }
            }
        }
        if(!queue.isEmpty()) { // testcase: "["
            return false;
        }
        return true;
    }

    boolean isLeft(Character ch) {
        return ch.equals('(') || ch.equals('{') || ch.equals('[');
    }
    boolean isMatch(Character pop, Character ch ) {
        if(pop.equals('(') && ch.equals(')') ) {
            return true;
        }
        else if(pop.equals('{') && ch.equals('}')) {
            return true;
        } else if(pop.equals('[') && ch.equals(']')){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidParenthesesTDD solution = new ValidParenthesesTDD();
//        System.out.println(solution.isValid("()")); // exp true
//        System.out.println(solution.isValid("()[]{}")); // exp true
//        System.out.println(solution.isValid("()[]{}}}}")); // exp false
        System.out.println(solution.isValid("(]}")); // exp false
        System.out.println(solution.isValid("([)]")); // exp false
        System.out.println(solution.isValid("[")); // exp false
    }
}
