package com.simon.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 求解“Most Frequent IDs（动态维护出现次数最大值）”问题的参考实现。<p>
 * 问题：给定等长数组 {@code nums} 与 {@code freq}。对于每个索引 i，令 id = nums[i]，并将该 id 的出现次数按 {@code freq[i]} 增减（允许负数）。<p>
 * 在每次更新之后，返回当前所有 id 中的最大出现次数，最终返回长度为 n 的数组。<p>
 * 算法：
 * <p>使用 {@code HashMap<Integer, Long>} 维护每个 id 的当前出现次数；再使用 {@code TreeMap<Long, Integer>} 维护“出现次数 -> 拥有该出现次数的 id 个数”的桶结构，以便在每次更新后以对数复杂度取得最大出现次数（树的最大键）。<p>
 * 说明：若某一步出现次数降为负数，则在本实现中将其视作 0 并移除该 id（更稳健）。如题目保证每次更新后次数非负，则该分支不会触发。<p>
 * TDD：见 {@link #main(String[])}，通过若干用例以 PASS/FAIL 文本日志验证。<p>
 */
public class MostFrequentIdsTDD {

    /**
     * 计算每次增量更新后的最大出现次数。
     * <p>时间复杂度：每次更新为 O(log K)，其中 K 为当前出现次数桶的不同键数量（不超过不同 id 的个数）。总复杂度为 O(n log K)。<p>
     * 空间复杂度：O(K)。
     *
     * @param nums 每次更新的 id 序列
     * @param freq 与 {@code nums} 对应的出现次数增量（可为负）
     * @return 长度为 {@code nums.length} 的数组，其中每个元素为该步更新后全局最大出现次数
     * @throws IllegalArgumentException 当 {@code nums} 与 {@code freq} 长度不同时抛出
     */
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        if (nums == null || freq == null || nums.length != freq.length) {
            throw new IllegalArgumentException("nums and freq must be non-null and of equal length");
        }

        int n = nums.length;
        long[] ans = new long[n];

        // id -> current count
        Map<Integer, Long> countById = new HashMap<>();
        // count -> number of ids owning this count
        TreeMap<Long, Integer> buckets = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int id = nums[i];
            long delta = freq[i];

            long oldCount = countById.getOrDefault(id, 0L);
            long newCount = oldCount + delta;

            // 移除旧桶（oldCount > 0 才有桶），并更新该 id 的计数
            if (oldCount > 0) {
                decBucket(buckets, oldCount);
            }

            if (newCount <= 0) {
                // 将负数/零视为移除该 id
                countById.remove(id);
            } else {
                countById.put(id, newCount);
                incBucket(buckets, newCount);
            }

            ans[i] = buckets.isEmpty() ? 0L : buckets.lastKey();
        }

        return ans;
    }

    private static void incBucket(TreeMap<Long, Integer> buckets, long key) {
        buckets.put(key, buckets.getOrDefault(key, 0) + 1);
    }

    private static void decBucket(TreeMap<Long, Integer> buckets, long key) {
        Integer c = buckets.get(key);
        if (c == null) return;
        if (c <= 1) buckets.remove(key);
        else buckets.put(key, c - 1);
    }

    /**
     * 使用 main 方法进行 TDD：构造用例、调用被测方法、打印 PASS/FAIL。
     * <p>无需引入 JUnit，方便在任意环境下直接运行与验证。<p>
     */
    public static void main(String[] args) {
        MostFrequentIdsTDD solver = new MostFrequentIdsTDD();

        // 用例 1：基础递增
        runTest(
                "basic-increase",
                solver.mostFrequentIDs(
                        new int[]{1, 1, 2, 1},
                        new int[]{1, 1, 1, 1}
                ),
                new long[]{1, 2, 2, 3}
        );

        // 用例 2：递增后递减（次数可以归零）
        runTest(
                "increase-then-decrease",
                solver.mostFrequentIDs(
                        new int[]{1, 1, 2, 2},
                        new int[]{1, -1, 1, -1}
                ),
                new long[]{1, 0, 1, 0}
        );

        // 用例 3：多 id 交错更新
        runTest(
                "interleaved-ids",
                solver.mostFrequentIDs(
                        new int[]{1, 2, 2, 1, 2},
                        new int[]{1, 1, 1, 1, -1}
                ),
                new long[]{1, 1, 2, 2, 2}
        );

        // 用例 4：大数更新，确保使用 long 不溢出
        runTest(
                "large-counts",
                solver.mostFrequentIDs(
                        new int[]{5, 5, 5},
                        new int[]{1_000_000_000, 1_000_000_000, 1_000_000_000}
                ),
                new long[]{1_000_000_000L, 2_000_000_000L, 3_000_000_000L}
        );

        // 用例 5：空输入
        runTest(
                "empty-input",
                solver.mostFrequentIDs(new int[]{}, new int[]{}),
                new long[]{}
        );

        // 用例 6：负数导致计数降至零（稳健性）
        runTest(
                "negative-to-zero",
                solver.mostFrequentIDs(
                        new int[]{7, 7},
                        new int[]{2, -3}
                ),
                new long[]{2, 0}
        );
    }

    private static void runTest(String name, long[] actual, long[] expected) {
        boolean ok = arraysEqual(actual, expected);
        if (ok) {
            System.out.println("[PASS] " + name);
        } else {
            System.out.println("[FAIL] " + name);
            System.out.println("  expected: " + arrayToString(expected));
            System.out.println("  actual  : " + arrayToString(actual));
        }
    }

    private static boolean arraysEqual(long[] a, long[] b) {
        if (a == null || b == null) return a == b;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    private static String arrayToString(long[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(',').append(' ');
            sb.append(arr[i]);
        }
        sb.append(']');
        return sb.toString();
    }
}
