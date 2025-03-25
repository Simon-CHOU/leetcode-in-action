package com.simon.tw;

import com.simon.util.ListNode;

import java.util.ArrayList;
import java.util.List;

class Solution234 {
    /**
     * 找到前半部分链表的尾节点。
     * 反转后半部分链表。
     * 判断是否回文。
     * 恢复链表。
     * 返回结果
     * @param head 头结点
     * @return 是否回文
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true; // 不是false
//        //得到反转
//        ListNode node = getHalfHead(head);//不是要把链表一分为二
//        ListNode node2 = getHalfTail(head);
        // 找到前一半链表的尾结点，并反转之
        ListNode node = getHalfHead(head);
        ListNode node2 = reverseHalfHead(node.next);
        //判断是否正确
        ListNode p = head; //node;
        ListNode q = node2;
//        while(p.next!=null && q.next!=null) {
        while(q!=null) {
            //这里的 while 循环条件应该是 q != null，而不是 q.next != null。原因如下：
            //
            //q 是反转后的后半部分链表的头。如果后半部分链表只有一个节点（如例子中的 1->2，反转后半部分后，q 指向 2），q.next 就已经是 null 了。你的循环条件会导致直接跳过比较。
            //比较应该持续到 q 的末尾。 q 代表的是后半部分，我们需要完整地比较后半部分和前半部分。
            if(p.val != q.val) return false; // 如果要还原，则不能直接退出，
            p = p.next;
            q = q.next; // 别忘了步进
        }
        // 还原链表？只是为了判断出结果，甚至可以不用还原？

        return  true;
    }

    ListNode getHalfHead(ListNode head){
       ListNode fast = head ;// 两倍步进。fast 触底，slow 就是一半。
       ListNode slow = head ;
//       while(fast.next != null) {
        while(fast.next != null && fast.next.next!= null) {
           fast = fast.next.next;
           slow = slow.next;
       }
       return slow;
    }
    ListNode reverseHalfHead(ListNode head){
          ListNode prev = null;
          ListNode curr = head;
          while(curr != null) {
              ListNode tmp = curr.next;
              curr.next = prev;
              prev = curr;
              curr = tmp;
          }
//          return curr;
        return prev; // why?
    }

    /***/
    ListNode frontP = new ListNode();

    public boolean isPalindromeRecur(ListNode head) {
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

        ListNode head1 = new ListNode(1,
                new ListNode(2,null)); // 1-2
        boolean checkRes = solution234.isPalindrome(head1);
        assert !checkRes; //false
        System.out.println(checkRes);
    }
}
