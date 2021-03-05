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
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int prev = 1, curr = 1;
        //根据斐波那契数列的状态转移方程，当前状态只和之前的两个状态有关，其实并不需要那么长的一个 DP table 来存储所有的状态，
        // 只要想办法存储之前的两个状态就行了。所以，可以进一步优化，把空间复杂度降为 O(1)
//        int[] dp = new int[n + 1];
//        dp[1] = 1;
//        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
//        return dp[n];
        return curr;
    }

}