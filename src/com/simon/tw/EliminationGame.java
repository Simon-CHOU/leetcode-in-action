package com.simon.tw;
class Solution390 {
    public int lastRemaining(int n) {
        if(n == 1) {
            return 1;
        }
        return 2 *(n /2 + 1 - lastRemaining( n / 2));
    }
}
public class EliminationGame {
    public static void main(String[] args) {
        Solution390 solution390 = new Solution390();
        System.out.println(solution390.lastRemaining(9)); // 6
    }
}
