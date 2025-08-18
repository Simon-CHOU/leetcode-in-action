package com.simon.tdd;

/**
 * 1052. 爱生气的书店老板
 * <p>
 * 问题描述：
 * 有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。
 * 给定一个长度为 n 的整数数组 customers，其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，
 * 所有这些顾客在第 i 分钟结束后离开。
 * 在某些时候，书店老板会生气。如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * 请返回这一天营业下来，最多有多少客户能够感到满意。
 * <p>
 * 示例：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 */
public class GrumpyBookstoreOwnerTDD {
    
    /**
     * 计算最多可获得的满意顾客数
     *
     * @param customers 顾客数量数组，customers[i] 表示第 i 分钟进入的顾客数量
     * @param grumpy    老板是否生气的数组，grumpy[i] = 1 表示生气，0 表示不生气
     * @param minutes   技能持续分钟数（连续不生气的时间）
     * @return 最大满意顾客数
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        // 计算基础满意顾客数（老板不使用技能时的满意顾客）
        int baseSatisfied = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                baseSatisfied += customers[i];
            }
        }
        
        // 使用滑动窗口找出使用技能能带来的最大额外满意顾客数
        int maxExtra;
        int currentExtra = 0;
        
        // 初始化第一个窗口
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                currentExtra += customers[i];
            }
        }
        maxExtra = currentExtra;
        
        // 滑动窗口更新额外满意顾客数
        for (int i = minutes; i < customers.length; i++) {
            // 移除窗口左边界的贡献
            if (grumpy[i - minutes] == 1) {
                currentExtra -= customers[i - minutes];
            }
            // 添加窗口右边界的贡献
            if (grumpy[i] == 1) {
                currentExtra += customers[i];
            }
            // 更新最大额外满意顾客数
            maxExtra = Math.max(maxExtra, currentExtra);
        }
        
        // 返回基础满意顾客数 + 使用技能带来的最大额外满意顾客数
        return baseSatisfied + maxExtra;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        GrumpyBookstoreOwnerTDD solution = new GrumpyBookstoreOwnerTDD();
        
        // 测试用例1：题目示例
        int[] customers1 = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy1 = {0, 1, 0, 1, 0, 1, 0, 1};
        int minutes1 = 3;
        int expected1 = 16;
        int result1 = solution.maxSatisfied(customers1, grumpy1, minutes1);
        printTestResult("Test 1 (示例)", expected1, result1);
        
        // 测试用例2：技能覆盖全时段
        int[] customers2 = {3, 1, 1, 5};
        int[] grumpy2 = {1, 1, 1, 1};
        int minutes2 = 4;
        int expected2 = 10; // 所有顾客都满意
        int result2 = solution.maxSatisfied(customers2, grumpy2, minutes2);
        printTestResult("Test 2 (技能覆盖全时段)", expected2, result2);
        
        // 测试用例3：老板从不生气
        int[] customers3 = {5, 5, 5};
        int[] grumpy3 = {0, 0, 0};
        int minutes3 = 2;
        int expected3 = 15; // 所有顾客都满意
        int result3 = solution.maxSatisfied(customers3, grumpy3, minutes3);
        printTestResult("Test 3 (老板从不生气)", expected3, result3);
        
        // 测试用例4：技能时长为1
        int[] customers4 = {10, 1, 10};
        int[] grumpy4 = {1, 0, 1};
        int minutes4 = 1;
        int expected4 = 11; // 基础1 + max(10,10) = 11
        int result4 = solution.maxSatisfied(customers4, grumpy4, minutes4);
        printTestResult("Test 4 (技能时长为1)", expected4, result4);
        
        // 测试用例5：无顾客
        int[] customers5 = {0, 0, 0};
        int[] grumpy5 = {1, 1, 1};
        int minutes5 = 2;
        int expected5 = 0;
        int result5 = solution.maxSatisfied(customers5, grumpy5, minutes5);
        printTestResult("Test 5 (无顾客)", expected5, result5);
    }
    
    /**
     * 打印测试结果
     */
    private static void printTestResult(String testName, int expected, int actual) {
        String status = expected == actual ? "PASS" : "FAIL";
        System.out.printf("%s: Expected=%d, Actual=%d, Status=%s%n", testName, expected, actual, status);
    }
}