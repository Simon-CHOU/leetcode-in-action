package com.simon.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array.
 * <p>
 * Problem Summary: Given an array nums of size n containing numbers in the range [1, n],
 * some numbers appear twice and others once. Return all numbers in the range [1, n] that do not appear in nums.
 * <p>
 * Key Idea: Use in-place marking to record appearance. For each value x, mark nums[|x|-1] negative.
 * After marking, indices with positive values represent missing numbers (index + 1).
 * <p>
 * Complexity: Time O(n), Space O(1) excluding the returned list.
 */
public class FindAllNumbersDisappearedInAnArrayTDD {

    /**
     * Returns all numbers in [1, n] that do not appear in the input array.
     * <p>
     * This method mutates the input array by using sign marking; if mutation is undesired, clone the array before use.
     * <p>
     * Algorithm:
     * <p>1) Iterate nums: for each value v, compute index = |v| - 1 and set nums[index] = -|nums[index]|.
     * <p>2) Iterate indices 0..n-1: if nums[i] > 0, then (i+1) is missing.
     *
     * @param nums The input array with values in [1, n].
     * @return A list of missing numbers in ascending order.
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    /**
     * Entry point for TDD verification without external test frameworks.
     * <p>
     * Prints PASS/FAIL logs for multiple scenarios.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArrayTDD solution = new FindAllNumbersDisappearedInAnArrayTDD();
        System.out.println("Starting TDD for FindAllNumbersDisappearedInAnArrayTDD...");

        runTest(solution, "LeetCode Example", new int[]{4, 3, 2, 7, 8, 2, 3, 1}, Arrays.asList(5, 6));
        runTest(solution, "All Present", new int[]{1, 2, 3, 4, 5}, List.of());
        runTest(solution, "Single Missing", new int[]{1, 1, 2, 3, 4}, List.of(5));
        runTest(solution, "Single Element Present", new int[]{1}, List.of());
        runTest(solution, "Duplicate Ones", new int[]{1, 1}, List.of(2));
        runTest(solution, "Missing Last", new int[]{1, 2, 3, 4, 5, 5}, List.of(6));
        runTest(solution, "Empty Array", new int[]{}, List.of());

        System.out.println("All tests completed.");
    }

    /**
     * Runs a single test case and prints PASS/FAIL.
     * <p>
     * Compares the returned list with the expected list by value and order.
     *
     * @param solution The solution instance.
     * @param name     The test name.
     * @param nums     The input array.
     * @param expected The expected missing numbers list.
     */
    private static void runTest(FindAllNumbersDisappearedInAnArrayTDD solution, String name, int[] nums, List<Integer> expected) {
        List<Integer> copyExpected = new ArrayList<>(expected);
        List<Integer> result = solution.findDisappearedNumbers(Arrays.copyOf(nums, nums.length));
        boolean pass = result.equals(copyExpected);
        System.out.printf("Test: %-20s | Input: nums=%s | Expected: %s | Got: %s ... %s\n",
                name, Arrays.toString(nums), copyExpected, result, pass ? "PASS" : "FAIL");
    }
}
