package com.simon.hot100;

/**
 * LeetCode 169. 多数元素 - Boyer-Moore投票算法实现
 * <p>
 * 问题描述：给定一个大小为 n 的数组，找到其中的多数元素。
 * 多数元素是指在数组中出现次数大于 ⌊n/2⌋ 的元素。
 * <p>
 * 算法思想：Boyer-Moore投票算法
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(1)
 * <p>
 * 核心原理：不同元素相互抵消，多数元素必然剩余
 * 
 * @author Simon
 * @version 1.0
 */
public class MajorityElementTDD {
    
    /**
     * 使用Boyer-Moore投票算法找到数组中的多数元素
     * <p>
     * 算法步骤：
     * 1. 初始化候选人candidate和计数器count
     * 2. 遍历数组，执行投票逻辑
     * 3. 返回最终的候选人
     * 
     * @param nums 输入的整数数组，保证存在多数元素
     * @return 数组中的多数元素
     * @throws IllegalArgumentException 如果输入数组为null或空
     */
    public int majorityElement(int[] nums) {
        // 输入验证
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("输入数组不能为null或空");
        }
        
        // Boyer-Moore投票算法核心实现
        int candidate = nums[0];  // 候选人
        int count = 1;           // 票数计数器
        
        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                // 票数为0时，更换候选人
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                // 相同元素，票数+1
                count++;
            } else {
                // 不同元素，票数-1（抵消）
                count--;
            }
        }
        
        return candidate;
    }
    
    /**
     * 测试驱动开发(TDD)主方法
     * <p>
     * 包含多个测试用例验证算法正确性
     */
    public static void main(String[] args) {
        MajorityElementTDD solution = new MajorityElementTDD();
        
        System.out.println("=== LeetCode 169. 多数元素 - TDD测试 ===\n");
        
        // 测试用例计数器
        int testCount = 0;
        int passCount = 0;
        
        // 测试用例1: 基本情况 - 奇数长度数组
        testCount++;
        try {
            int[] nums1 = {3, 2, 3};
            int expected1 = 3;
            int actual1 = solution.majorityElement(nums1);
            if (actual1 == expected1) {
                System.out.println("✅ 测试用例1 PASS: [3,2,3] -> " + actual1);
                passCount++;
            } else {
                System.out.println("❌ 测试用例1 FAIL: 期望=" + expected1 + ", 实际=" + actual1);
            }
        } catch (Exception e) {
            System.out.println("❌ 测试用例1 FAIL: 异常 - " + e.getMessage());
        }
        
        // 测试用例2: 偶数长度数组
        testCount++;
        try {
            int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
            int expected2 = 2;
            int actual2 = solution.majorityElement(nums2);
            if (actual2 == expected2) {
                System.out.println("✅ 测试用例2 PASS: [2,2,1,1,1,2,2] -> " + actual2);
                passCount++;
            } else {
                System.out.println("❌ 测试用例2 FAIL: 期望=" + expected2 + ", 实际=" + actual2);
            }
        } catch (Exception e) {
            System.out.println("❌ 测试用例2 FAIL: 异常 - " + e.getMessage());
        }
        
        // 测试用例3: 单元素数组
        testCount++;
        try {
            int[] nums3 = {1};
            int expected3 = 1;
            int actual3 = solution.majorityElement(nums3);
            if (actual3 == expected3) {
                System.out.println("✅ 测试用例3 PASS: [1] -> " + actual3);
                passCount++;
            } else {
                System.out.println("❌ 测试用例3 FAIL: 期望=" + expected3 + ", 实际=" + actual3);
            }
        } catch (Exception e) {
            System.out.println("❌ 测试用例3 FAIL: 异常 - " + e.getMessage());
        }
        
        // 测试用例4: 所有元素相同
        testCount++;
        try {
            int[] nums4 = {5, 5, 5, 5};
            int expected4 = 5;
            int actual4 = solution.majorityElement(nums4);
            if (actual4 == expected4) {
                System.out.println("✅ 测试用例4 PASS: [5,5,5,5] -> " + actual4);
                passCount++;
            } else {
                System.out.println("❌ 测试用例4 FAIL: 期望=" + expected4 + ", 实际=" + actual4);
            }
        } catch (Exception e) {
            System.out.println("❌ 测试用例4 FAIL: 异常 - " + e.getMessage());
        }
        
        // 测试用例5: 负数情况
        testCount++;
        try {
            int[] nums5 = {-1, -1, -2, -1, -2, -1, -1};
            int expected5 = -1;
            int actual5 = solution.majorityElement(nums5);
            if (actual5 == expected5) {
                System.out.println("✅ 测试用例5 PASS: [-1,-1,-2,-1,-2,-1,-1] -> " + actual5);
                passCount++;
            } else {
                System.out.println("❌ 测试用例5 FAIL: 期望=" + expected5 + ", 实际=" + actual5);
            }
        } catch (Exception e) {
            System.out.println("❌ 测试用例5 FAIL: 异常 - " + e.getMessage());
        }
        
        // 测试用例6: 边界情况 - null输入
        testCount++;
        try {
            int[] nums6 = null;
            solution.majorityElement(nums6);
            System.out.println("❌ 测试用例6 FAIL: 应该抛出异常但没有");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ 测试用例6 PASS: null输入正确抛出异常 - " + e.getMessage());
            passCount++;
        } catch (Exception e) {
            System.out.println("❌ 测试用例6 FAIL: 抛出了错误的异常类型 - " + e.getClass().getSimpleName());
        }
        
        // 测试用例7: 边界情况 - 空数组
        testCount++;
        try {
            int[] nums7 = {};
            solution.majorityElement(nums7);
            System.out.println("❌ 测试用例7 FAIL: 应该抛出异常但没有");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ 测试用例7 PASS: 空数组正确抛出异常 - " + e.getMessage());
            passCount++;
        } catch (Exception e) {
            System.out.println("❌ 测试用例7 FAIL: 抛出了错误的异常类型 - " + e.getClass().getSimpleName());
        }
        
        // 测试结果汇总
        System.out.println("\n=== 测试结果汇总 ===");
        System.out.println("总测试用例: " + testCount);
        System.out.println("通过用例: " + passCount);
        System.out.println("失败用例: " + (testCount - passCount));
        System.out.println("通过率: " + String.format("%.1f%%", (double) passCount / testCount * 100));
        
        if (passCount == testCount) {
            System.out.println("🎉 所有测试用例通过！算法实现正确！");
        } else {
            System.out.println("⚠️  存在失败的测试用例，请检查算法实现！");
        }
    }
}
