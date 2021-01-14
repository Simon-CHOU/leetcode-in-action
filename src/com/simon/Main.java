package com.simon;

import com.simon.util.InputUtil;
import com.simon.util.LinkedList;
import com.simon.util.ListNode;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] a = InputUtil.inputIntArray();
            int[] b = InputUtil.inputIntArray();
            LinkedList list1 = new LinkedList();
            ListNode l1 = list1.initByArr(a);
            LinkedList list2 = new LinkedList();
            ListNode l2 = list2.initByArr(b);
            System.out.println(solution.mergeTwoLists(l1, l2));
        }
    }


}


class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode();//with head node
        ListNode last = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                last.next = l2;
                l2 = l2.next;
            } else {
                last.next = l1;
                l1 = l1.next;
            }
            last = last.next;
        }
        if(l1!=null){
            last.next = l1;
        }
        if(l2!=null) {
            last.next = l2;
        }
//        loop is unnecessary
//        while (l1 != null) {
//            last.next = l1;
//            last = last.next;
//            l1 = l1.next;
//        }
//        while (l2 != null) {
//            last.next = l2;
//            last = last.next;
//            l2 = l2.next;
//        }
        return newHead.next;// return no head node
    }
}