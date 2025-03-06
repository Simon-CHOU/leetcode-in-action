package com.simon.tw;


class Solution69 {
    //https://leetcode.cn/problems/sqrtx/solutions/2942682/kai-qu-jian-er-fen-jian-ji-xie-fa-python-v4fb/
    public int mySqrt(int x) {
        int right = Math.min(x, 46340) + 1; // sqrt Integer.MAX_VALUE; 2147483647   //2147483647 的平方根大约是 46340.95。
        int left = 0; // 非负数 Integer.MIN_VALUE

        while (left + 1 < right) {
            int mid = left + right >>> 1;
            if (mid * mid <= x) {
                left = mid;
            } else {
                right = mid;
            }
        }
//        return right;
        return left;
    }

}

public class Sqrtx {

    public static void main(String[] args) {
        Solution69 s = new Solution69();
        System.out.println(s.mySqrt(4));
    }
}
