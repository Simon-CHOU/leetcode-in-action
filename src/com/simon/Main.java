package com.simon;

import com.simon.uitl.InputUtil;
import com.simon.uitl.LinkedList;
import com.simon.uitl.ListNode;

import java.util.*;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        ListNode first = null, last= null;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null) {
            if (first == null) {
                if(p1.val > p2.val){
                    first =  p2;
                    p2 = p2.next;
                }else {
                    first = p1;
                    p1 = p1.next;
                }
                last = first;
            }else {
                if (p1.val > p2.val) {
                    last.next = p2;
                    last = last.next;
                    p2 = p2.next;
                } else {
                    last.next = p1;
                    last = last.next;
                    p1 = p1.next;
                }
            }
        }
        if(p1!=null){
            if(last ==null){
                last =p1;
                first =last;
            }else {
                last.next = p1;
            }
        }
        if(p2!=null) {
            if (last == null) {
                last = p2;
                first = last;
            } else {
                last.next = p2;
            }
        }
        return first;
    }
}