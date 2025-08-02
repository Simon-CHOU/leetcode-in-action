package com.simon.tdd;

public class MinimumRecolorsToGetKConsecutiveBlackBlocksTDD {
    private static final char BLACK = 'B';
    private static final int IMPOSSIBLE = 0; // 题目保证 k <= n，故不会出现

    private MinimumRecolorsToGetKConsecutiveBlackBlocksTDD() { /* utility class */ }

    public static int minimumRecolors(String blocks, int k) {
        if (blocks == null || k <= 0 || k > blocks.length()) {
            throw new IllegalArgumentException("Invalid input");
        }

        int n = blocks.length();
        int minOps = Integer.MAX_VALUE;

        int currentBlack = 0;
        // 初始窗口 [0..k-1]
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == BLACK) currentBlack++;
        }
        minOps = k - currentBlack;

        // 滑动窗口
        for (int right = k; right < n; right++) {
            int left = right - k;
            if (blocks.charAt(right) == BLACK) currentBlack++;
            if (blocks.charAt(left)  == BLACK) currentBlack--;
            minOps = Math.min(minOps, k - currentBlack);
            if (minOps == 0) break;   // 无法更优
        }
        return minOps;
    }
    /* ---------- 自包含的 main 测试 ---------- */
    public static void main(String[] args) {
        test("WWBBBWBBBBBWWBWWWB", 16, 6);
        test("WBWBBWWWB", 3, 1);
        test("BBBBB", 5, 0);
        test("W", 1, 1);
        test("BWWWBB", 6, 3);
        test("WBWBWBWBWB", 2, 1);
        System.out.println("All tests passed.");
    }

    private static void test(String blocks, int k, int expected) {
        int actual = minimumRecolors(blocks, k);
        if (actual != expected) {
            throw new AssertionError(
                    String.format("Test failed: blocks=%s, k=%d, expected=%d, actual=%d",
                            blocks, k, expected, actual));
        }
    }
}
