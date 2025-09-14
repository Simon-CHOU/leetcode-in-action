package com.simon.tdd;

import com.simon.util.ListNode;
import com.simon.util.ListNodeUtils;

/**
 * LeetCode 876. 链表的中间结点
 * <p>
 * 问题分析（金字塔原理）：
 * <p>
 * 核心结论：使用快慢指针（Floyd算法）一次遍历找到链表中间节点
 * <p>
 * 支撑论据：
 * 1. 数据结构特点：单向链表无法直接访问中间位置，需要遍历
 * 2. 算法策略：快指针每次走2步，慢指针每次走1步，当快指针到达末尾时慢指针正好在中间
 * 3. 边界处理：奇数长度返回中间节点，偶数长度返回第二个中间节点
 * <p>
 * 算法可视化（ASCII图解）：
 * <pre>
 * 示例1: [1,2,3,4,5] (奇数长度)
 * 初始状态:
 * slow fast
 *  ↓    ↓
 *  1 -> 2 -> 3 -> 4 -> 5 -> null
 * 
 * 第1步: slow走1步，fast走2步
 *       slow    fast
 *        ↓       ↓
 *  1 -> 2 -> 3 -> 4 -> 5 -> null
 * 
 * 第2步: slow走1步，fast走2步
 *            slow         fast
 *             ↓            ↓
 *  1 -> 2 -> 3 -> 4 -> 5 -> null
 * 
 * fast到达末尾，slow指向中间节点3
 * 
 * 示例2: [1,2,3,4,5,6] (偶数长度)
 * 初始状态:
 * slow fast
 *  ↓    ↓
 *  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 * 
 * 第1步: slow走1步，fast走2步
 *       slow    fast
 *        ↓       ↓
 *  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 * 
 * 第2步: slow走1步，fast走2步
 *            slow         fast
 *             ↓            ↓
 *  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 * 
 * 第3步: slow走1步，fast走2步
 *                 slow              fast
 *                  ↓                ↓
 *  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
 * 
 * fast到达末尾，slow指向第二个中间节点4
 * </pre>
 */
public class MiddleOfTheLinkedListTDD {

    /**
     * 使用快慢指针找到链表的中间节点
     * <p>
     * 算法思路：
     * 1. 初始化快慢指针都指向头节点
     * 2. 快指针每次走2步，慢指针每次走1步
     * 3. 当快指针到达链表末尾时，慢指针正好在中间位置
     * <p>
     * 时间复杂度：O(n)，其中n是链表长度，只需要遍历一次
     * 空间复杂度：O(1)，只使用了两个指针变量
     *
     * @param head 链表头节点
     * @return 链表的中间节点（偶数长度时返回第二个中间节点）
     */
    public ListNode middleNode(ListNode head) {
        // 边界检查：空链表或单节点链表
        if (head == null || head.next == null) {
            return head;
        }
        
        // 初始化快慢指针
        ListNode slow = head;
        ListNode fast = head;
        
        // 快指针每次走2步，慢指针每次走1步
        // 当fast到达末尾时，slow正好在中间
        while (fast != null && fast.next != null) {
            slow = slow.next;      // 慢指针走1步
            fast = fast.next.next; // 快指针走2步
        }
        
        return slow;
    }

    /**
     * TDD测试驱动开发 - 主测试方法
     * <p>
     * 测试用例设计原则：
     * 1. 边界用例：空链表、单节点
     * 2. 奇数长度链表：验证返回正中间节点
     * 3. 偶数长度链表：验证返回第二个中间节点
     * 4. 最小有效用例：两个节点的链表
     */
    public static void main(String[] args) {
        MiddleOfTheLinkedListTDD solution = new MiddleOfTheLinkedListTDD();
        
        System.out.println("=== LeetCode 876. 链表的中间结点 - TDD测试 ===");
        System.out.println();
        
        // 测试用例1：空链表
        testCase(solution, null, null, "空链表");
        
        // 测试用例2：单节点链表 [1]
        ListNode single = new ListNode(1);
        testCase(solution, single, single, "单节点链表 [1]");
        
        // 测试用例3：两节点链表 [1,2] - 偶数长度
        ListNode twoNodes = ListNodeUtils.arrayToListNode(new int[]{1, 2});
        ListNode expectedTwo = twoNodes.next; // 节点2
        testCase(solution, twoNodes, expectedTwo, "两节点链表 [1,2]");
        
        // 测试用例4：奇数长度链表 [1,2,3,4,5]
        ListNode oddList = ListNodeUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5});
        ListNode expectedOdd = oddList.next.next; // 节点3
        testCase(solution, oddList, expectedOdd, "奇数长度链表 [1,2,3,4,5]");
        
        // 测试用例5：偶数长度链表 [1,2,3,4,5,6]
        ListNode evenList = ListNodeUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6});
        ListNode expectedEven = evenList.next.next.next; // 节点4
        testCase(solution, evenList, expectedEven, "偶数长度链表 [1,2,3,4,5,6]");
        
        System.out.println();
        System.out.println("=== 算法复杂度分析 ===");
        printComplexityAnalysis();
    }
    
    /**
     * 测试用例执行方法
     *
     * @param solution 解决方案实例
     * @param input 输入链表
     * @param expected 期望结果
     * @param testName 测试用例名称
     */
    private static void testCase(MiddleOfTheLinkedListTDD solution, ListNode input, 
                                ListNode expected, String testName) {
        System.out.println("测试用例: " + testName);
        
        // 打印输入
        if (input == null) {
            System.out.println("输入: null");
        } else {
            System.out.println("输入: " + input.toString());
        }
        
        // 执行算法
        ListNode result = solution.middleNode(input);
        
        // 验证结果
        boolean passed = (expected == null && result == null) || 
                        (expected != null && result != null && expected.val == result.val);
        
        // 打印结果
        if (result == null) {
            System.out.println("输出: null");
        } else {
            System.out.println("输出: 节点值 = " + result.val);
        }
        
        System.out.println("期望: " + (expected == null ? "null" : "节点值 = " + expected.val));
        System.out.println("结果: " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
    }
    
    /**
     * 打印算法复杂度分析
     */
    private static void printComplexityAnalysis() {
        System.out.println("时间复杂度分析：");
        System.out.println("1. 设链表长度为n");
        System.out.println("2. 快指针每次走2步，需要走n/2次到达末尾");
        System.out.println("3. 慢指针每次走1步，总共走n/2步");
        System.out.println("4. 总时间复杂度：T(n) = O(n/2) = O(n)");
        System.out.println();
        
        System.out.println("空间复杂度分析：");
        System.out.println("1. 只使用了两个指针变量：slow和fast");
        System.out.println("2. 不依赖于输入规模，为常数空间");
        System.out.println("3. 总空间复杂度：S(n) = O(1)");
        System.out.println();
        
        System.out.println("算法正确性证明：");
        System.out.println("设链表长度为n：");
        System.out.println("- 当n为奇数时：快指针走(n-1)/2步后到达末尾，慢指针走(n-1)/2步，指向第(n+1)/2个节点（中间节点）");
        System.out.println("- 当n为偶数时：快指针走n/2步后到达末尾，慢指针走n/2步，指向第n/2+1个节点（第二个中间节点）");
        System.out.println("符合题目要求：偶数长度时返回第二个中间节点");
    }
}
