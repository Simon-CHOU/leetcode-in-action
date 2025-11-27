package com.simon.hot100;

import java.util.Arrays;

/**
 * 494. Target Sum Solution using Dynamic Programming.
 * <p>
 * Problem Analysis:
 * This problem can be transformed into a classic "0/1 Knapsack Problem" or "Subset Sum Problem".
 * We need to divide the array nums into two subsets: P (positive signs) and N (negative signs).
 * <p>
 * Mathematical Derivation:
 * <pre>
 * sum(P) - sum(N) = target
 * sum(P) + sum(N) = sum(nums)
 * </pre>
 * Adding the two equations:
 * <pre>
 * 2 * sum(P) = target + sum(nums)
 * sum(P) = (target + sum(nums)) / 2
 * </pre>
 * <p>
 * Conclusion:
 * Find a subset P such that its sum is (target + sum(nums)) / 2.
 * Let this required sum be `bagSize`.
 * </p>
 */
public class TargetSumTDD {

    /**
     * Calculates the number of ways to assign symbols to make the sum of integers equal to the target.
     * <p>
     * Time Complexity: O(n * m), where n is the number of elements and m is the size of the bag ((sum + target) / 2).
     * Space Complexity: O(m), for the 1D dp array.
     * </p>
     *
     * @param nums   The array of non-negative integers.
     * @param target The target sum.
     * @return The number of different ways to assign symbols.
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // Edge case 1: If absolute target is greater than sum, impossible.
        if (Math.abs(target) > sum) {
            return 0;
        }

        // Edge case 2: If (target + sum) is odd, impossible to divide by 2 (sums are integers).
        // Edge case 3: If bagSize is negative, impossible (nums are non-negative).
        if ((target + sum) % 2 != 0 || (target + sum) < 0) {
            return 0;
        }

        int bagSize = (target + sum) / 2;

        // dp[j] represents the number of ways to fill a bag of capacity j.
        int[] dp = new int[bagSize + 1];

        // Base case: There is 1 way to fill a bag of capacity 0 (by choosing nothing).
        dp[0] = 1;

        // Iterate through each number in nums (items)
        for (int num : nums) {
            // Iterate through bag capacities backwards to avoid using the same item multiple times
            for (int j = bagSize; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[bagSize];
    }

    /**
     * Main method for TDD verification.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        TargetSumTDD solution = new TargetSumTDD();
        System.out.println("Starting TDD for TargetSumTDD...");

        // Test Case 1: Standard case from LeetCode
        // Input: nums = [1,1,1,1,1], target = 3
        // Output: 5
        runTest(solution, "Standard Case", new int[]{1, 1, 1, 1, 1}, 3, 5);

        // Test Case 2: Target = 1, nums = [1]
        // Output: 1
        runTest(solution, "Single Element Case", new int[]{1}, 1, 1);

        // Test Case 3: Target cannot be reached (parity mismatch)
        // nums = [1, 2], sum = 3. target = 2. (3+2)/2 = 2.5 -> impossible
        runTest(solution, "Parity Mismatch Case", new int[]{1, 2}, 2, 0);

        // Test Case 4: Target larger than sum
        // nums = [1, 1], sum = 2. target = 3.
        runTest(solution, "Target > Sum Case", new int[]{1, 1}, 3, 0);
        
        // Test Case 5: Zero in nums
        // nums = [0,0,0,0,0,0,0,0,1], target = 1
        // This tests if 0s are handled correctly (they increase combinations).
        // 2^8 * 1 = 256 ways.
        runTest(solution, "Zeros in Input Case", new int[]{0,0,0,0,0,0,0,0,1}, 1, 256);

        System.out.println("All tests completed.");
    }

    private static void runTest(TargetSumTDD solution, String testName, int[] nums, int target, int expected) {
        System.out.printf("Test: %-20s | Input: nums=%s, target=%d | Expected: %d ... ", 
            testName, Arrays.toString(nums), target, expected);
        
        int result = solution.findTargetSumWays(nums, target);
        
        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL (Got: " + result + ")");
        }
    }
}
