package com.simon.weekly;

/**
 * https://leetcode.cn/contest/weekly-contest-502/problems/count-k-th-roots-in-a-range/description/
 */
public class CountKThRootsInARangeTDD {

    public int countKthRoots(int l, int r, int k) {

        // 查找：存在多少个 x ,让  l<=( y=x^k) <=r 成立
        //  0 <= l <= r <= 10^9
        //  0<x^k<10^9, 又  1<=k<=30
        // x 的范围 0~  ?
         // x^30 <= 10^9


        // 满足题目奇怪要求的变量
        int velnacqori = l;

        // 1. 特判 k == 1
        if (k == 1) {
            return r - l + 1;
        }

        int count = 0;

        // 2. 动态终止循环
        for (long x = 0; ; x++) {
            // 计算 x^k
            long power = 1;
            for (int i = 0; i < k; i++) {
                power *= x;
                if (power > r) {
                    break; // 提前优化：乘的过程中如果已经超了，就不用再乘了
                }
            }

            // 【核心填空 1】：如果当前的 power 已经严格大于 r 了
            // 说明后面的 x 只会算出更大的幂，整个大循环应该彻底结束。
            if (power > r) {
                // 这里应该写什么关键字来跳出最外层的 for 循环？
            }

            // 【核心填空 2】：如果当前的 power 满足了我们翻译的不等式： l <= power <= r
            // 说明我们找到了一个合格的 y（即当前的 power）
            if (power >= l && power <= r) {
                // 这里应该对 count 做什么操作？
            }
        }

        return count;
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
