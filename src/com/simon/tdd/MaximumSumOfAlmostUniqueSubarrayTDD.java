package com.simon.tdd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解决"几乎唯一子数组的最大和"问题
 * 
 * 算法思路:
 * 1. 使用滑动窗口技术遍历所有长度为k的子数组
 * 2. 使用HashMap维护窗口内每个元素的出现次数
 * 3. 当窗口内不同元素个数 >= m时，计算窗口元素和并更新最大值
 * 
 * 时间复杂度: O(n)，其中n是数组长度
 * 空间复杂度: O(k)，哈希表最多存储k个不同的元素
 */
public class MaximumSumOfAlmostUniqueSubarrayTDD {

    /**
     * 计算满足条件的子数组的最大和
     * 
     * @param nums 输入的整数列表
     * @param m 子数组中至少需要的不同元素个数
     * @param k 子数组的长度
     * @return 满足条件的子数组的最大和，如果不存在满足条件的子数组则返回0
     */
    public long maxSum(List<Integer> nums, int m, int k) {
        int n = nums.size();
        long maxSum = 0;
        long windowSum = 0;
        
        // 使用HashMap记录窗口内每个元素的出现次数
        Map<Integer, Integer> elementCount = new HashMap<>();
        
        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            int num = nums.get(i);
            windowSum += num;
            elementCount.put(num, elementCount.getOrDefault(num, 0) + 1);
        }
        
        // 检查第一个窗口是否满足条件
        if (elementCount.size() >= m) { // nums 的一个子数组有至少 m 个互不相同的元素
            maxSum = windowSum;
        }
        
        // 滑动窗口，从第k个元素开始
        for (int i = k; i < n; i++) {
            // 添加新元素到窗口
            int newElement = nums.get(i);
            windowSum += newElement;
            elementCount.put(newElement, elementCount.getOrDefault(newElement, 0) + 1);
            
            // 移除窗口左侧的旧元素
            int oldElement = nums.get(i - k);
            windowSum -= oldElement;
            elementCount.put(oldElement, elementCount.get(oldElement) - 1); // 这里为什么不用getOrDefault？因为必然有值，左边的值是在前面的循环中我们亲手加进去的
            
            // 如果某个元素计数为0，则从哈希表中移除
            if (elementCount.get(oldElement) == 0) {
                elementCount.remove(oldElement);
            }
            
            // 检查当前窗口是否满足条件
            if (elementCount.size() >= m) {
                maxSum = Math.max(maxSum, windowSum);
            }
        }
        
        return maxSum;
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        MaximumSumOfAlmostUniqueSubarrayTDD solution = new MaximumSumOfAlmostUniqueSubarrayTDD();
        
        // 测试用例1
        // 输入: nums = [2,6,7,3,1,7], m = 3, k = 4
        // 输出: 18
        List<Integer> nums1 = List.of(2, 6, 7, 3, 1, 7);
        long result1 = solution.maxSum(nums1, 3, 4);
        long expected1 = 18;
        System.out.println("测试用例1结果: " + result1 + ", 期望: " + expected1);
        if (result1 == expected1) {
            System.out.println("测试用例1: 通过");
        } else {
            System.out.println("测试用例1: 失败");
        }
        
        // 测试用例2
        // 输入: nums = [5,9,9,2,4,5,4], m = 1, k = 3
        // 输出: 23
        List<Integer> nums2 = List.of(5, 9, 9, 2, 4, 5, 4);
        long result2 = solution.maxSum(nums2, 1, 3);
        long expected2 = 23;
        System.out.println("测试用例2结果: " + result2 + ", 期望: " + expected2);
        if (result2 == expected2) {
            System.out.println("测试用例2: 通过");
        } else {
            System.out.println("测试用例2: 失败");
        }
        
        // 测试用例3
        // 输入: nums = [1,2,1,2,6,7,5], m = 3, k = 4
        // 输出: 20
        List<Integer> nums3 = List.of(1, 2, 1, 2, 6, 7, 5);
        long result3 = solution.maxSum(nums3, 3, 4);
        long expected3 = 20;
        System.out.println("测试用例3结果: " + result3 + ", 期望: " + expected3);
        if (result3 == expected3) {
            System.out.println("测试用例3: 通过");
        } else {
            System.out.println("测试用例3: 失败");
        }
        
        // 测试用例4 - 不满足条件的情况
        // 输入: nums = [1,1,1,1], m = 2, k = 2
        // 输出: 0
        List<Integer> nums4 = List.of(1, 1, 1, 1);
        long result4 = solution.maxSum(nums4, 2, 2);
        long expected4 = 0;
        System.out.println("测试用例4结果: " + result4 + ", 期望: " + expected4);
        if (result4 == expected4) {
            System.out.println("测试用例4: 通过");
        } else {
            System.out.println("测试用例4: 失败");
        }
    }
}