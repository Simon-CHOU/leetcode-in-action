package com.simon.hot100;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
 * 你可以按 任意顺序 返回答案。
 * <p>
 * 解题思路：
 * 1. 使用哈希表统计每个元素出现的频率
 * 2. 使用小根堆维护出现频率最高的 K 个元素
 * 3. 当堆大小超过 K 时，移除堆顶元素（频率最小的元素）
 * 4. 最终堆中剩下的就是出现频率最高的 K 个元素
 */
public class TopKFrequentElementsTDD {
    
    /**
     * 返回出现频率前 k 高的元素
     * <p>
     * 算法步骤：
     * 1. 统计元素频率：O(n)
     * 2. 构建小根堆：O(n log k)
     * 3. 提取结果：O(k)
     *
     * @param nums 输入数组
     * @param k    返回前 k 个高频元素
     * @return 频率前 k 高的元素数组
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 边界条件检查
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        // 步骤1: 统计每个元素出现的频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // 步骤2: 创建小根堆，按元素频率排序
        // 注意：优先队列默认是小根堆，我们自定义比较器按频率排序
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        
        // 步骤3: 遍历频率映射，维护小根堆大小为k
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int num = entry.getKey();
            int frequency = entry.getValue();
            
            if (minHeap.size() < k) {
                // 堆未满，直接添加
                minHeap.offer(new int[]{num, frequency});
            } else if (frequency > minHeap.peek()[1]) {
                // 当前元素频率大于堆顶最小频率，替换堆顶
                minHeap.poll();
                minHeap.offer(new int[]{num, frequency});
            }
        }
        
        // 步骤4: 提取结果
        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll()[0];
        }
        
        return result;
    }
    
    /**
     * 主方法：测试用例
     */
    public static void main(String[] args) {
        TopKFrequentElementsTDD solution = new TopKFrequentElementsTDD();
        
        // 测试用例1: 常规情况
        testCase(solution, new int[]{1, 1, 1, 2, 2, 3}, 2, new int[]{1, 2}, "测试用例1: 常规情况");
        
        // 测试用例2: k等于数组中不同元素的数量
        testCase(solution, new int[]{1, 2}, 2, new int[]{1, 2}, "测试用例2: k等于数组中不同元素的数量");
        
        // 测试用例3: 所有元素都相同
        testCase(solution, new int[]{3, 3, 3, 3}, 1, new int[]{3}, "测试用例3: 所有元素都相同");
        
        // 测试用例4: 单元素数组
        testCase(solution, new int[]{1}, 1, new int[]{1}, "测试用例4: 单元素数组");
        
        // 测试用例5: k=1，找出现频率最高的元素
        testCase(solution, new int[]{4, 1, -1, 2, -1, 2, 3}, 2, new int[]{-1, 2}, "测试用例5: k=2，含负数");
    }
    
    /**
     * 执行测试用例并打印结果
     *
     * @param solution 解决方案对象
     * @param nums     输入数组
     * @param k        前k个高频元素
     * @param expected 预期结果（不保证顺序）
     * @param testName 测试名称
     */
    private static void testCase(TopKFrequentElementsTDD solution, int[] nums, int k, int[] expected, String testName) {
        int[] result = solution.topKFrequent(nums, k);
        
        // 对结果和预期进行排序，以便比较（顺序不重要）
        Arrays.sort(result);
        Arrays.sort(expected);
        
        boolean passed = Arrays.equals(result, expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        
        if (!passed) {
            System.out.println("  期望: " + Arrays.toString(expected));
            System.out.println("  实际: " + Arrays.toString(result));
        }
    }
}
