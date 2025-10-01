/**
 * 解决LeetCode 167题：两数之和 II - 输入有序数组
 * 使用双指针算法高效求解
 * 
 * @author Simon
 */
package com.simon.tdd;

class TwoSum2InputArrayIsSorted {
    /**
     * 在有序数组中找出和为目标值的两个数，并返回它们的索引（索引从1开始计数）
     * 使用双指针技术实现O(n)时间复杂度
     * 
     * @param numbers 已排序的整数数组
     * @param target 目标和值
     * @return 包含两个整数索引的数组
     */
    public int[] twoSum(int[] numbers, int target) {
        // 初始化左右指针，分别指向数组开头和结尾
        int left = 0;
        int right = numbers.length - 1;
        
        // 当左指针小于右指针时继续搜索
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            
            if (sum == target) {
                // 找到目标值，返回索引（注意索引从1开始）
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                // 两数之和小于目标值，左指针右移（增大和）
                left++;
            } else {
                // 两数之和大于目标值，右指针左移（减小和）
                right--;
            }
        }
        
        // 题目保证有解，所以不会执行到这里
        return new int[] {-1, -1};
    }

    /**
     * 主方法，用于测试twoSum函数
     * 使用TDD模式进行测试验证
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        TwoSum2InputArrayIsSorted solution = new TwoSum2InputArrayIsSorted();
        
        // 测试用例1: [2,7,11,15], target=9
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] expected1 = {1, 2};
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("Test 1: " + (isEqual(result1, expected1) ? "PASS" : "FAIL"));
        
        // 测试用例2: [2,3,4], target=6
        int[] nums2 = {2, 3, 4};
        int target2 = 6;
        int[] expected2 = {1, 3};
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("Test 2: " + (isEqual(result2, expected2) ? "PASS" : "FAIL"));
        
        // 测试用例3: [-1,0], target=-1
        int[] nums3 = {-1, 0};
        int target3 = -1;
        int[] expected3 = {1, 2};
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("Test 3: " + (isEqual(result3, expected3) ? "PASS" : "FAIL"));
        
        // ASCII图示说明算法过程
        visualizeAlgorithm();
        
        // 时间复杂度分析
        analyzeTimeComplexity();
    }
    
    /**
     * 比较两个数组是否相等
     * 
     * @param a 第一个数组
     * @param b 第二个数组
     * @return 如果两个数组元素相同则返回true，否则返回false
     */
    private static boolean isEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
    
    /**
     * 可视化双指针算法的执行过程
     */
    private static void visualizeAlgorithm() {
        System.out.println("\n算法执行过程可视化：");
        System.out.println("示例: nums = [2,7,11,15], target = 9");
        System.out.println("初始状态:");
        System.out.println("  [2, 7, 11, 15]");
        System.out.println("   ↑           ↑");
        System.out.println("  left=0      right=3");
        System.out.println("sum = 2 + 15 = 17 > 9，right左移");
        System.out.println("\n下一步:");
        System.out.println("  [2, 7, 11, 15]");
        System.out.println("   ↑      ↑");
        System.out.println("  left=0  right=2");
        System.out.println("sum = 2 + 11 = 13 > 9，right左移");
        System.out.println("\n下一步:");
        System.out.println("  [2, 7, 11, 15]");
        System.out.println("   ↑  ↑");
        System.out.println("  left=0 right=1");
        System.out.println("sum = 2 + 7 = 9 = target，找到解");
        System.out.println("返回结果: [1, 2] (索引从1开始)");
    }
    
    /**
     * 分析算法的时间复杂度
     */
    private static void analyzeTimeComplexity() {
        System.out.println("\n时间复杂度分析：");
        System.out.println("1. 使用双指针法，指针left和right最多遍历数组一次");
        System.out.println("2. while循环的条件是left < right，每次循环至少移动一个指针");
        System.out.println("3. 最坏情况下，需要进行n-1次比较和指针移动操作");
        System.out.println("4. 因此，时间复杂度为O(n)，其中n是数组的长度");
        System.out.println("\n空间复杂度分析：");
        System.out.println("只使用了常数额外空间，因此空间复杂度为O(1)");
    }
}