package com.simon.tdd;

import com.simon.util.ListNode;

/**
 * LeetCode 142题: 环形链表 II<br>
 * 查找链表中环的入口节点
 */
public class LinkedListCycle2 {

    /**
     * 检测链表中是否有环，如果有则返回环的入口节点，否则返回null
     * 使用Floyd的龟兔赛跑算法（快慢指针法）
     *
     * @param head 链表的头节点
     * @return 环的入口节点，如果没有环则返回null
     */
    public ListNode detectCycle(ListNode head) {
        // 边界情况：空链表或只有一个节点且无环
        if (head == null || head.next == null) {
            return null;
        }

        // 第一阶段：使用快慢指针找到环内的一个交点
        ListNode slow = head;  // 慢指针，每次移动一步
        ListNode fast = head;  // 快指针，每次移动两步
        boolean hasCycle = false;

        while (fast != null && fast.next != null) { // Q1 这里为什么不判断slow ?
            slow = slow.next;          // 慢指针移动一步
            fast = fast.next.next;     // 快指针移动两步

            // 快慢指针相遇，说明链表中有环
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // 如果没有环，直接返回null
        if (!hasCycle) {
            return null;
        }

        // 第二阶段：找到环的入口
        // 重置一个指针到链表头部，两个指针都以相同速度移动
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        } // Q2 找到环的入口为什么还需要在遍历？直接用前文判断hasCycle的循环不行吗？

        // 两个指针再次相遇的点就是环的入口
        return slow;
    }

    /**
     * 创建带环的链表
     *
     * @param values 链表节点的值数组
     * @param pos 环的入口位置（从0开始），-1表示无环
     * @return 链表的头节点
     */
    private static ListNode createLinkedListWithCycle(int[] values, int pos) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleEntry = null;

        if (pos == 0) {
            cycleEntry = head;
        }

        // 创建链表
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
            if (i == pos) {
                cycleEntry = current;
            }
        }

        // 如果pos有效，则创建环
        if (pos >= 0 && cycleEntry != null) {
            current.next = cycleEntry;
        }

        return head;
    }

    /**
     * 主方法，用于测试detectCycle函数
     */
    public static void main(String[] args) {
        // 测试用例1：有环链表，入口在索引1
        int[] values1 = {3, 2, 0, -4};
        int pos1 = 1;
        ListNode head1 = createLinkedListWithCycle(values1, pos1);
        ListNode result1 = new LinkedListCycle2().detectCycle(head1);
        boolean test1Passed = result1 != null && result1.val == values1[pos1];
        System.out.println("Test 1: " + (test1Passed ? "PASS" : "FAIL") + ", Expected: " + values1[pos1] + ", Actual: " + (result1 != null ? result1.val : "null"));

        // 测试用例2：有环链表，入口在索引0
        int[] values2 = {1, 2};
        int pos2 = 0;
        ListNode head2 = createLinkedListWithCycle(values2, pos2);
        ListNode result2 = new LinkedListCycle2().detectCycle(head2);
        boolean test2Passed = result2 != null && result2.val == values2[pos2];
        System.out.println("Test 2: " + (test2Passed ? "PASS" : "FAIL") + ", Expected: " + values2[pos2] + ", Actual: " + (result2 != null ? result2.val : "null"));

        // 测试用例3：无环链表
        int[] values3 = {1};
        int pos3 = -1;
        ListNode head3 = createLinkedListWithCycle(values3, pos3);
        ListNode result3 = new LinkedListCycle2().detectCycle(head3);
        boolean test3Passed = result3 == null;
        System.out.println("Test 3: " + (test3Passed ? "PASS" : "FAIL") + ", Expected: null, Actual: " + (result3 != null ? result3.val : "null"));

        // 测试用例4：空链表
        ListNode head4 = null;
        ListNode result4 = new LinkedListCycle2().detectCycle(head4);
        boolean test4Passed = result4 == null;
        System.out.println("Test 4: " + (test4Passed ? "PASS" : "FAIL") + ", Expected: null, Actual: " + (result4 != null ? result4.val : "null"));

        // 测试用例5：较长的有环链表
        int[] values5 = {1, 2, 3, 4, 5, 6, 7, 8};
        int pos5 = 3;
        ListNode head5 = createLinkedListWithCycle(values5, pos5);
        ListNode result5 = new LinkedListCycle2().detectCycle(head5);
        boolean test5Passed = result5 != null && result5.val == values5[pos5];
        System.out.println("Test 5: " + (test5Passed ? "PASS" : "FAIL") + ", Expected: " + values5[pos5] + ", Actual: " + (result5 != null ? result5.val : "null"));
    }
}
