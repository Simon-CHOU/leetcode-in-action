package com.simon.tdd;

import com.simon.util.ListNode;
import com.simon.util.ListNodeUtils;

import java.util.Arrays;

public class PartitionListTDD {

    public ListNode partition(ListNode head, int x) {
        // 创建哑节点作为小链表和大链表的头部
        ListNode smallDummy = new ListNode(0);
        ListNode bigDummy = new ListNode(0);
        // 初始化小链表和大链表的尾部指针
        ListNode smallTail = smallDummy;
        ListNode bigTail = bigDummy;

        // 遍历原链表
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                // 添加到小链表尾部
                smallTail.next = current;
                smallTail = smallTail.next;
            } else {
                // 添加到大链表尾部
                bigTail.next = current;
                bigTail = bigTail.next;
            }
            current = current.next;
        }

        // 连接小链表和大链表
        smallTail.next = bigDummy.next;
        // 将大链表尾部置为null，防止环
        bigTail.next = null;

        // 返回小链表的头部（跳过哑节点）
        return smallDummy.next;
    }

    public static void main(String[] args) {
        PartitionListTDD solution = new PartitionListTDD();

        testPartition(solution, new int[]{1, 4, 3, 2, 5}, 3, new int[]{1, 2, 4, 3, 5});
        testPartition(solution, new int[]{2, 1}, 2, new int[]{1, 2});
    }

    private static void testPartition(PartitionListTDD solution, int[] input, int x, int[] expected) {
        String testName = "Test with input: " + Arrays.toString(input) + ", x = " + x;

        // 构造输入链表
        ListNode head = ListNodeUtils.arrayToListNode(input);

        // 执行算法
        ListNode result = solution.partition(head, x);

        // 转换为数组
        int[] resultArray = ListNodeUtils.listNodeToArray(result);

        // 验证结果
        if (Arrays.equals(resultArray, expected)) {
            System.out.println(testName + " ✅");
        } else {
            System.out.println(testName + " ❌");
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual:   " + Arrays.toString(resultArray));
        }
    }
}
