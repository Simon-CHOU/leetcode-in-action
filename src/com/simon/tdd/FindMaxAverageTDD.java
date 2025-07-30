package com.simon.tdd;

public class FindMaxAverageTDD {
    public double findMaxAverage(int[] nums, int k) {
        // 处理单元素数组
        if (nums.length == 1) return (double) nums[0];

        int windowSum = 0;
        // 初始化窗口和
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        int maxSum = windowSum;
        // 滑动窗口：右边界从k开始
        for (int right = k; right < nums.length; right++) {
            int left = right - k; // 窗口左边界
            windowSum = windowSum - nums[left] + nums[right];
            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k; // 最终转为浮点除法
    }

    // 在main中实现TDD测试
    public static void main(String[] args) {
        FindMaxAverageTDD solution = new FindMaxAverageTDD();
        boolean allPassed = true;

        // 测试用例1：标准示例
        int[] test1 = {1,12,-5,-6,50,3};
        double result1 = solution.findMaxAverage(test1, 4);
        System.out.println("Test 1: " + result1 + " → " + (result1 == 12.75 ? "PASS" : "FAIL"));
        allPassed &= (result1 == 12.75);

        // 测试用例2：全负数数组
        int[] test2 = {-1,-2,-3,-4,-5};
        double result2 = solution.findMaxAverage(test2, 2);
        System.out.println("Test 2: " + result2 + " → " + (result2 == -1.5 ? "PASS" : "FAIL"));
        allPassed &= (result2 == -1.5);

        // 测试用例3：单元素数组
        int[] test3 = {5};
        double result3 = solution.findMaxAverage(test3, 1);
        System.out.println("Test 3: " + result3 + " → " + (result3 == 5.0 ? "PASS" : "FAIL"));
        allPassed &= (result3 == 5.0);

        // 测试用例4：k等于数组长度
        int[] test4 = {0,1,2,3,4};
        double result4 = solution.findMaxAverage(test4, 5);
        System.out.println("Test 4: " + result4 + " → " + (result4 == 2.0 ? "PASS" : "FAIL"));
        allPassed &= (result4 == 2.0);

        System.out.println("\nAll tests passed? " + allPassed);
    }
}
