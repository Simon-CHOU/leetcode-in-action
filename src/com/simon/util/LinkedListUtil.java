package com.simon.util;

public class LinkedListUtil {
    private ListNode head;
    private ListNode tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(int item) {
        ListNode p = new ListNode(item, null);//wrong statement
        if (isEmpty()) {
            head = p;
            tail = head;
        } else {
            tail.next = p;
            tail = tail.next;
        }
    }

    public ListNode getHead() {
        return this.head;
    }

//    public ListNode initByArr(int[] a) {
//        for (int j : a) {
//            insert(j);
//        }
//        return this.head;
//    }

    public static ListNode initByArr(int[] a) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        for (int item : a) {
            current.next = new ListNode(item);
            current = current.next;
        }
        return dummy.next;
    }
}
