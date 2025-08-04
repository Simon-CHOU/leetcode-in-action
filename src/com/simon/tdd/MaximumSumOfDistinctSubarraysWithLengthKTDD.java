package com.simon.tdd;

public class MaximumSumOfDistinctSubarraysWithLengthKTDD {

    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        long curSum = 0;
        int n = nums.length;
        
        // 使用数组代替HashMap来提高性能，假设数值范围不会太大
        // 这里使用一个足够大的数组来模拟哈希表
        int[] count = new int[100001]; // 根据题目数据范围调整
        int distinct = 0; // 记录当前窗口中不同元素的个数

        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            int num = nums[i];
            curSum += num;
            if (count[num] == 0) {
                distinct++;
            }
            count[num]++;
        }

        // 如果第一个窗口满足条件（所有元素都不相同）
        if (distinct == k) {
            maxSum = curSum;
        }

        // 滑动窗口
        for (int i = k; i < n; i++) {
            int left = nums[i - k];  // 要移出窗口的元素
            int right = nums[i];     // 要加入窗口的元素
            
            // 更新当前窗口和
            curSum = curSum - left + right;

            // 移除左侧元素
            count[left]--;
            if (count[left] == 0) {
                distinct--;
            }

            // 添加右侧元素
            if (count[right] == 0) {
                distinct++;
            }
            count[right]++;

            // 如果当前窗口满足条件（所有元素都不相同）
            if (distinct == k) {
                maxSum = Math.max(maxSum, curSum);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSumOfDistinctSubarraysWithLengthKTDD solution = new MaximumSumOfDistinctSubarraysWithLengthKTDD();
        int[] nums = {1, 5, 4, 2, 9, 9, 9};
        int k = 3;
        long expected = 15;
        long result = solution.maximumSubarraySum(nums, k);
        System.out.println("Test case 1:");
        System.out.println("Input: nums = [1,5,4,2,9,9,9], k = 3");
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Pass: " + (result == expected));


        int[] nums2 = {9, 9, 9, 1, 2, 3};
        int k2 = 3;
        long expected2 = 12; // [9,1,2] 的和为 12
        long result2 = solution.maximumSubarraySum(nums2, k2);
        System.out.println("\nTest case 2:");
        System.out.println("Input: nums = [9,9,9,1,2,3], k = 3");
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
        System.out.println("Pass: " + (result2 == expected2));
    }
}
