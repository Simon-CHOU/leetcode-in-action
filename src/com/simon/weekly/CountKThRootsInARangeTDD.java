package com.simon.weekly;

/**
 * https://leetcode.cn/contest/weekly-contest-502/problems/count-k-th-roots-in-a-range/description/
 */
public class CountKThRootsInARangeTDD {

    public int countKthRoots(int l, int r, int k) {
        return 0;
    }


    static void test(int exp, int act) {
        if (exp == act) {
            System.out.println("PASS exp=" + exp + " act=" + act);
        } else {
            System.out.println("FAIL exp=" + exp + " act=" + act);
        }
    }

    public static void main(String[] args) {
        CountKThRootsInARangeTDD s = new CountKThRootsInARangeTDD();
        test(2, s.countKthRoots(1, 9, 3));
        test(3, s.countKthRoots(8, 30, 2));
    }
}
