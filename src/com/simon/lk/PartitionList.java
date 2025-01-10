package com.simon.lk;

import com.simon.util.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution86 { // 86. 分隔链表 https://leetcode.cn/problems/partition-list/description/
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHeader = small;
        ListNode large = new ListNode(0);
        ListNode largeHeader = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHeader.next;
        return smallHeader.next;
    }
}

public class PartitionList {
    public static void main(String[] args) {
//        int [] head = {1,4,3,2,5,2};
        // 根据 int [] head = {1,4,3,2,5,2}; 创建链表
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        int x = 3;

        Solution86 solution86 = new Solution86();
        ListNode newHead =  solution86.partition(head, 3);

//        while (head != null) {
//            System.out.print(head.val+" ");
//            head = head.next;
//        }

        // 打印链表
        System.out.print("[");
        while (newHead != null) {
            System.out.print(newHead.val);
            if (newHead.next != null) {
                System.out.print(",");
            }
            newHead = newHead.next;
        }
        System.out.print("]");
    }
}
