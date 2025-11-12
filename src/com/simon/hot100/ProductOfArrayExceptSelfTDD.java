package com.simon.hot100;

/**
 * 238. 除自身以外数组的乘积
 * <p>题意：给定长度为 n 的整数数组 {@code nums}，返回长度为 n 的整数数组 {@code answer}，
 * 其中 {@code answer[i]} 等于数组中除 {@code nums[i]} 之外其余所有元素的乘积。</p>
 * <p>约束：不允许使用除法；时间复杂度应为 O(n)；额外空间复杂度应为 O(1)（不计输出数组）。</p>
 */
public class ProductOfArrayExceptSelfTDD {

    /**
     * 题解实现类，采用左右乘积法（前缀 + 后缀），实现 O(n) 时间，O(1) 额外空间。
     * <p>核心思路：先在一次正向遍历中为每个位置计算“左侧所有元素乘积”；再在一次反向遍历中
     * 维护滚动的“右侧所有元素乘积”，并与左乘积相乘得到最终答案。</p>
     */
    static class Solution {
        /**
         * 计算除自身以外数组的乘积。
         * <p>算法不使用除法；通过两次线性扫描实现：第一次构建每个位置的左侧乘积，
         * 第二次从右向左用滚动变量累乘右侧乘积并与左侧乘积相乘。</p>
         *
         * @param nums 输入整数数组；若为 {@code null} 或长度为 0，则返回长度为 0 的数组
         * @return 输出数组，其中每个位置为除自身以外的乘积；当长度为 1 时返回 {1}
         */
        public int[] productExceptSelf(int[] nums) {
            if (nums == null || nums.length == 0) {
                return new int[0];
            }
            int n = nums.length;
            int[] ans = new int[n];

            // ans[i] 先承载 nums[0..i-1] 的乘积（左乘积），其中 ans[0] = 1（左侧为空，乘法单位元）
            ans[0] = 1;
            for (int i = 1; i < n; i++) {
                ans[i] = ans[i - 1] * nums[i - 1];
            }

            // 右侧乘积滚动变量：right = nums[i+1] * nums[i+2] * ... * nums[n-1]
            int right = 1;
            for (int i = n - 1; i >= 0; i--) {
                ans[i] = ans[i] * right;
                right = right * nums[i];
            }
            return ans;
        }
    }

    /**
     * 简易 TDD 测试入口：不引入 JUnit，使用 main 方法执行测试并打印 PASS/FAIL。
     * <p>测试覆盖：常规序列、含零场景（一个零、两个零）、负数、单元素、重复元素。</p>
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        Solution s = new Solution();

        // 测试用例集
        int[][] cases = {
                {1, 2, 3, 4},
                {0, 1, 2, 3},
                {0, 0, 2},
                {-1, 1, 0, -3, 3},
                {5},
                {2, 2, 2}
        };
        int[][] expected = {
                {24, 12, 8, 6},
                {6, 0, 0, 0},
                {0, 0, 0},
                {0, 0, 9, 0, 0},
                {1},
                {4, 4, 4}
        };

        int pass = 0;
        int fail = 0;

        for (int i = 0; i < cases.length; i++) {
            int[] input = cases[i];
            int[] actual = s.productExceptSelf(input);
            boolean ok = arraysEqual(expected[i], actual);
            if (ok) {
                pass++;
                System.out.println("CASE#" + (i + 1) + " PASS  input=" + formatArray(input)
                        + " expected=" + formatArray(expected[i]) + " actual=" + formatArray(actual));
            } else {
                fail++;
                System.out.println("CASE#" + (i + 1) + " FAIL  input=" + formatArray(input)
                        + " expected=" + formatArray(expected[i]) + " actual=" + formatArray(actual));
            }
        }

        System.out.println("SUMMARY: PASS=" + pass + " FAIL=" + fail);
        if (fail == 0) {
            System.out.println("ALL TESTS PASSED");
        } else {
            System.out.println("SOME TESTS FAILED");
        }
    }

    /**
     * 数组内容比较（逐元素）。
     *
     * @param a 第一个数组
     * @param b 第二个数组
     * @return 当两个数组长度相等且逐元素相等时返回 true；否则返回 false
     */
    private static boolean arraysEqual(int[] a, int[] b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将数组格式化为易读字符串。
     *
     * @param arr 输入数组
     * @return 字符串形式，如 "[1, 2, 3]"
     */
    private static String formatArray(int[] arr) {
        if (arr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
