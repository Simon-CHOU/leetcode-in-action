package com.simon.util;

import java.util.ArrayList;
import java.util.List;

public class ListNodeUtils {

    // 将整数数组转换为链表
    public static ListNode arrayToListNode(int[] arr) {
        if (arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // 将链表转换为整数数组
    public static int[] listNodeToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 打印链表
    public static void print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" -> ");
            head = head.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }
}