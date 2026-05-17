package com.simon.weekly;

/**
 * https://leetcode.cn/contest/weekly-contest-502/problems/check-adjacent-digit-differences/
 */
public class CheckAdjacentDigitDifferencesTDD {
    public boolean isAdjacentDiffAtMostTwo(String s) {
        final int maxDiff = 2;
        char[] charArray = s.toCharArray();
        if(s.length() <2) {
            return false;
        }
        for (int i = 0; i < charArray.length -1; i++) {
            int i1 = Math.abs(charArray[i] - charArray[i + 1]);
            if(i1 >maxDiff) {
                return  false;
            }
        }
        return true;
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
