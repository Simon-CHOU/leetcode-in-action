package com.simon.nowcoder;

/**
 * 01 背包，动态规划&最优解回溯
 * https://blog.csdn.net/qq_38410730/article/details/81667885
 */
public class Main {
    static int[] w = {0, 2, 3, 4, 5};
    static int[] v = {0, 3, 4, 5, 6};
    static int bagCapacity = 8;
    static int[][] dp = new int[5][9];
    static int[] item = new int[5]; // optimal solution

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= bagCapacity; j++) {
                if (j - w[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 8) { // last item of row
                    System.out.print(dp[i][j] + "\n");
                } else {
                    System.out.print(dp[i][j] + " ");
                }
            }
        }
        recall(4,8);
        for (int i = 0; i < 5; i++) {
            if(item[i] == 1){
                System.out.print("get " + i +" ");
            }
        }
    }

    static void recall(int i, int j) {
        if (i > 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                item[i] = 0;
                recall(i - 1, j);
            } else if (j - w[i] >= 0 &&
                    dp[i][j] == dp[i - 1][j - w[i]] + v[i]) {
                item[i] = 1;
                recall(i - 1, j - w[i]);
            }
        }
    }
}
