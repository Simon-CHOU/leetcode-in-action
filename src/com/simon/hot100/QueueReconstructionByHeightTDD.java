package com.simon.hot100;

import java.util.Arrays;

/**
 * LeetCode 406. 根据身高重建队列的解法实现
 * <p>
 * 本题使用贪心算法解决：
 * 1. 先按身高降序、k值升序排序
 * 2. 然后按顺序将每个人插入到结果数组的第k个位置
 * <p>
 * 时间复杂度：O(n²)，主要由排序O(n log n)和插入操作O(n²)决定
 * 空间复杂度：O(n)，用于存储结果数组
 */
public class QueueReconstructionByHeightTDD {
    
    /**
     * 根据身高重建队列
     * 
     * @param people 二维数组，people[i] = [hi, ki] 表示第i个人的身高为hi，
     *               且前面有ki个身高大于或等于hi的人
     * @return 重建后的队列
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return new int[0][];
        }
        
        // 按身高降序、k值升序排序
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0]; // 身高降序
            } else {
                return a[1] - b[1]; // k值升序
            }
        });
        
        // 逐个插入到结果数组的第k个位置
        int[][] result = new int[people.length][];
        for (int i = 0; i < people.length; i++) {
            int k = people[i][1];
            // 将当前元素插入到位置k，将后面的元素后移
            System.arraycopy(result, k, result, k + 1, i - k);
            result[k] = people[i];
        }
        
        return result;
    }
    
    /**
     * 测试方法，使用TDD方式验证解法的正确性
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        QueueReconstructionByHeightTDD solution = new QueueReconstructionByHeightTDD();
        
        // 测试用例1：常规情况
        testCase(solution, new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}},
                  new int[][]{{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}});
        
        // 测试用例2：相同身高，不同k值
        testCase(solution, new int[][]{{6, 0}, {6, 1}, {6, 2}},
                  new int[][]{{6, 0}, {6, 1}, {6, 2}});
        
        // 测试用例3：空数组
        testCase(solution, new int[][]{}, new int[][]{});
        
        // 测试用例4：单元素
        testCase(solution, new int[][]{{5, 0}}, new int[][]{{5, 0}});
        
        // 测试用例5：身高已排序
        testCase(solution, new int[][]{{5, 0}, {4, 1}, {3, 2}, {2, 3}, {1, 4}},
                  new int[][]{{5, 0}, {4, 1}, {3, 2}, {2, 3}, {1, 4}});
    }
    
    /**
     * 执行单个测试用例并输出结果
     * 
     * @param solution 解决方案实例
     * @param input 输入参数
     * @param expected 期望输出
     */
    private static void testCase(QueueReconstructionByHeightTDD solution, int[][] input, int[][] expected) {
        int[][] result = solution.reconstructQueue(deepCopy(input));
        boolean passed = Arrays.deepEquals(result, expected);
        System.out.println(passed ? "PASS" : "FAIL");
        
        if (!passed) {
            System.out.println("Input: " + Arrays.deepToString(input));
            System.out.println("Expected: " + Arrays.deepToString(expected));
            System.out.println("Actual: " + Arrays.deepToString(result));
        }
    }
    
    /**
     * 创建二维数组的深拷贝
     * 
     * @param original 原始数组
     * @return 深拷贝后的数组
     */
    private static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }
        
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}
