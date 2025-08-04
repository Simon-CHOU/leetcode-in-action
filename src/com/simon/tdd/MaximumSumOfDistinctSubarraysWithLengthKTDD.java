package com.simon.tdd;

import java.util.HashMap;

public class MaximumSumOfDistinctSubarraysWithLengthKTDD {



    public long maximumSubarraySum(int[] nums, int k) { //20250804 self

        long maxSum = 0;
        long curSum = 0;

        // 各不相同 k = hash.size();
        HashMap<Long, Integer> cmap = new HashMap<>();

        for (int i = 0; i < k; i++) {
            long n = nums[i];
            curSum += n;
            cmap.put(n, cmap.getOrDefault(n, 0) + 1);
        }
        if (cmap.size() == k) { // 我一共只放了k个元素，cmap.size() <= k 恒为真，所以不必考虑更复杂的“各不相同”的算法
            maxSum = curSum;
        }

        for (int i = k; i < nums.length; i++) {
            long left = nums[i - k];
            long right = nums[i];
            curSum = curSum - left + right;
            cmap.put(left, cmap.get(left) - 1);
            if (cmap.get(left) == 0) {
                cmap.remove(left);
            }

            cmap.put(right, cmap.getOrDefault(right, 0) + 1); // 右侧可能有新值，新值第一次添加到map必须要0值，故用getOrDefault
            if (cmap.size() == k) {
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
