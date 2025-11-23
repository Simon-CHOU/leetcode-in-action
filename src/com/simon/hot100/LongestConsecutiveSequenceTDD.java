package com.simon.hot100;

import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列问题解决方案。<p>
 * <p>
 * 算法核心：利用HashSet实现O(1)查找，仅当num-1不存在时（序列起点）才开始计数，
 * 确保每个元素最多被访问两次，达到O(n)时间复杂度。
 */
public class LongestConsecutiveSequenceTDD {

    /**
     * 找出未排序整数数组中最长连续数字序列的长度。<p>
     * <p>
     * 示例: [100,4,200,1,3,2] → 最长序列为[1,2,3,4] → 返回4
     *
     * @param nums 输入整数数组，可能包含重复元素
     * @return 最长连续序列的长度（数组为空时返回0）
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = buildNumberSet(nums);
        return findLongestSequenceLength(numSet);
    }

    /**
     * 步骤1：将所有数字存入HashSet，实现O(1)查找。
     */
    private Set<Integer> buildNumberSet(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        return numSet;
    }

    /**
     * 步骤2&3：遍历set，找出最长连续序列。
     */
    private int findLongestSequenceLength(Set<Integer> numSet) {
        int maxLength = 0;

        for (int num : numSet) {
            // 仅当num-1不存在时才开始计数（序列起点）
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;

                // 向后延伸序列
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }

    /**
     * TDD测试入口，验证算法正确性。
     */
    public static void main(String[] args) {
        LongestConsecutiveSequenceTDD solution = new LongestConsecutiveSequenceTDD();

        // 测试1：基本场景
        testCase("基本场景", new int[]{100, 4, 200, 1, 3, 2}, 4, solution);

        // 测试2：空数组
        testCase("空数组", new int[]{}, 0, solution);

        // 测试3：单元素数组
        testCase("单元素数组", new int[]{5}, 1, solution);

        // 测试4：全部重复元素
        testCase("全部重复元素", new int[]{2, 2, 2, 2}, 1, solution);

        // 测试5：负数序列
        testCase("负数序列", new int[]{-1, -3, -2, -5, -4}, 5, solution);

        // 测试6：无连续序列
        testCase("无连续序列", new int[]{1, 3, 5, 7, 9}, 1, solution);

        // 测试7：已经排序
        testCase("已经排序", new int[]{1, 2, 3, 4, 5}, 5, solution);

        // 测试8：大范围间隔
        testCase("大范围间隔", new int[]{0, 1, 2, 1000000, 1000001, 1000002, 1000003}, 4, solution);
    }

    /**
     * 执行单个测试用例并打印结果。
     */
    private static void testCase(String description, int[] nums, int expected, LongestConsecutiveSequenceTDD solution) {
        int actual = solution.longestConsecutive(nums);
        String status = actual == expected ? "PASS" : "FAIL";
        System.out.printf("[%s] %s: nums=%s, expected=%d, actual=%d%n", status, description, arrayToString(nums), expected, actual);
    }

    /**
     * 数组转字符串辅助方法。
     */
    private static String arrayToString(int[] nums) {
        if (nums.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < Math.min(nums.length, 10); i++) {
            sb.append(nums[i]).append(", ");
        }
        if (nums.length > 10) {
            sb.append("...").append(nums[nums.length - 1]);
        } else {
            sb.setLength(sb.length() - 2);
        }
        return sb.append("]").toString();
    }
}