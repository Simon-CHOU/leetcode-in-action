package com.simon.tw;

import java.util.Arrays;

class Solution274 {
    public int hIndex(int[] citations) {// 二分。
        int left = 0, right = citations.length;
        int mid = 0, cnt = 0;
        while (left < right) {
            mid = (left + right + 1) >>> 1;
            cnt = 0;
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] >= mid) {
                    cnt++;
                }
            }
            if (cnt >= mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int hIndexCountSort(int[] citations) {//https://leetcode.cn/problems/h-index/solutions/869042/h-zhi-shu-by-leetcode-solution-fnhl/
        int n = citations.length, tot = 0;
        int[] counter = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                counter[n]++;
            } else {
                counter[citations[i]]++; // 重叠起来用数组很常见。也很打脑壳。
            }
        }
        for (int i = n; i >= 0; i--) {
            tot += counter[i];
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }

    public int hIndexSort(int[] citations) {
        Arrays.sort(citations);
        int h = 0, len = citations.length - 1;
        while (len >= 0 && citations[len] > h) {
            h++;
            len--;
        }
        // 定义 至少 有 h 篇论文被引用次数大于等于 h
        // 定义 len=4, citations[4]=6 > 0
        return h;
    }

    public int hIndexSelf(int[] citations) { // 20250311 自解

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


        System.out.println(solution274.hIndex(new int[]{1, 3, 1})); // exp 1
        System.out.println(solution274.hIndex(new int[]{0})); // exp 1
    }
}
