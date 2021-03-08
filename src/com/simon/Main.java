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
        // 自底向上的动态规划
        if (coins.length == 0) {
            return -1;
        }

        // memo[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
        int[] memo = new int[amount + 1];
        memo[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int rest = i - coin;
                if (rest <0){
                    continue;
                }
                if (memo[rest] < min) {
                    min = memo[rest] + 1;
                }
            }
            memo[i] = min;
        }
        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}
/**
 * [1,2,5]
 * 11
 * exp:3
 * [186,419,83,408]
 * 6249
 * exp:20
 */