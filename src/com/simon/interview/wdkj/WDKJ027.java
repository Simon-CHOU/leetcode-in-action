package com.simon.interview.wdkj;

import java.util.Random;

/**
 * 一千万个数求和问题解决方案
 * <p>
 * 问题分析：
 * 1. 数据规模：一千万个数字，需要考虑内存和性能
 * 2. 数据类型选择：根据数据范围选择合适的数据类型
 * 3. 精度要求：考虑是否需要处理超出基本数据类型范围的数值
 * <p>
 * 解决方案：
 * 1. 对于普通整数求和，使用long类型避免溢出
 * 2. 对于超大整数，使用BigInteger处理
 * 3. 对于浮点数，需要考虑精度问题
 */
public class WDKJ027 {
    
    /**
     * 对整数数组进行求和
     * <p>
     * 算法思路：
     * 1. 遍历数组中的每个元素
     * 2. 使用long类型累加避免整数溢出
     * 3. 返回求和结果
     * <p>
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
     * 对大整数数组进行求和（处理可能超出long范围的数值）
     * <p>
     * 算法思路：
     * 1. 使用BigInteger处理大整数
     * 2. 遍历数组中的每个元素并累加
     * <p>
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
     * 算法执行过程演示（ASCII图示）
     * <p>
     * 假设数组为 [1, 2, 3, 4, 5]，求和过程如下：
     * <p>
     * 初始状态:
     * sum = 0
     * array: [1,  2,  3,  4,  5]
     * index:  0   1   2   3   4
     *         ^
     *         i
     * <p>
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
     * <p>
     * 第3步: sum = 3 + 3 = 6
     * array: [1,  2,  3,  4,  5]
     * index:  0   1   2   3   4
     *                     ^
     *                     i
     * <p>
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
        
        // 测试用例5: 一千万个数求和性能测试（使用较小规模模拟）
        System.out.println("\n测试用例5: 性能测试（100万个数）");
        int[] largeArray = solution.generateTestData(1000000);
        long startTime = System.currentTimeMillis();
        long result5 = solution.sum(largeArray);
        long endTime = System.currentTimeMillis();
        System.out.println("结果: " + result5);
        System.out.println("耗时: " + (endTime - startTime) + " 毫秒 " + (endTime - startTime < 1000 ? "PASS" : "FAIL"));
        
        // 测试BigInteger版本
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
    }
}