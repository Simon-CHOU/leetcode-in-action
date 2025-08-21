package com.simon.interview.zywl;

/**
 * LeetCode 287. 寻找重复数
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 [1, n] 范围内。
 * 你需要找出数组中重复了的数字。
 * <p>
 * 解法：弗洛伊德环检测算法（Floyd's Cycle Detection Algorithm）
 * 将数组看作链表，数组下标作为节点，数组值作为指向下一个节点的指针。
 * 由于存在重复数字，所以必然存在环，而环的入口就是重复的数字。
 * <p>
 * 算法步骤：
 * 1. 使用快慢指针找到环中的相遇点
 * 2. 将一个指针重置到起点，两个指针以相同速度移动，再次相遇点即为环的入口点
 * <p>
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class ZYWL014 {
    
    /**
     * 查找数组中的重复数字
     * 
     * @param nums 输入数组，包含 n+1 个整数，每个整数都在 [1, n] 范围内
     * @return 返回数组中的重复数字
     */
    public static int findDuplicate(int[] nums) {
        // 第一阶段：使用快慢指针找到环中的相遇点
        int slow = nums[0];
        int fast = nums[0];
        
        // 移动指针直到相遇
        do {
            slow = nums[slow];        // 慢指针走一步
            fast = nums[nums[fast]];  // 快指针走两步
        } while (slow != fast);
        
        // 第二阶段：找到环的入口点（重复数字）
        slow = nums[0];  // 重置慢指针到起点
        
        // 两个指针以相同速度移动，直到相遇
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;  // 返回环的入口点，即重复数字
    }
    
    /**
     * 使用主方法进行测试
     */
    public static void main(String[] args) {
        // 测试用例1: [1,3,4,2,2] -> 期望结果: 2
        int[] nums1 = {1, 3, 4, 2, 2};
        int result1 = findDuplicate(nums1);
        System.out.println("测试用例1:");
        System.out.println("输入: [1,3,4,2,2]");
        System.out.println("结果: " + result1 + " (期望: 2) " + (result1 == 2 ? "PASS" : "FAIL"));
        
        // 测试用例2: [3,1,3,4,2] -> 期望结果: 3
        int[] nums2 = {3, 1, 3, 4, 2};
        int result2 = findDuplicate(nums2);
        System.out.println("测试用例2:");
        System.out.println("输入: [3,1,3,4,2]");
        System.out.println("结果: " + result2 + " (期望: 3) " + (result2 == 3 ? "PASS" : "FAIL"));
        
        // 测试用例3: [2,2,2,2,2] -> 期望结果: 2
        int[] nums3 = {2, 2, 2, 2, 2};
        int result3 = findDuplicate(nums3);
        System.out.println("测试用例3:");
        System.out.println("输入: [2,2,2,2,2]");
        System.out.println("结果: " + result3 + " (期望: 2) " + (result3 == 2 ? "PASS" : "FAIL"));
        
        // 测试用例4: [1,1] -> 期望结果: 1
        int[] nums4 = {1, 1};
        int result4 = findDuplicate(nums4);
        System.out.println("测试用例4:");
        System.out.println("输入: [1,1]");
        System.out.println("结果: " + result4 + " (期望: 1) " + (result4 == 1 ? "PASS" : "FAIL"));
    }
}