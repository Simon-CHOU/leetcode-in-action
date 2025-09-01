package com.simon.tdd;

import com.simon.util.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class ReverseLinkedListTDD {

    /**
     * 反转单链表<p>
     * <p>
     * 使用迭代方法，通过三个指针(prev, curr, next)来完成链表的反转操作<p>
     * <p>
     * 算法思路：<p>
     * 1. 初始化prev为null，curr为head<p>
     * 2. 遍历链表，在每次迭代中：<p>
     *    - 保存curr的下一个节点到next<p>
     *    - 将curr的next指针指向prev<p>
     *    - 将prev移动到curr<p>
     *    - 将curr移动到next<p>
     * 3. 当curr为null时，prev即为新的头节点<p>
     * <p>
     * 示例：<p>
     * 输入：1->2->3->4->5->NULL<p>
     * 输出：5->4->3->2->1->NULL<p>
     *
     * @param head 链表的头节点
     * @return 反转后链表的头节点
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        System.out.println("开始反转链表:");
        printListWithPointers(prev, curr, "初始状态");
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            printListWithPointers(prev, curr, "本次迭代后");
        }
        
        System.out.println("循环结束时: curr=" + curr + ", prev=" + (prev != null ? prev.val : "null"));
        System.out.println("返回prev，因为prev现在指向反转后链表的头节点");
        
        return prev;
    }
    
    /**
     * 打印带指针状态的链表
     */
    private void printListWithPointers(ListNode prev, ListNode curr, String desc) {
        System.out.println(desc + ": prev=" + (prev != null ? prev.val : "null") + 
                          ", curr=" + (curr != null ? curr.val : "null"));
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        ReverseLinkedListTDD solution = new ReverseLinkedListTDD();
        
        // 测试用例1: [1,2,3,4,5]
        System.out.println("=== 测试用例1 ===");
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        
        System.out.println("原链表: 1->2->3->4->5->NULL");
        ListNode result1 = solution.reverseList(head1);
        System.out.print("最终结果: ");
        printList(result1);
        boolean test1 = checkList(result1, new int[]{5, 4, 3, 2, 1});
        System.out.println("测试结果: " + (test1 ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * 打印链表
     * @param head 链表头节点
     */
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("NULL");
            return;
        }
        
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print("->");
            } else {
                System.out.print("->NULL");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    /**
     * 检查链表是否与预期结果一致
     * @param head 链表头节点
     * @param expected 期望的结果数组
     * @return 是否一致
     */
    private static boolean checkList(ListNode head, int[] expected) {
        ListNode current = head;
        int i = 0;
        
        while (current != null && i < expected.length) {
            if (current.val != expected[i]) {
                return false;
            }
            current = current.next;
            i++;
        }
        
        // 检查长度是否一致
        return (current == null && i == expected.length);
    }
}