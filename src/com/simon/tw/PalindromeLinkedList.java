package com.simon.tw;

import com.simon.util.ListNode;

class Solution234 {
    public boolean isPalindrome(ListNode head) {
        StringBuffer sb = new StringBuffer();
        ListNode p  = head;
        while(p!=null) {
            sb.append(p.val);
            p = p.next;
        }
        // check;
        String str = sb.toString();
        for(int i = 0; i< str.length() /2; i++) {
            if(str.charAt(i) != str.charAt(str.length()  - i - 1)) {
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
