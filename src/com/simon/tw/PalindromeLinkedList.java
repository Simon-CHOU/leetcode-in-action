package com.simon.tw;

import com.simon.util.ListNode;

import java.util.ArrayList;
import java.util.List;

class Solution234 {
    ListNode frontP = new ListNode();

    public boolean isPalindrome(ListNode head) {
        frontP = head;
        return isPalindromeCheck(head);
    }

    public boolean isPalindromeCheck(ListNode head) {
        if (head != null) {
            if (!isPalindromeCheck(head.next)) {
                return false;
            }
            if (frontP.val != head.val) {
                return false;
            }
            frontP = frontP.next;// 不要忘记
        }
        return true;
    }

    public boolean isPalindromeArr(ListNode head) {
        List<Integer> cache = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            cache.add(p.val);
            p = p.next;
        }
        // check;
        int left = 0;
        int right = cache.size() - 1;
        while (left < right) {
            if (!cache.get(left).equals(cache.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindromeSB(ListNode head) {
        StringBuffer sb = new StringBuffer();
        ListNode p = head;
        while (p != null) {
            sb.append(p.val);
            p = p.next;
        }
        // check;
        String str = sb.toString();
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}

public class PalindromeLinkedList {
    public static void main(String[] args) {
        // 使用链式构造函数更简洁的写法：
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(2,
                                new ListNode(1)))); // 1-2-2-1
        Solution234 solution234 = new Solution234();
        System.out.println(solution234.isPalindrome(head));
    }
}
