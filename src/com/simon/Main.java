package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
//            TreeNode s = InputTreeUtil.inputBtree();
            int s = InputUtil.inputInt();
            System.out.println(solution.fib(s));
        }
    }
}

class Solution {
    public int fib(int n) {
        if(n==0)return 0 ;
        if (n == 1 || n == 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}