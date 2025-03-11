package com.simon.tw;

class Solution274 {
    public int hIndex(int[] citations) { // 20250311 自解

//        int min = Integer.MAX_VALUE;
//        int max = 0;
//        for (int ii : citations) {
//            if (min > ii) min = ii;
//            if (max < ii) max = ii;
//        }
        // 如果 h 有多种可能的值，h 指数 是其中最大的那个。
        for (int i = citations.length; i >= 1; i--) {
            if (judge(citations, i)) return i;
        }

        return 0;
    }

    boolean judge(int[] arr, int x) {
        // 他（她）至少发表了 h 篇论文
        int c1 = arr.length;
//        for (int ii : arr) {
//            c1 += ii;
//        }
        if (c1 < x) return false;
        // 至少 有 h 篇论文被引用次数大于等于 h
        int c = 0;
        for (int ii : arr) {
            if (ii >= x) c++;
        }
//        if (c != x) return false;

        return c >= x; // 定义：有c篇大于假设的 h-index x，就是合法，判别通过。之后再从结果里挑最大的。
//        return true;
    }
}

public class HIndex {
    public static void main(String[] args) {
        Solution274 solution274 = new Solution274();
        int[] arr = new int[]{3, 0, 6, 1, 5};
        System.out.println(solution274.hIndex(arr)); // exp 3


        System.out.println(solution274.hIndex(new int[]{1,3,1})); // exp 1
        System.out.println(solution274.hIndex(new int[]{0})); // exp 1
    }
}
