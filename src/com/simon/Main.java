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
    private List<Integer> memo = new ArrayList<>(Arrays.asList(0, 1, 1));

    public int fib(int n) {
        if (n == 0) return 0;
        return helper(n);
    }

    private int helper(int n) {
        if (n <= memo.size() - 1) return memo.get(n);
        memo.add(n, helper(n - 1) + helper(n - 2));
        return memo.get(n);
    }
}