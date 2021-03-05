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

    public int coinChange(int[] coins, int amount) {
        return dp(amount, coins, Integer.MAX_VALUE);
    }

    int dp(int n, final int[] coins, int res) {
        if (n == 0) return 0;
        if (n < 0) return -1;
        for (int coin : coins) {
            int subProblem = dp(n - coin, coins, res);
            if (subProblem == -1) {//子问题无界，则跳过
                continue;
            }
            res = Math.min(res, 1 + subProblem);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }
}
/**
 * [1,2,5]
 * 11
 * exp:3
 */