package com.simon.interview.zywl;

public class ZYWL013 {  //leetcode 287. 寻找重复数
    // https://leetcode.cn/problems/find-the-duplicate-number/

    /**
     * 使用Floyd环检测算法查找重复数
     * 将数组看作链表，值指向下一个节点的索引
     * 利用快慢指针找到环，再找到环的入口点即为重复数
     * 
     * @param nums 输入数组，包含n+1个整数，范围在[1,n]内
     * @return 重复的数字
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
    
    // 测试方法
    public static void main(String[] args) {
        ZYWL013 solution = new ZYWL013();
        
        // 测试用例1: [1,3,4,2,2] -> 2
        int[] nums1 = {1, 3, 4, 2, 2};
        int result1 = solution.findDuplicate(nums1);
        boolean pass1 = result1 == 2;
        System.out.println("测试用例1: [1,3,4,2,2]");
        System.out.println("结果: " + result1 + " (期望: 2) " + (pass1 ? "PASS" : "FAIL"));
        System.out.println();

        // 测试用例2: [3,1,3,4,2] -> 3
        int[] nums2 = {3, 1, 3, 4, 2};
        int result2 = solution.findDuplicate(nums2);
        boolean pass2 = result2 == 3;
        System.out.println("测试用例2: [3,1,3,4,2]");
        System.out.println("结果: " + result2 + " (期望: 3) " + (pass2 ? "PASS" : "FAIL"));
        System.out.println();

        // 测试用例3: [1,1] -> 1
        int[] nums3 = {1, 1};
        int result3 = solution.findDuplicate(nums3);
        boolean pass3 = result3 == 1;
        System.out.println("测试用例3: [1,1]");
        System.out.println("结果: " + result3 + " (期望: 1) " + (pass3 ? "PASS" : "FAIL"));
        System.out.println();

        // 测试用例4: [2,2,2,2,2] -> 2
        int[] nums4 = {2, 2, 2, 2, 2};
        int result4 = solution.findDuplicate(nums4);
        boolean pass4 = result4 == 2;
        System.out.println("测试用例4: [2,2,2,2,2]");
        System.out.println("结果: " + result4 + " (期望: 2) " + (pass4 ? "PASS" : "FAIL"));
        System.out.println();
        
        boolean allPass = pass1 && pass2 && pass3 && pass4;
        System.out.println("全部测试通过: " + (allPass ? "PASS" : "FAIL"));
    }
}