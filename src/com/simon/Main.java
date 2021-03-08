package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
//            TreeNode s = InputTreeUtil.inputBtree();
            int[] c = InputUtil.inputIntArray();
            int s = InputUtil.inputInt();
            System.out.println(solution.coinChange(c, s));
        }
    }
}

class Solution {
    // HashMap<Integer, Integer> memo = new HashMap<>();//HashMap没有int数组块
    int[] memo;// 带备忘录的递归

    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];//第一位不用
        return dp(amount, coins);
    }

    int dp(int n, final int[] coins) {
        if (n < 0) return -1;
        if (n == 0) return 0;
        if (memo[n] != 0) {
            return memo[n];
        }
        int res = Integer.MAX_VALUE;// res 不要带着一起递归
        for (int coin : coins) {
            int subProblem = dp(n - coin, coins);
            if (subProblem == -1) {//子问题无解，则跳过
                continue;
            }
            res = Math.min(res, 1 + subProblem);
        }
        memo[n] = res != Integer.MAX_VALUE ? res : -1;
        return memo[n];
    }
}
/**
 * [1,2,5]
 * 11
 * exp:3
 * <p>
 * [186,419,83,408]
 * 6249
 * exp:20
 */