package com.simon.interview.zywl;

/**
 * 链表节点定义
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class ZYWL014 {
    
    /**
     * 使用Floyd算法（快慢指针法）检测链表中环的入口节点
     * 
     * 算法分为两个阶段：
     * 1. 使用快慢指针判断链表中是否存在环
     * 2. 如果存在环，找到环的入口节点
     * 
     * 时间复杂度：O(n)，其中n是链表中的节点数
     * 空间复杂度：O(1)，只使用了常数级别的额外空间
     * 
     * @param pHead 链表头节点
     * @return 环的入口节点，如果不存在环则返回null
     */
    public static ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        
        // 第一阶段：使用快慢指针检测环的存在
        ListNode slow = pHead;
        ListNode fast = pHead;
        
        // 快指针每次走两步，慢指针每次走一步
        do {
            // 如果快指针或快指针的下一个节点为null，说明没有环
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        
        // 第二阶段：找到环的入口
        // 将快指针重置到链表头部，慢指针保持在相遇点
        // 两个指针以相同速度移动，再次相遇的点就是环的入口
        fast = pHead;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        ZYWL014 solution = new ZYWL014();
        
        // 测试用例1：没有环的链表
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        // 没有形成环
        
        ListNode result1 = EntryNodeOfLoop(node1);
        System.out.println("测试用例1 - 没有环的链表:");
        System.out.println("结果: " + result1 + " (期望: null) " + (result1 == null ? "PASS" : "FAIL"));
        
        // 测试用例2：有环的链表
        ListNode node6 = new ListNode(1);
        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(3);
        ListNode node9 = new ListNode(4);
        ListNode node10 = new ListNode(5);
        
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node8; // 形成环，入口是node8(值为3)
        
        ListNode result2 = EntryNodeOfLoop(node6);
        System.out.println("测试用例2 - 有环的链表:");
        System.out.println("结果: " + (result2 != null ? result2.val : result2) + " (期望: 3) " + 
                          (result2 != null && result2.val == 3 ? "PASS" : "FAIL"));
        
        // 测试用例3：单节点自环
        ListNode node11 = new ListNode(1);
        node11.next = node11; // 自环
        
        ListNode result3 = EntryNodeOfLoop(node11);
        System.out.println("测试用例3 - 单节点自环:");
        System.out.println("结果: " + (result3 != null ? result3.val : result3) + " (期望: 1) " + 
                          (result3 != null && result3.val == 1 ? "PASS" : "FAIL"));
        
        // 测试用例4：空链表
        ListNode result4 = EntryNodeOfLoop(null);
        System.out.println("测试用例4 - 空链表:");
        System.out.println("结果: " + result4 + " (期望: null) " + (result4 == null ? "PASS" : "FAIL"));
    }
}