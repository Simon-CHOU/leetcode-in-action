package com.simon.hot100;

import com.simon.util.ListNode;
import com.simon.util.ListNodeUtils;
import java.util.Arrays;
/**
 * 148. 排序链表 —— 归并排序（自顶向下）
 * <p>
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(log n) 递归栈
 */
public class SortListTDD {

    /**
     * 对链表进行升序排序并返回新头结点。
     *
     * @param head 链表头结点
     * @return 排序后的链表头结点
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;                       // 0 或 1 个节点已天然有序
        }

        // 1. 快慢指针找中点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;                      // 斩断成左右两半

        // 2. 分治排序
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        // 3. 合并两条有序链表
        return merge(left, right);
    }

    /**
     * 合并两个已排序链表。
     *
     * @param l1 链表1头结点
     * @param l2 链表2头结点
     * @return 合并后的链表头结点
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    /* ---------------- TDD 验证区 ---------------- */
    public static void main(String[] args) {
        SortListTDD t = new SortListTDD();

        // 测试用例 1: [4,2,1,3]
        ListNode head = createList(new int[]{4, 2, 1, 3});
        ListNode sorted = t.sortList(head);
        check(sorted, new int[]{1, 2, 3, 4});

        // 测试用例 2: 空链
        check(t.sortList(null), new int[]{});

        // 测试用例 3: 单节点
        check(t.sortList(createList(new int[]{5})), new int[]{5});

        // 测试用例 4: 已逆序
        check(t.sortList(createList(new int[]{5, 4, 3, 2, 1})), new int[]{1, 2, 3, 4, 5});

        System.out.println("All tests done.");
    }

    /* ---------- 辅助工具 ---------- */
    private static ListNode createList(int[] vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    private static void check(ListNode head, int[] expect) {
        int i = 0;
        for (ListNode p = head; p != null; p = p.next, i++) {
            if (i >= expect.length || p.val != expect[i]) {
                throw new RuntimeException("FAIL at index " + i);
            }
        }
        if (i != expect.length) {
            throw new RuntimeException("FAIL: length mismatch");
        }
        System.out.println("PASS");
    }
}