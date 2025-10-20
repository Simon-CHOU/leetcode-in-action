package com.simon.tdd.monotonicstack;

import java.util.*;

/**
 * LeetCode 496: Next Greater Element I - 单调栈解法
 * <p>
 * 问题描述：给定两个没有重复元素的数组 nums1 和 nums2，其中 nums1 是 nums2 的子集。
 * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * 核心思想：使用单调递减栈预处理 nums2，建立每个元素到其下一个更大元素的映射关系。
 * <p>
 * 算法步骤：
 * 1. 遍历 nums2，维护一个单调递减栈
 * 2. 当遇到比栈顶更大的元素时，弹出栈顶并记录映射关系
 * 3. 将当前元素入栈
 * 4. 根据映射关系构造 nums1 的结果数组
 * 
 * @author Simon
 * @version 1.0
 */
public class NextGreaterElementITDD {

    /**
     * 使用单调栈解决下一个更大元素问题
     * <p>
     * 时间复杂度：O(m + n)，其中 m 是 nums1 的长度，n 是 nums2 的长度
     * 空间复杂度：O(n)，用于存储栈和哈希映射
     *
     * @param nums1 查询数组，是 nums2 的子集
     * @param nums2 目标数组，包含所有可能的元素
     * @return 结果数组，包含 nums1 中每个元素在 nums2 中的下一个更大元素
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 边界条件检查
        if (nums1 == null || nums2 == null || nums1.length == 0) {
            return new int[0];
        }
        
        // 使用哈希表存储每个元素到其下一个更大元素的映射
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        
        // 单调递减栈，存储尚未找到下一个更大元素的数字
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        
        // 遍历 nums2，构建下一个更大元素的映射关系
        for (int num : nums2) {
            // 当栈不为空且当前元素大于栈顶元素时
            // 说明找到了栈顶元素的下一个更大元素
            while (!monotonicStack.isEmpty() && num > monotonicStack.peek()) {
                int smallerElement = monotonicStack.pop();
                nextGreaterMap.put(smallerElement, num);
            }
            
            // 将当前元素入栈，等待寻找其下一个更大元素
            monotonicStack.push(num);
        }
        
        // 构造结果数组
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            // 如果映射中存在，则使用映射值；否则使用 -1 表示不存在
            result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }
        
        return result;
    }

    /**
     * 测试驱动开发 (TDD) 主方法
     * <p>
     * 包含多个测试用例验证算法正确性
     */
    public static void main(String[] args) {
        NextGreaterElementITDD solution = new NextGreaterElementITDD();
        
        System.out.println("=== LeetCode 496: Next Greater Element I - 单调栈解法测试 ===\n");
        
        // 测试用例1：基本功能测试
        testCase1(solution);
        
        // 测试用例2：无下一个更大元素
        testCase2(solution);
        
        // 测试用例3：边界条件测试
        testCase3(solution);
        
        // 测试用例4：单元素测试
        testCase4(solution);
        
        // 测试用例5：递增序列测试
        testCase5(solution);
        
        System.out.println("=== 所有测试完成 ===");
    }
    
    /**
     * 测试用例1：基本功能测试
     * nums1 = [4,1,2], nums2 = [1,3,4,2]
     * 预期结果：[-1,3,-1]
     */
    private static void testCase1(NextGreaterElementITDD solution) {
        System.out.println("测试用例1：基本功能测试");
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] expected = {-1, 3, -1};
        
        printAlgorithmTrace(nums2);
        
        int[] result = solution.nextGreaterElement(nums1, nums2);
        boolean passed = Arrays.equals(result, expected);
        
        System.out.println("输入: nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("预期: " + Arrays.toString(expected));
        System.out.println("实际: " + Arrays.toString(result));
        System.out.println("结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 测试用例2：无下一个更大元素
     * nums1 = [2,4], nums2 = [1,2,3,4]
     * 预期结果：[3,-1]
     */
    private static void testCase2(NextGreaterElementITDD solution) {
        System.out.println("测试用例2：无下一个更大元素");
        int[] nums1 = {2, 4};
        int[] nums2 = {1, 2, 3, 4};
        int[] expected = {3, -1};
        
        int[] result = solution.nextGreaterElement(nums1, nums2);
        boolean passed = Arrays.equals(result, expected);
        
        System.out.println("输入: nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("预期: " + Arrays.toString(expected));
        System.out.println("实际: " + Arrays.toString(result));
        System.out.println("结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 测试用例3：边界条件测试
     * nums1 = [], nums2 = [1,2,3]
     * 预期结果：[]
     */
    private static void testCase3(NextGreaterElementITDD solution) {
        System.out.println("测试用例3：边界条件测试");
        int[] nums1 = {};
        int[] nums2 = {1, 2, 3};
        int[] expected = {};
        
        int[] result = solution.nextGreaterElement(nums1, nums2);
        boolean passed = Arrays.equals(result, expected);
        
        System.out.println("输入: nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("预期: " + Arrays.toString(expected));
        System.out.println("实际: " + Arrays.toString(result));
        System.out.println("结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 测试用例4：单元素测试
     * nums1 = [1], nums2 = [1,2]
     * 预期结果：[2]
     */
    private static void testCase4(NextGreaterElementITDD solution) {
        System.out.println("测试用例4：单元素测试");
        int[] nums1 = {1};
        int[] nums2 = {1, 2};
        int[] expected = {2};
        
        int[] result = solution.nextGreaterElement(nums1, nums2);
        boolean passed = Arrays.equals(result, expected);
        
        System.out.println("输入: nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("预期: " + Arrays.toString(expected));
        System.out.println("实际: " + Arrays.toString(result));
        System.out.println("结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 测试用例5：递增序列测试
     * nums1 = [1,2,3], nums2 = [1,2,3,4]
     * 预期结果：[2,3,4]
     */
    private static void testCase5(NextGreaterElementITDD solution) {
        System.out.println("测试用例5：递增序列测试");
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {1, 2, 3, 4};
        int[] expected = {2, 3, 4};
        
        int[] result = solution.nextGreaterElement(nums1, nums2);
        boolean passed = Arrays.equals(result, expected);
        
        System.out.println("输入: nums1 = " + Arrays.toString(nums1) + ", nums2 = " + Arrays.toString(nums2));
        System.out.println("预期: " + Arrays.toString(expected));
        System.out.println("实际: " + Arrays.toString(result));
        System.out.println("结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 打印算法执行过程的ASCII图解
     * <p>
     * 演示单调栈在处理 nums2 = [1,3,4,2] 时的状态变化
     */
    private static void printAlgorithmTrace(int[] nums2) {
        System.out.println("算法执行过程可视化 (nums2 = " + Arrays.toString(nums2) + "):");
        System.out.println();
        
        System.out.println("步骤1: 处理元素 1");
        System.out.println("┌─────────────────┐");
        System.out.println("│ 单调栈 (递减)   │");
        System.out.println("├─────────────────┤");
        System.out.println("│       1         │ ← 栈顶");
        System.out.println("└─────────────────┘");
        System.out.println("映射表: {}");
        System.out.println();
        
        System.out.println("步骤2: 处理元素 3");
        System.out.println("3 > 1 (栈顶), 弹出1, 记录映射 1→3");
        System.out.println("┌─────────────────┐");
        System.out.println("│ 单调栈 (递减)   │");
        System.out.println("├─────────────────┤");
        System.out.println("│       3         │ ← 栈顶");
        System.out.println("└─────────────────┘");
        System.out.println("映射表: {1→3}");
        System.out.println();
        
        System.out.println("步骤3: 处理元素 4");
        System.out.println("4 > 3 (栈顶), 弹出3, 记录映射 3→4");
        System.out.println("┌─────────────────┐");
        System.out.println("│ 单调栈 (递减)   │");
        System.out.println("├─────────────────┤");
        System.out.println("│       4         │ ← 栈顶");
        System.out.println("└─────────────────┘");
        System.out.println("映射表: {1→3, 3→4}");
        System.out.println();
        
        System.out.println("步骤4: 处理元素 2");
        System.out.println("2 < 4 (栈顶), 直接入栈");
        System.out.println("┌─────────────────┐");
        System.out.println("│ 单调栈 (递减)   │");
        System.out.println("├─────────────────┤");
        System.out.println("│       2         │ ← 栈顶");
        System.out.println("│       4         │");
        System.out.println("└─────────────────┘");
        System.out.println("映射表: {1→3, 3→4}");
        System.out.println();
        
        System.out.println("最终结果构造:");
        System.out.println("nums1[0]=4 → 映射表中无4 → -1");
        System.out.println("nums1[1]=1 → 映射表中1→3 → 3");
        System.out.println("nums1[2]=2 → 映射表中无2 → -1");
        System.out.println("结果: [-1, 3, -1]");
        System.out.println();
    }
}
