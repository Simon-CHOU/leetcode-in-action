package com.simon.interview.wdkj;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 一千万个数求和问题解决方案
 * 
 * 问题分析：
 * 1. 数据规模：一千万个数字，需要考虑内存和性能
 * 2. 数据类型选择：根据数据范围选择合适的数据类型
 * 3. 精度要求：考虑是否需要处理超出基本数据类型范围的数值
 * 
 * 解决方案：
 * 1. 对于普通整数求和，使用long类型避免溢出
 * 2. 对于超大整数，使用BigInteger处理
 * 3. 对于浮点数，需要考虑精度问题
 */
public class WDKJ027 {
    
    /**
     * 对整数数组进行求和
     * 
     * 算法思路：
     * 1. 遍历数组中的每个元素
     * 2. 使用long类型累加避免整数溢出
     * 3. 返回求和结果
     * 
     * 时间复杂度：O(n) - 需要遍历所有元素
     * 空间复杂度：O(1) - 只使用常数额外空间
     * 
     * @param numbers 待求和的整数数组
     * @return 求和结果
     */
    public long sum(int[] numbers) {
        long sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
    
    /**
     * 对大整数数组进行求和（单线程版本）
     * 
     * 算法思路：
     * 1. 使用BigInteger处理大整数
     * 2. 遍历数组中的每个元素并累加
     * 
     * 时间复杂度：O(n) - 需要遍历所有元素
     * 空间复杂度：O(1) - 只使用常数额外空间（不考虑BigInteger内部存储）
     * 
     * @param numbers 待求和的大整数数组
     * @return 求和结果
     */
    public java.math.BigInteger sum(java.math.BigInteger[] numbers) {
        java.math.BigInteger sum = java.math.BigInteger.ZERO;
        for (java.math.BigInteger number : numbers) {
            sum = sum.add(number);
        }
        return sum;
    }
    
    /**
     * 对大整数数组进行求和（多线程并行版本）
     * 
     * 算法思路：
     * 1. 使用Fork/Join框架将大数组分割成小块
     * 2. 并行计算各块的和
     * 3. 合并各块结果得到最终结果
     * 
     * 时间复杂度：O(n/p) - 其中p为并行线程数
     * 空间复杂度：O(1) - 只使用常数额外空间（不考虑BigInteger内部存储）
     * 
     * @param numbers 待求和的大整数数组
     * @return 求和结果
     */
    public java.math.BigInteger parallelSum(java.math.BigInteger[] numbers) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        try {
            return forkJoinPool.invoke(new BigIntegerSumTask(numbers, 0, numbers.length));
        } finally {
            forkJoinPool.shutdown();
        }
    }
    
    /**
     * Fork/Join任务类，用于并行计算BigInteger数组部分和
     */
    private static class BigIntegerSumTask extends RecursiveTask<java.math.BigInteger> {
        private static final int THRESHOLD = 10000; // 阈值，小于此值时直接计算
        private final java.math.BigInteger[] numbers;
        private final int start;
        private final int end;
        
        public BigIntegerSumTask(java.math.BigInteger[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected java.math.BigInteger compute() {
            // 如果任务足够小则直接计算
            if (end - start <= THRESHOLD) {
                java.math.BigInteger sum = java.math.BigInteger.ZERO;
                for (int i = start; i < end; i++) {
                    sum = sum.add(numbers[i]);
                }
                return sum;
            } else {
                // 否则分割任务
                int mid = (start + end) / 2;
                BigIntegerSumTask leftTask = new BigIntegerSumTask(numbers, start, mid);
                BigIntegerSumTask rightTask = new BigIntegerSumTask(numbers, mid, end);
                
                // 并行执行子任务
                leftTask.fork();
                java.math.BigInteger rightResult = rightTask.compute();
                java.math.BigInteger leftResult = leftTask.join();
                
                // 合并结果
                return leftResult.add(rightResult);
            }
        }
    }
    
    /**
     * 生成测试数据
     * 
     * @param size 数组大小
     * @return 随机整数数组
     */
    public int[] generateTestData(int size) {
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(1000); // 生成0-999的随机数
        }
        return data;
    }
    
    /**
     * 生成大整数测试数据
     * 
     * @param size 数组大小
     * @return 随机大整数数组
     */
    public java.math.BigInteger[] generateBigTestData(int size) {
        java.math.BigInteger[] data = new java.math.BigInteger[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            // 生成随机大整数
            data[i] = new java.math.BigInteger(64, random);
        }
        return data;
    }
    
    /**
     * 算法执行过程演示（ASCII图示）
     * 
     * 假设数组为 [1, 2, 3, 4, 5]，求和过程如下：
     * 
     * 初始状态:
     * sum = 0
     * array: [1,  2,  3,  4,  5]
     * index:  0   1   2   3   4
     *         ^
     *         i
     * 
     * 第1步: sum = 0 + 1 = 1
     * array: [1,  2,  3,  4,  5]
     * index:  0   1   2   3   4
     *             ^
     *             i
     * 
     * 第2步: sum = 1 + 2 = 3
     * array: [1,  2,  3,  4,  5]
     * index:  0   1   2   3   4
     *                 ^
     *                 i
     * 
     * 第3步: sum = 3 + 3 = 6
     * array: [1,  2,  3,  4,  5]
     * index:  0   1   2   3   4
     *                     ^
     *                     i
     * 
     * 第4步: sum = 6 + 4 = 10
     * array: [1,  2,  3,  4,  5]
     * index:  0   1   2   3   4
     *                         ^
     *                         i
     * 
     * 第5步: sum = 10 + 5 = 15
     * array: [1,  2,  3,  4,  5]
     * index:  0   1   2   3   4
     *                             ^
     *                             i (越界，循环结束)
     * 
     * 最终结果: sum = 15
     * 
     * 并行求和过程演示（以8个元素为例）:
     * 
     * 初始数组: [1, 2, 3, 4, 5, 6, 7, 8]
     * 
     * 第一层分割:
     * [1, 2, 3, 4]     [5, 6, 7, 8]
     *       |                 |
     *    sum=10            sum=26
     * 
     * 第二层分割:
     * [1, 2]  [3, 4]   [5, 6]  [7, 8]
     *   |       |        |       |
     * sum=3   sum=7    sum=11  sum=15
     * 
     * 合并结果: 3+7=10, 11+15=26, 10+26=36
     * 
     * 最终结果: sum = 36
     */
    
    public static void main(String[] args) {
        WDKJ027 solution = new WDKJ027();
        
        // 测试用例1: 小规模数据测试
        System.out.println("测试用例1: 小规模数据测试");
        int[] testArray1 = {1, 2, 3, 4, 5};
        long result1 = solution.sum(testArray1);
        System.out.println("结果: " + result1 + " (期望: 15) " + (result1 == 15 ? "PASS" : "FAIL"));
        
        // 测试用例2: 包含负数的测试
        System.out.println("\n测试用例2: 包含负数的测试");
        int[] testArray2 = {10, -5, 3, -2, 4};
        long result2 = solution.sum(testArray2);
        System.out.println("结果: " + result2 + " (期望: 10) " + (result2 == 10 ? "PASS" : "FAIL"));
        
        // 测试用例3: 大数测试
        System.out.println("\n测试用例3: 大数测试");
        int[] testArray3 = {1000000, 2000000, 3000000};
        long result3 = solution.sum(testArray3);
        System.out.println("结果: " + result3 + " (期望: 6000000) " + (result3 == 6000000 ? "PASS" : "FAIL"));
        
        // 测试用例4: 空数组测试
        System.out.println("\n测试用例4: 空数组测试");
        int[] testArray4 = {};
        long result4 = solution.sum(testArray4);
        System.out.println("结果: " + result4 + " (期望: 0) " + (result4 == 0 ? "PASS" : "FAIL"));
        
        // 测试用例5: 性能测试（100万个数）
        System.out.println("\n测试用例5: 性能测试（100万个数）");
        int[] largeArray = solution.generateTestData(1000000);
        long startTime = System.currentTimeMillis();
        long result5 = solution.sum(largeArray);
        long endTime = System.currentTimeMillis();
        System.out.println("结果: " + result5);
        System.out.println("耗时: " + (endTime - startTime) + " 毫秒 " + (endTime - startTime < 1000 ? "PASS" : "FAIL"));
        
        // 测试用例6: BigInteger求和测试
        System.out.println("\n测试用例6: BigInteger求和测试");
        java.math.BigInteger[] bigNumbers = {
            new java.math.BigInteger("123456789012345678901234567890"),
            new java.math.BigInteger("987654321098765432109876543210"),
            new java.math.BigInteger("100000000000000000000000000000")
        };
        java.math.BigInteger bigResult = solution.sum(bigNumbers);
        java.math.BigInteger expected = new java.math.BigInteger("1211111110111111111011111111100");
        System.out.println("结果: " + bigResult + " (期望: " + expected + ") " + 
                          (bigResult.equals(expected) ? "PASS" : "FAIL"));
        
        // 测试用例7: 并行BigInteger求和测试
        System.out.println("\n测试用例7: 并行BigInteger求和测试");
        java.math.BigInteger[] bigNumbers2 = {
            new java.math.BigInteger("123456789012345678901234567890"),
            new java.math.BigInteger("987654321098765432109876543210"),
            new java.math.BigInteger("100000000000000000000000000000")
        };
        java.math.BigInteger bigResult2 = solution.parallelSum(bigNumbers2);
        System.out.println("结果: " + bigResult2 + " (期望: " + expected + ") " + 
                          (bigResult2.equals(expected) ? "PASS" : "FAIL"));
        
        // 测试用例8: 大规模BigInteger性能对比测试
        System.out.println("\n测试用例8: 大规模BigInteger性能对比测试（10万个大整数）");
        java.math.BigInteger[] largeBigArray = solution.generateBigTestData(100000);
        
        // 单线程版本测试
        long startTime1 = System.currentTimeMillis();
        java.math.BigInteger singleThreadResult = solution.sum(largeBigArray);
        long endTime1 = System.currentTimeMillis();
        long duration1 = endTime1 - startTime1;
        
        // 多线程版本测试
        long startTime2 = System.currentTimeMillis();
        java.math.BigInteger multiThreadResult = solution.parallelSum(largeBigArray);
        long endTime2 = System.currentTimeMillis();
        long duration2 = endTime2 - startTime2;
        
        boolean resultsEqual = singleThreadResult.equals(multiThreadResult);
        
        System.out.println("单线程版本:");
        System.out.println("  结果长度: " + singleThreadResult.toString().length() + " 位数字");
        System.out.println("  耗时: " + duration1 + " 毫秒");
        
        System.out.println("多线程版本:");
        System.out.println("  结果长度: " + multiThreadResult.toString().length() + " 位数字");
        System.out.println("  耗时: " + duration2 + " 毫秒");
        
        System.out.println("结果一致性: " + (resultsEqual ? "PASS" : "FAIL"));
        System.out.println("性能提升: " + (duration1 > duration2 ? "多线程版本更快" : "单线程版本更快或相当") + 
                          " (" + Math.abs(duration1 - duration2) + " 毫秒差异)");
    }
}