package com.simon.tdd;

import java.util.Arrays;

public class KRadiusSubarrayAveragesTDD {
    /**
     * 2090. 半径为 k 的子数组平均值
     * @param nums 输入数组
     * @param k 子数组半径
     * @return 每个中心点对应的平均值数组
     */
    public int[] getAverages(int[] nums, int k) {
        // 初始化结果数组，默认值为-1
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        
        // 如果k为0，则每个元素的平均值就是它本身
        if (k == 0) {
            return nums.clone();
        }
        
        // 如果2*k+1大于数组长度，则无法构成任何有效子数组
        if (2 * k + 1 > nums.length) {
            return result;
        }
        
        // 使用滑动窗口计算平均值
        long windowSum = 0;
        int windowSize = 2 * k + 1;
        
        // 计算初始窗口的和
        for (int i = 0; i < windowSize; i++) {
            windowSum += nums[i];
        }
        
        // 设置第一个有效中心点的平均值
        result[k] = (int) (windowSum / windowSize);
        
        // 滑动窗口计算其他中心点的平均值
        for (int i = k + 1; i < nums.length - k; i++) {
            // 移除窗口左边的元素，添加窗口右边的新元素
            windowSum = windowSum - nums[i - k - 1] + nums[i + k];
            result[i] = (int) (windowSum / windowSize);
        }
        
        return result;
    }

    public static void main(String[] args) {
        KRadiusSubarrayAveragesTDD solution = new KRadiusSubarrayAveragesTDD();
        boolean allPassed = true;
        
        // 测试用例1: 标准示例
        int[] nums1 = {7, 4, 3, 9, 1, 8, 5, 2, 6};
        int k1 = 3;
        int[] expected1 = {-1, -1, -1, 5, 4, 4, -1, -1, -1};
        int[] result1 = solution.getAverages(nums1, k1);
        System.out.println("Test 1:");
        System.out.println("Input: nums = [7,4,3,9,1,8,5,2,6], k = 3");
        System.out.println("Expected: [-1,-1,-1,5,4,4,-1,-1,-1]");
        System.out.println("Actual:   " + java.util.Arrays.toString(result1));
        boolean pass1 = java.util.Arrays.equals(result1, expected1);
        System.out.println("Result: " + (pass1 ? "PASS" : "FAIL"));
        allPassed &= pass1;
        System.out.println();
        
        // 测试用例2: k = 0的情况
        int[] nums2 = {100000};
        int k2 = 0;
        int[] expected2 = {100000};
        int[] result2 = solution.getAverages(nums2, k2);
        System.out.println("Test 2:");
        System.out.println("Input: nums = [100000], k = 0");
        System.out.println("Expected: [100000]");
        System.out.println("Actual:   " + java.util.Arrays.toString(result2));
        boolean pass2 = java.util.Arrays.equals(result2, expected2);
        System.out.println("Result: " + (pass2 ? "PASS" : "FAIL"));
        allPassed &= pass2;
        System.out.println();
        
        // 测试用例3: 无法构成有效子数组的情况
        int[] nums3 = {1, 2, 3, 4};
        int k3 = 2;
        int[] expected3 = {-1, -1, -1, -1};
        int[] result3 = solution.getAverages(nums3, k3);
        System.out.println("Test 3:");
        System.out.println("Input: nums = [1,2,3,4], k = 2");
        System.out.println("Expected: [-1,-1,-1,-1]");
        System.out.println("Actual:   " + java.util.Arrays.toString(result3));
        boolean pass3 = java.util.Arrays.equals(result3, expected3);
        System.out.println("Result: " + (pass3 ? "PASS" : "FAIL"));
        allPassed &= pass3;
        System.out.println();
        
        // 测试用例4: 精度问题测试
        int[] nums4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k4 = 1;
        int[] expected4 = {-1, 2, 3, 4, 5, 6, 7, 8, 9, -1};
        int[] result4 = solution.getAverages(nums4, k4);
        System.out.println("Test 4:");
        System.out.println("Input: nums = [1,2,3,4,5,6,7,8,9,10], k = 1");
        System.out.println("Expected: [-1,2,3,4,5,6,7,8,9,-1]");
        System.out.println("Actual:   " + java.util.Arrays.toString(result4));
        boolean pass4 = java.util.Arrays.equals(result4, expected4);
        System.out.println("Result: " + (pass4 ? "PASS" : "FAIL"));
        allPassed &= pass4;
        System.out.println();
        
        System.out.println("================================");
        System.out.println("All tests passed: " + allPassed);
    }
}