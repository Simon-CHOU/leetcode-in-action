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
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins
                 ) {
                if(i-coin<0)continue;;
                dp[i] = Math.min(dp[i], 1+dp[i-coin]);
            }
        }
        return (dp[amount] == amount+1)?-1:dp[amount];
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