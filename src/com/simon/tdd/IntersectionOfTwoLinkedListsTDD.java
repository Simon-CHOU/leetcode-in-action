package com.simon.tdd;

import com.simon.util.ListNode;

/**
 * LeetCode 160. 相交链表 - TDD实现
 * <p>
 * 题目描述：给你两个单链表的头节点 headA 和 headB，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null。
 * <p>
 * 核心考察点：
 * 1. 链表遍历和指针操作
 * 2. 双指针技巧（消除长度差异）
 * 3. 空间复杂度优化
 * <p>
 * 解题思路（金字塔原理）：
 * 顶层结论：使用双指针消除链表长度差异，实现同步遍历找到交点
 * <p>
 * 第二层分解：
 * 1. 问题本质：两个不同长度的链表如何同步到达交点
 * 2. 核心洞察：让两个指针都走过相同的总距离
 * 3. 实现策略：指针A走完链表A后走链表B，指针B走完链表B后走链表A
 * <p>
 * 第三层细节：
 * 1. 初始化：两个指针分别指向两个链表头
 * 2. 遍历规则：到达末尾后切换到另一个链表
 * 3. 终止条件：两指针相遇（交点或null）
 * <p>
 * 算法执行过程ASCII图解：
 * <pre>
 * 场景1：有交点的情况
 * 链表A: 4 -> 1 -> 8 -> 4 -> 5 -> null
 * 链表B:      2 -> 3 -> 8 -> 4 -> 5 -> null
 *                    ↑
 *                  交点
 * 
 * 步骤1: pA=4, pB=2
 * A: [pA]4 -> 1 -> 8 -> 4 -> 5 -> null
 * B: [pB]2 -> 3 -> 8 -> 4 -> 5 -> null
 * 
 * 步骤2: pA=1, pB=3  
 * A: 4 -> [pA]1 -> 8 -> 4 -> 5 -> null
 * B: 2 -> [pB]3 -> 8 -> 4 -> 5 -> null
 * 
 * 步骤3: pA=8, pB=8 (到达交点)
 * A: 4 -> 1 -> [pA]8 -> 4 -> 5 -> null
 * B: 2 -> 3 -> [pB]8 -> 4 -> 5 -> null
 * 
 * 场景2：无交点的情况
 * 链表A: 2 -> 6 -> 4 -> null
 * 链表B: 1 -> 5 -> null
 * 
 * 两指针最终都会到达null，返回null
 * </pre>
 * 
 * @author Simon
 * @version 1.0
 */
public class IntersectionOfTwoLinkedListsTDD {

    /**
     * 寻找两个链表的交点
     * <p>
     * 算法核心思想：
     * 1. 使用双指针技巧消除链表长度差异
     * 2. 指针A遍历完链表A后遍历链表B
     * 3. 指针B遍历完链表B后遍历链表A
     * 4. 两指针最终会在交点相遇，或同时到达null
     * <p>
     * 时间复杂度：O(m + n)，其中m和n分别是两个链表的长度
     * 空间复杂度：O(1)，只使用了常数级别的额外空间
     * 
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return 交点节点，如果不存在交点则返回null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界条件检查
        if (headA == null || headB == null) {
            return null;
        }
        
        // 初始化双指针
        ListNode pA = headA;
        ListNode pB = headB;
        
        // 核心算法：双指针遍历
        // 当pA和pB不相等时继续遍历
        while (pA != pB) {
            // pA到达链表A末尾后切换到链表B，否则继续遍历链表A
            pA = (pA == null) ? headB : pA.next;
            // pB到达链表B末尾后切换到链表A，否则继续遍历链表B  
            pB = (pB == null) ? headA : pB.next;
        }
        
        // 返回交点（或null）
        return pA;
    }

    /**
     * TDD测试主方法
     * <p>
     * 测试用例设计原则：
     * 1. 边界条件：空链表、单节点
     * 2. 正常情况：有交点、无交点
     * 3. 特殊情况：不同长度的链表
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        IntersectionOfTwoLinkedListsTDD solution = new IntersectionOfTwoLinkedListsTDD();
        
        System.out.println("=== LeetCode 160. 相交链表 - TDD测试 ===");
        System.out.println();
        
        // 测试用例1：有交点的情况（LeetCode示例1）
        testCase1(solution);
        
        // 测试用例2：有交点的情况（LeetCode示例2）  
        testCase2(solution);
        
        // 测试用例3：无交点的情况（LeetCode示例3）
        testCase3(solution);
        
        // 测试用例4：边界条件 - 空链表
        testCase4(solution);
        
        // 测试用例5：边界条件 - 单节点相交
        testCase5(solution);
        
        System.out.println("\n=== 所有测试完成 ===");
    }
    
    /**
     * 测试用例1：有交点的情况
     * 链表A: 4->1->8->4->5
     * 链表B: 5->6->1->8->4->5
     * 交点：8
     */
    private static void testCase1(IntersectionOfTwoLinkedListsTDD solution) {
        System.out.println("测试用例1 - 有交点的情况:");
        
        // 创建交点后的公共部分: 8->4->5
        ListNode intersection = new ListNode(8);
        intersection.next = new ListNode(4);
        intersection.next.next = new ListNode(5);
        
        // 创建链表A: 4->1->8->4->5
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = intersection;
        
        // 创建链表B: 5->6->1->8->4->5
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = intersection;
        
        System.out.print("链表A: ");
        printList(headA);
        System.out.print("链表B: ");
        printList(headB);
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        boolean passed = (result != null && result.val == 8);
        
        System.out.println("期望交点值: 8");
        System.out.println("实际交点值: " + (result != null ? result.val : "null"));
        System.out.println("测试结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 测试用例2：有交点的情况（不同长度）
     * 链表A: 1->9->1->2->4
     * 链表B: 3->2->4
     * 交点：2
     */
    private static void testCase2(IntersectionOfTwoLinkedListsTDD solution) {
        System.out.println("测试用例2 - 有交点的情况（不同长度）:");
        
        // 创建交点后的公共部分: 2->4
        ListNode intersection = new ListNode(2);
        intersection.next = new ListNode(4);
        
        // 创建链表A: 1->9->1->2->4
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(9);
        headA.next.next = new ListNode(1);
        headA.next.next.next = intersection;
        
        // 创建链表B: 3->2->4
        ListNode headB = new ListNode(3);
        headB.next = intersection;
        
        System.out.print("链表A: ");
        printList(headA);
        System.out.print("链表B: ");
        printList(headB);
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        boolean passed = (result != null && result.val == 2);
        
        System.out.println("期望交点值: 2");
        System.out.println("实际交点值: " + (result != null ? result.val : "null"));
        System.out.println("测试结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 测试用例3：无交点的情况
     * 链表A: 2->6->4
     * 链表B: 1->5
     * 交点：null
     */
    private static void testCase3(IntersectionOfTwoLinkedListsTDD solution) {
        System.out.println("测试用例3 - 无交点的情况:");
        
        // 创建链表A: 2->6->4
        ListNode headA = new ListNode(2);
        headA.next = new ListNode(6);
        headA.next.next = new ListNode(4);
        
        // 创建链表B: 1->5
        ListNode headB = new ListNode(1);
        headB.next = new ListNode(5);
        
        System.out.print("链表A: ");
        printList(headA);
        System.out.print("链表B: ");
        printList(headB);
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        boolean passed = (result == null);
        
        System.out.println("期望交点值: null");
        System.out.println("实际交点值: " + (result != null ? result.val : "null"));
        System.out.println("测试结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 测试用例4：边界条件 - 空链表
     */
    private static void testCase4(IntersectionOfTwoLinkedListsTDD solution) {
        System.out.println("测试用例4 - 边界条件（空链表）:");
        
        ListNode headA = null;
        ListNode headB = new ListNode(1);
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        boolean passed = (result == null);
        
        System.out.println("链表A: null");
        System.out.print("链表B: ");
        printList(headB);
        System.out.println("期望交点值: null");
        System.out.println("实际交点值: " + (result != null ? result.val : "null"));
        System.out.println("测试结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 测试用例5：边界条件 - 单节点相交
     */
    private static void testCase5(IntersectionOfTwoLinkedListsTDD solution) {
        System.out.println("测试用例5 - 边界条件（单节点相交）:");
        
        // 创建共同的单节点
        ListNode intersection = new ListNode(1);
        
        ListNode headA = intersection;
        ListNode headB = intersection;
        
        System.out.print("链表A: ");
        printList(headA);
        System.out.print("链表B: ");
        printList(headB);
        
        ListNode result = solution.getIntersectionNode(headA, headB);
        boolean passed = (result != null && result.val == 1);
        
        System.out.println("期望交点值: 1");
        System.out.println("实际交点值: " + (result != null ? result.val : "null"));
        System.out.println("测试结果: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 辅助方法：打印链表
     * 
     * @param head 链表头节点
     */
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print("->");
            }
            current = current.next;
        }
        System.out.println();
    }
}
