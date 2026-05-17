package com.simon.weekly;

/**
 * https://leetcode.cn/contest/weekly-contest-502/problems/check-adjacent-digit-differences/
 */
public class CheckAdjacentDigitDifferencesTDD {
    public boolean isAdjacentDiffAtMostTwo(String s) {
        return false;
    }

    static void test(boolean exp, boolean act) {
        if (exp == act) {
            System.out.println("PASS exp=" + exp + " act=" + act);
        } else {
            System.out.println("FAIL exp=" + exp + " act=" + act);
        }
    }

    public static void main(String[] args) {
        CheckAdjacentDigitDifferencesTDD s = new CheckAdjacentDigitDifferencesTDD();
        test(true, s.isAdjacentDiffAtMostTwo("132"));
        test(false, s.isAdjacentDiffAtMostTwo("129"));
    }
}
