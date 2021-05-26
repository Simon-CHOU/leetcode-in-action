package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] a = InputUtil.inputIntArray();
            System.out.println(solution.maxProfit(a));
        }
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; i++) {
            // dp0 当天结束不持有（昨天就卖了or卖了昨天的）
            int newDp0 = Math.max(dp0, dp1 + prices[i]);//max(昨天就没有今天也不买,今天卖出)
            // dp1 当天结束持有（昨天就买了or今天刚买）
            int newDp1 = Math.max(dp1, dp0 - prices[i]);//max(昨天就持有今天不动，今天买入)
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }
    /**
     *  public int maxProfit(int[] prices) {
     *         int n = prices.length;
     *         int[][] dp = new int[n][2];
     *         dp[0][0] = 0;
     *         dp[0][1] = -prices[0];
     *         int maxProfit = 0;
     *         for (int i = 1; i < n; i++) {
     *             // [x][0] 当天结束不持有（昨天就卖了or卖了昨天的）
     *             dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);//max(昨天就没有今天也不买,今天卖出)
     *             // [x][1] 当天结束持有（昨天就买了or今天刚买）
     *             dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);//max(昨天就持有今天不动，今天买入)
     *         }
     *         return dp[n - 1][0];
     *     }
     */
}