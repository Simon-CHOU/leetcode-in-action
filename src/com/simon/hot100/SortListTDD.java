package com.simon.hot100;

import com.simon.util.ListNode;
import com.simon.util.ListNodeUtils;
import java.util.Arrays;

public class SortListTDD {
    /**
     * 148. 排序链表<p>
     * 使用自底向上的归并排序对单链表进行原地排序，时间复杂度 O(n log n)，空间复杂度 O(1)。<p>
     * 关键思想（金字塔原理）：<p>
     * 1. 顶层结论：链表最优排序选择归并排序；自底向上的迭代版可实现常数额外空间。<p>
     * 2. 支撑逻辑：链表随机访问效率低，不适合快速排序的分区；归并排序只需要顺序访问并在合并时重连指针。<p>
     * 3. 实施要点：逐步扩大子段长度 size=1,2,4,...，每轮将相邻两段合并；通过拆分函数 split 和合并函数 merge 精准控制子链。<p>
     * 算法单步可视化（ASCII）：<p>
     * <pre>
     * 初始：head 指向无序链表
     * size=1 时按每 1 个元素分段并两两合并：
     * [n1] [n2] [n3] [n4] ...  => 合并相邻对，得到排序的 2 长度段
     *     tail
     *      ↓        cur
     * dummy -> n1 -> n2 -> n3 -> n4 -> ... -> null
     * 变量含义：dummy 伪头；tail 指向已合并段的尾；cur 指向尚未处理的起点。<p>
     * 每次循环：
     * left = cur
     * right = split(left, size)    // 截取左段 size 长并断开
     * cur = split(right, size)     // 截取右段 size 长并断开，cur 指向下一对的起点
     * (mh, mt) = merge(left, right)// 返回合并后段的头尾
     * tail.next = mh; tail = mt    // 连接到结果并移动尾指针
     * </pre>
     * @param head 未排序的链表头
     * @return 排序后的链表头
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int n = length(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (int size = 1; size < n; size <<= 1) {
            ListNode tail = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, size);
                cur = split(right, size);
                ListNode[] merged = merge(left, right);
                tail.next = merged[0];
                tail = merged[1];
            }
        }
        return dummy.next;
    }

    /**
     * 计算链表长度<p>
     * @param head 头结点
     * @return 长度
     */
    private int length(ListNode head) {
        int count = 0;
        ListNode p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    /**
     * 从 head 起截取长度为 size 的子链并断开，返回剩余部分的起点<p>
     * 若剩余不足 size，则返回 null。<p>
     * @param head 子链起点
     * @param size 截取长度
     * @return 下一段起点
     */
    private ListNode split(ListNode head, int size) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        for (int i = 1; i < size && p.next != null; i++) {
            p = p.next;
        }
        ListNode next = p.next;
        p.next = null;
        return next;
    }

    /**
     * 合并两条已排序链表，返回合并后段的头与尾<p>
     * @param l1 左段
     * @param l2 右段
     * @return {mergedHead, mergedTail}
     */
    private ListNode[] merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        ListNode p = l1;
        ListNode q = l2;
        while (p != null && q != null) {
            if (p.val <= q.val) {
                tail.next = p;
                p = p.next;
            } else {
                tail.next = q;
                q = q.next;
            }
            tail = tail.next;
        }
        tail.next = (p != null) ? p : q;
        while (tail.next != null) {
            tail = tail.next;
        }
        return new ListNode[]{dummy.next, tail};
    }

    public static void main(String[] args) {
        // TDD: 构造测试用例并打印 PASS/FAIL
        SortListTDD solution = new SortListTDD();
        runTest(solution, new int[]{}, "空链表");
        runTest(solution, new int[]{1}, "单元素");
        runTest(solution, new int[]{2, 1}, "两元素逆序");
        runTest(solution, new int[]{4, 2, 1, 3}, "示例用例 4,2,1,3");
        runTest(solution, new int[]{-1, 5, 3, 4, 0}, "示例用例 -1,5,3,4,0");
        runTest(solution, new int[]{1, 1, 1, 1}, "全部相等");
        runTest(solution, new int[]{5, 4, 3, 2, 1}, "完全逆序");
        runTest(solution, new int[]{3, 3, 2, 1, 2, 4, 3}, "含重复元素");

        System.out.println("=== 所有用例已执行 ===");
    }

    /**
     * 运行单个测试用例并打印断言结果<p>
     * @param solution 算法实例
     * @param input 输入数组
     * @param name 用例名称
     */
    private static void runTest(SortListTDD solution, int[] input, String name) {
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        ListNode head = ListNodeUtils.arrayToListNode(input);
        ListNode sorted = solution.sortList(head);
        int[] actual = ListNodeUtils.listNodeToArray(sorted);
        boolean pass = Arrays.equals(expected, actual);
        System.out.println((pass ? "PASS" : "FAIL") + " - " + name + " -> expected=" + Arrays.toString(expected) + ", actual=" + Arrays.toString(actual));
    }
    
}
