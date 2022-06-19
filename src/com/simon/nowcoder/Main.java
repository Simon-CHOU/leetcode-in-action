package com.simon.nowcoder;

import java.util.Scanner;

/**
 * [编程题]删除链表的倒数第n个节点
 * https://www.nowcoder.com/questionTerminal/f95dcdafbde44b22a6d741baf71653f6
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    static Solution solution = new Solution();
    static Solution1 solution1 = new Solution1();

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        // 1->2->3->4->5, n= 2
        int n = sc.nextInt();

//        solution.removeNthFromEnd(n1, n);
        solution1.removeNthFromEnd(n1, n);

        displayList(n1); // n=2 res:   1->2->3->5
        // n=3 res:   1->2->4->5

    }

    public static void displayList(ListNode head) {
        while (head.next != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println(head.val); // error : head.next 1->2->3->4->null
    }

}

class ListNode {
    int val;
    ListNode next = null;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        for (int i = 0; i < n; i++)
            first = first.next;
        //如果n的值等于链表的长度,直接返回去掉头结点的链表
        if (first == null)
            return head.next;
        while (first.next != null)   //同时移动两个指针
        {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }
}

// 思路1：双指针法
// https://blog.csdn.net/qq_35398517/article/details/114492012
class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();//借助虚拟指针
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
