package com.simon.hot100;

/**
 * LeetCode 416. 分割等和子集
 * <p>
 * 使用动态规划解决0-1背包问题的变种，判断数组是否可以分割成两个和相等的子集
 */
public class PartitionEqualSubsetSumTDD {

    /**
     * 判断给定数组是否可以分割成两个和相等的子集
     * <p>
     * 算法思路：
     * 1. 计算数组总和，如果总和为奇数，则无法分割成两个相等子集
     * 2. 目标是找到和为target=total/2的子集
     * 3. 使用一维DP数组，dp[i]表示是否可以得到和为i的子集
     * 4. 遍历每个数字，更新DP数组（从target到num的逆序更新）
     * <p>
     * 时间复杂度：O(n * target)，其中n是数组长度，target是数组总和的一半
     * 空间复杂度：O(target)
     *
     * @param nums 整数数组
     * @return 如果可以分割成两个相等子集返回true，否则返回false
     */
    public boolean canPartition(int[] nums) {
        // 计算数组总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 如果总和为奇数，无法分割成两个相等子集
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        // dp[i]表示是否可以得到和为i的子集
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // 和为0的子集总是存在（空集）

        // 遍历每个数字
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            // 逆序更新dp数组，避免重复使用当前数字
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }

    

    /**
     * 测试函数，使用TDD方式验证算法正确性
     */
    public static void main(String[] args) {
        PartitionEqualSubsetSumTDD solution = new PartitionEqualSubsetSumTDD();

        // 测试用例1: 可以分割 [1,5,11,5] -> [1,5,5]和[11]
        testCase(solution, new int[]{1, 5, 11, 5}, true, "测试用例1: [1,5,11,5]");

        // 测试用例2: 不可以分割 [1,2,3,5]
        testCase(solution, new int[]{1, 2, 3, 5}, false, "测试用例2: [1,2,3,5]");

        // 测试用例3: 空数组
        testCase(solution, new int[]{}, true, "测试用例3: 空数组");

        // 测试用例4: 单个元素0
        testCase(solution, new int[]{0}, true, "测试用例4: [0]");

        // 测试用例5: 单个元素非0
        testCase(solution, new int[]{1}, false, "测试用例5: [1]");

        // 测试用例6: 两个相同元素
        testCase(solution, new int[]{2, 2}, true, "测试用例6: [2,2]");

        // 测试用例7: 较大的测试用例
        testCase(solution, new int[]{1, 2, 5}, false, "测试用例7: [1,2,5]");
    }

    /**
     * 执行单个测试用例并输出结果
     *
     * @param solution 解决方案实例
     * @param nums     测试数组
     * @param expected 期望结果
     * @param testName 测试名称
     */
    private static void testCase(PartitionEqualSubsetSumTDD solution, int[] nums, boolean expected, String testName) {
        System.out.println("执行: " + testName);
        
        boolean result = solution.canPartition(nums);
        String status = (result == expected) ? "PASS" : "FAIL";
        
        System.out.println("测试结果: " + status);
    }

    
}
