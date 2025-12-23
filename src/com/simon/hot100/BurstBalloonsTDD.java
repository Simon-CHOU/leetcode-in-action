package com.simon.hot100;

public class BurstBalloonsTDD {

    /**
     * <p>解题思路：区间动态规划 (Interval DP)</p>
     * <p>1. 核心矛盾：正向思考戳气球会导致原本不相邻的气球变得相邻，子问题依赖关系复杂。</p>
     * <p>2. 逆向思维：考虑区间 (i, j) 内最后一个被戳破的气球 k。此时，i 和 j 是 k 的左右邻居。</p>
     * <p>3. 状态定义：dp[i][j] 表示戳破 (i, j) 开区间内所有气球获得的最大金币数。</p>
     * <p>4. 转移方程：dp[i][j] = max(dp[i][k] + dp[k][j] + points[i] * points[k] * points[j])，其中 k 属于 (i, j)。</p>
     *
     * @param nums 气球数组
     * @return 最大金币数
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 1. 预处理：两端补 1，简化边界处理
        int n = nums.length;
        int[] points = new int[n + 2];
        points[0] = 1;
        points[n + 1] = 1;
        System.arraycopy(nums, 0, points, 1, n);

        // 2. 初始化 DP 表
        // dp[i][j] 的含义：戳破开区间 (i, j) 之间的所有气球所能获得的最大金币
        int[][] dp = new int[n + 2][n + 2];

        // 3. 状态转移：按区间长度从短到长进行遍历
        // 区间长度 len 表示 i 和 j 之间的距离，最小为 2 (即中间有一个气球)
        for (int len = 2; len <= n + 1; len++) {
            // 开始位置 i
            for (int i = 0; i <= n + 1 - len; i++) {
                int j = i + len;
                // 枚举区间 (i, j) 中最后一个戳破的气球 k
                for (int k = i + 1; k < j; k++) {
                    int current = points[i] * points[k] * points[j];
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + current);
                }
            }
        }

        return dp[0][n + 1];
    }

    /**
     * <p>TDD 测试入口</p>
     * <p>使用 main 方法模拟测试框架，验证不同场景下的正确性</p>
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        BurstBalloonsTDD solution = new BurstBalloonsTDD();

        // Test Case 1: Standard case from LeetCode
        assertTest("Example 1", solution.maxCoins(new int[]{3, 1, 5, 8}), 167);

        // Test Case 2: Minimal case
        assertTest("Single balloon", solution.maxCoins(new int[]{1, 5}), 10);

        // Test Case 3: Empty input
        assertTest("Empty array", solution.maxCoins(new int[]{}), 0);

        // Test Case 4: Already sorted or random
        assertTest("Small case", solution.maxCoins(new int[]{1, 5}), 10);
        
        System.out.println("\nAll tests passed if no FAIL logs appear.");
    }

    /**
     * <p>断言辅助方法</p>
     *
     * @param testName 测试用例名称
     * @param actual 实际结果
     * @param expected 预期结果
     */
    private static void assertTest(String testName, int actual, int expected) {
        if (actual == expected) {
            System.out.printf("[PASS] %s: Expected %d, Actual %d%n", testName, expected, actual);
        } else {
            System.err.printf("[FAIL] %s: Expected %d, Actual %d%n", testName, expected, actual);
        }
    }
}
