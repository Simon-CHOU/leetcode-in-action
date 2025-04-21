package com.simon.daily;

class Solution2145 {
    public int numberOfArrays(int[] differences, int lower, int upper) {
       // 不是穷举法，是数学题
        long s = 0, minS = 0, maxS = 0;
        for (int d : differences) {
            s += d;
            minS = Math.min(minS, s);
            maxS = Math.max(maxS, s);
        }
        return (int) Math.max(upper- lower - maxS + minS + 1, 0);
    }
}
// https://leetcode.cn/problems/count-the-hidden-sequences/description/?envType=daily-question&envId=2025-04-21
public class CountTheHiddenSequences {
    public static void main(String[] args) {
        Solution2145 ss = new Solution2145();
        int[] differences = {1, -3, 4};
        int lower = 1;
        int upper = 6;
        System.out.println(ss.numberOfArrays(differences, lower, upper));// 2
    }
}
