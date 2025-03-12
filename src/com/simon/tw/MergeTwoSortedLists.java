package com.simon.tw;

import com.simon.util.LinkedListUtil;
import com.simon.util.ListNode;

class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode h = new ListNode(), head = h;
        ListNode p = list1;
        ListNode q = list2;
        while (p != null && q != null) {
            if (p.val <= q.val) {
                head.next = p;
                p = p.next;
            } else {
                head.next = q;
                q = q.next;
            }
            head = head.next;
        }
        //末尾
        if (p != null) {
            head.next = p;
        }
        if (q != null) {
            head.next = q;
        }
        return h.next;
    }
}

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution21 solution21 = new Solution21();
        int[] a1 = new int[]{1, 2, 4};
        int[] a2 = new int[]{1, 3, 4};
        ListNode l1 = LinkedListUtil.initByArr(a1);
        ListNode l2 = LinkedListUtil.initByArr(a2);
        System.out.println(solution21.mergeTwoLists(l1, l2));
        System.out.println(solution21.mergeTwoLists(l1, l2));
    }
}
