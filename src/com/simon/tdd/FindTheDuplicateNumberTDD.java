package com.simon.tdd;

/**
 * LeetCode 287. 寻找重复数<p>
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 [1, n] 范围内。<p>
 * 假设 nums 中只存在一个重复的整数，找出这个重复的数。<p>
 * <p>
 * 解法：Floyd 环检测算法（龟兔赛跑算法）<p>
 * 将数组看作链表，数组下标作为节点，数组值作为指向下一个节点的指针。<p>
 * 由于存在重复数字，所以必然存在环，而环的入口就是重复的数字。<p>
 * <p>
 * 算法步骤：<p>
 * 1. 使用快慢指针找到环中的相遇点<p>
 * 2. 将一个指针重置到起点，两个指针以相同速度移动，再次相遇点即为环的入口点<p>
 * <p>
 * 时间复杂度：O(n)<p>
 * 空间复杂度：O(1)<p>
 */
public class FindTheDuplicateNumberTDD {
    
    /**
     * 使用 Floyd 环检测算法查找数组中的重复数字
     * 
     * @param nums 输入数组，包含 n+1 个整数，每个整数都在 [1, n] 范围内
     * @return 返回数组中的重复数字
     */
    public int findDuplicate(int[] nums) {
        // 阶段1：使用快慢指针找到环中相遇点
        int slow = nums[0];
        int fast = nums[0];
        
        // 移动指针直到相遇
        do {
            slow = nums[slow];           // 慢指针每次移动一步
            fast = nums[nums[fast]];     // 快指针每次移动两步
        } while (slow != fast);
        
        // 阶段2：找到环的入口点（重复数字）
        // 将fast重置到起点，slow保持在相遇点
        fast = nums[0];
        
        // 两个指针以相同速度移动，再次相遇点即为环入口
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;  // 返回重复的数字
    }
    
    /**
     * 使用主方法进行测试
     */
    public static void main(String[] args) {
        FindTheDuplicateNumberTDD solution = new FindTheDuplicateNumberTDD();
        
        // 测试用例1: [1,3,4,2,2] -> 期望结果: 2
        int[] nums1 = {1, 3, 4, 2, 2};
        int result1 = solution.findDuplicate(nums1);
        boolean pass1 = result1 == 2;
        System.out.println("测试用例1:");
        System.out.println("输入: [1,3,4,2,2]");
        System.out.println("结果: " + result1 + " (期望: 2) " + (pass1 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例2: [3,1,3,4,2] -> 期望结果: 3
        int[] nums2 = {3, 1, 3, 4, 2};
        int result2 = solution.findDuplicate(nums2);
        boolean pass2 = result2 == 3;
        System.out.println("测试用例2:");
        System.out.println("输入: [3,1,3,4,2]");
        System.out.println("结果: " + result2 + " (期望: 3) " + (pass2 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例3: [1,1] -> 期望结果: 1
        int[] nums3 = {1, 1};
        int result3 = solution.findDuplicate(nums3);
        boolean pass3 = result3 == 1;
        System.out.println("测试用例3:");
        System.out.println("输入: [1,1]");
        System.out.println("结果: " + result3 + " (期望: 1) " + (pass3 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例4: [2,2,2,2,2] -> 期望结果: 2
        int[] nums4 = {2, 2, 2, 2, 2};
        int result4 = solution.findDuplicate(nums4);
        boolean pass4 = result4 == 2;
        System.out.println("测试用例4:");
        System.out.println("输入: [2,2,2,2,2]");
        System.out.println("结果: " + result4 + " (期望: 2) " + (pass4 ? "PASS" : "FAIL"));
        System.out.println();
        
        // 测试用例5: [2,5,9,6,9,3,8,9,7,1] -> 期望结果: 9
        int[] nums5 = {2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
        int result5 = solution.findDuplicate(nums5);
        boolean pass5 = result5 == 9;
        System.out.println("测试用例5:");
        System.out.println("输入: [2,5,9,6,9,3,8,9,7,1]");
        System.out.println("结果: " + result5 + " (期望: 9) " + (pass5 ? "PASS" : "FAIL"));
        System.out.println();
        
        boolean allPass = pass1 && pass2 && pass3 && pass4 && pass5;
        System.out.println("全部测试通过: " + (allPass ? "PASS" : "FAIL"));
    }
}