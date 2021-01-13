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
        return null;
    }
}