package com.simon.tdd;

public class MaximumPointsYouCanObtainFromCardsTDD {

    /**
     * 计算可获得的最大点数
     * @param cards 卡片数组
     * @param k 要取的卡片数量
     * @return 最大点数和
     */
    public static int maxScore(int[] cards, int k) {
        int n = cards.length;
        int totalSum = calculateTotalSum(cards);

        // 若取走所有卡片，直接返回总和
        if (k == n) {
            return totalSum;
        }

        int windowSize = n - k;
        int currentWindowSum = calculateInitialWindowSum(cards, windowSize);
        int minWindowSum = currentWindowSum;

        // 滑动窗口计算最小和
        for (int i = windowSize; i < n; i++) {
            currentWindowSum = currentWindowSum - cards[i - windowSize] + cards[i];
            minWindowSum = Math.min(minWindowSum, currentWindowSum);
        }

        return totalSum - minWindowSum;
    }

    /**
     * 计算数组总点数
     */
    private static int calculateTotalSum(int[] cards) {
        int sum = 0;
        for (int card : cards) {
            sum += card;
        }
        return sum;
    }

    /**
     * 计算初始窗口（前windowSize个元素）的和
     */
    private static int calculateInitialWindowSum(int[] cards, int windowSize) {
        int sum = 0;
        for (int i = 0; i < windowSize; i++) {
            sum += cards[i];
        }
        return sum;
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 测试用例1：示例输入
        int[] test1 = {1, 2, 3, 4, 5, 6, 1};
        int k1 = 3;
        int expected1 = 12;
        int result1 = maxScore(test1, k1);
        printTestResult("Test 1", expected1, result1);

        // 测试用例2：全取
        int[] test2 = {2, 2, 2};
        int k2 = 3;
        int expected2 = 6;
        int result2 = maxScore(test2, k2);
        printTestResult("Test 2", expected2, result2);

        // 测试用例3：取1张
        int[] test3 = {9, 7, 7, 9, 7, 7, 9};
        int k3 = 1;
        int expected3 = 9;
        int result3 = maxScore(test3, k3);
        printTestResult("Test 3", expected3, result3);

        // 测试用例4：取两端混合
        int[] test4 = {100, 40, 17, 9, 73, 75};
        int k4 = 3;
        int expected4 = 248; // 100 + 40 + 105（73+75？不，100+40+75=215？不对，正确计算：100+75+73=248）
        int result4 = maxScore(test4, k4);
        printTestResult("Test 4", expected4, result4);
    }

    /**
     * 打印测试结果
     */
    private static void printTestResult(String testName, int expected, int actual) {
        String status = expected == actual ? "PASS" : "FAIL";
        System.out.printf("%s: Expected=%d, Actual=%d, Status=%s%n", testName, expected, actual, status);
    }
}
