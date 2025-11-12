package com.simon.hot100;

import java.util.Random;

/**
 * LeetCode 215: 数组中的第K个最大元素
 * <p>
 * 使用快速选择算法（QuickSelect）解决，这是快速排序的变种，
 * 专门用于在未排序数组中找到第K大/小的元素。
 * <p>
 * 算法核心思想：
 * 1. 分治法：通过分区操作将数组分为两部分
 * 2. 剪枝：只在包含目标元素的一侧继续搜索
 * 3. 随机化：随机选择pivot避免最坏情况
 * <p>
 * 时间复杂度：平均O(n)，最坏O(n²)
 * 空间复杂度：O(1)
 *
 * @author Simon
 * @version 1.0
 */
public class KthLargestElementInAnArrayTDD {
    
    private final Random random = new Random();

    /**
     * 找到数组中第K个最大的元素
     * <p>
     * 使用快速选择算法，将问题转换为找到第(n-k)小的元素，
     * 其中n是数组长度。这样可以直接使用标准的快速选择实现。
     *
     * @param nums 输入数组，包含整数元素
     * @param k 要找的第K大元素的位置（1 ≤ k ≤ nums.length）
     * @return 第K个最大的元素值
     * @throws IllegalArgumentException 如果k超出有效范围
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        
        // 第K大元素 = 第(n-k)小元素（0-indexed）
        int targetIndex = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }

    /**
     * 快速选择算法的核心实现
     * <p>
     * 通过分区操作将数组分为两部分：小于pivot和大于等于pivot的元素。
     * 根据目标索引位置决定在哪一侧继续搜索。
     *
     * @param nums 待搜索的数组
     * @param left 搜索范围的左边界（包含）
     * @param right 搜索范围的右边界（包含）
     * @param targetIndex 目标元素在排序后数组中的索引位置
     * @return 目标位置的元素值
     */
    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left == right) {
            return nums[left];
        }
        
        // 随机选择pivot避免最坏情况
        int pivotIndex = partition(nums, left, right);
        
        if (pivotIndex == targetIndex) {
            return nums[pivotIndex];
        } else if (pivotIndex < targetIndex) {
            // 目标在右半部分
            return quickSelect(nums, pivotIndex + 1, right, targetIndex);
        } else {
            // 目标在左半部分
            return quickSelect(nums, left, pivotIndex - 1, targetIndex);
        }
    }

    /**
     * 分区操作：将数组分为小于pivot和大于等于pivot的两部分
     * <p>
     * 使用Lomuto分区方案：
     * 1. 随机选择pivot并移到末尾
     * 2. 维护一个指针i，指向小于pivot的区域的末尾
     * 3. 遍历数组，将小于pivot的元素交换到左侧
     * 4. 最后将pivot放到正确位置
     *
     * @param nums 待分区的数组
     * @param left 分区范围的左边界（包含）
     * @param right 分区范围的右边界（包含）
     * @return pivot元素在分区后的最终位置
     */
    private int partition(int[] nums, int left, int right) {
        // 随机选择pivot并移到末尾
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums, randomIndex, right);
        
        int pivot = nums[right];
        int i = left; // 小于pivot区域的末尾指针
        
        // 将小于pivot的元素移到左侧
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        
        // 将pivot放到正确位置
        swap(nums, i, right);
        return i;
    }

    /**
     * 交换数组中两个位置的元素
     *
     * @param nums 数组
     * @param i 第一个位置
     * @param j 第二个位置
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * TDD测试方法：使用main方法创建测试用例
     * <p>
     * 测试覆盖：
     * 1. 基本功能测试
     * 2. 边界条件测试
     * 3. 异常情况测试
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        KthLargestElementInAnArrayTDD solution = new KthLargestElementInAnArrayTDD();
        
        System.out.println("=== LeetCode 215: 数组中的第K个最大元素 - TDD测试 ===\n");
        
        // 测试用例1：基本功能测试
        testCase1(solution);
        
        // 测试用例2：单元素数组
        testCase2(solution);
        
        // 测试用例3：所有元素相同
        testCase3(solution);
        
        // 测试用例4：已排序数组
        testCase4(solution);
        
        // 测试用例5：逆序数组
        testCase5(solution);
        
        // 测试用例6：边界条件测试
        testCase6(solution);
        
        // 测试用例7：异常情况测试
        testCase7(solution);
        
        System.out.println("=== 所有测试完成 ===");
    }

    private static void testCase1(KthLargestElementInAnArrayTDD solution) {
        System.out.println("测试用例1: 基本功能测试");
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int expected1 = 5;
        int result1 = solution.findKthLargest(nums1, k1);
        System.out.println("输入: [3,2,1,5,6,4], k=2");
        System.out.println("期望: " + expected1 + ", 实际: " + result1);
        System.out.println("结果: " + (result1 == expected1 ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testCase2(KthLargestElementInAnArrayTDD solution) {
        System.out.println("测试用例2: 单元素数组");
        int[] nums2 = {1};
        int k2 = 1;
        int expected2 = 1;
        int result2 = solution.findKthLargest(nums2, k2);
        System.out.println("输入: [1], k=1");
        System.out.println("期望: " + expected2 + ", 实际: " + result2);
        System.out.println("结果: " + (result2 == expected2 ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testCase3(KthLargestElementInAnArrayTDD solution) {
        System.out.println("测试用例3: 所有元素相同");
        int[] nums3 = {2, 2, 2, 2};
        int k3 = 3;
        int expected3 = 2;
        int result3 = solution.findKthLargest(nums3, k3);
        System.out.println("输入: [2,2,2,2], k=3");
        System.out.println("期望: " + expected3 + ", 实际: " + result3);
        System.out.println("结果: " + (result3 == expected3 ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testCase4(KthLargestElementInAnArrayTDD solution) {
        System.out.println("测试用例4: 已排序数组");
        int[] nums4 = {1, 2, 3, 4, 5};
        int k4 = 2;
        int expected4 = 4;
        int result4 = solution.findKthLargest(nums4, k4);
        System.out.println("输入: [1,2,3,4,5], k=2");
        System.out.println("期望: " + expected4 + ", 实际: " + result4);
        System.out.println("结果: " + (result4 == expected4 ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testCase5(KthLargestElementInAnArrayTDD solution) {
        System.out.println("测试用例5: 逆序数组");
        int[] nums5 = {5, 4, 3, 2, 1};
        int k5 = 3;
        int expected5 = 3;
        int result5 = solution.findKthLargest(nums5, k5);
        System.out.println("输入: [5,4,3,2,1], k=3");
        System.out.println("期望: " + expected5 + ", 实际: " + result5);
        System.out.println("结果: " + (result5 == expected5 ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testCase6(KthLargestElementInAnArrayTDD solution) {
        System.out.println("测试用例6: 边界条件测试");
        int[] nums6 = {7, 10, 4, 3, 20, 15};
        int k6_1 = 1; // 最大元素
        int k6_2 = 6; // 最小元素
        int expected6_1 = 20;
        int expected6_2 = 3;
        int result6_1 = solution.findKthLargest(nums6.clone(), k6_1);
        int result6_2 = solution.findKthLargest(nums6.clone(), k6_2);
        
        System.out.println("输入: [7,10,4,3,20,15], k=1 (最大)");
        System.out.println("期望: " + expected6_1 + ", 实际: " + result6_1);
        System.out.println("结果: " + (result6_1 == expected6_1 ? "PASS" : "FAIL"));
        
        System.out.println("输入: [7,10,4,3,20,15], k=6 (最小)");
        System.out.println("期望: " + expected6_2 + ", 实际: " + result6_2);
        System.out.println("结果: " + (result6_2 == expected6_2 ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testCase7(KthLargestElementInAnArrayTDD solution) {
        System.out.println("测试用例7: 异常情况测试");
        
        // 测试null数组
        try {
            solution.findKthLargest(null, 1);
            System.out.println("null数组测试: FAIL (应该抛出异常)");
        } catch (IllegalArgumentException e) {
            System.out.println("null数组测试: PASS (正确抛出异常)");
        }
        
        // 测试空数组
        try {
            solution.findKthLargest(new int[0], 1);
            System.out.println("空数组测试: FAIL (应该抛出异常)");
        } catch (IllegalArgumentException e) {
            System.out.println("空数组测试: PASS (正确抛出异常)");
        }
        
        // 测试k超出范围
        try {
            solution.findKthLargest(new int[]{1, 2, 3}, 5);
            System.out.println("k超出范围测试: FAIL (应该抛出异常)");
        } catch (IllegalArgumentException e) {
            System.out.println("k超出范围测试: PASS (正确抛出异常)");
        }
        System.out.println();
    }
}
