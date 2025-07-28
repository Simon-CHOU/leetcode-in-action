package com.simon.tdd;

/**
 * 1456. 定长子串中元音的最大数目
 * 
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 */
public class MaxVowelsTDD {

    /**
     * 找出字符串中长度为k的子串中包含的最大元音字母数量
     * 
     * <p>核心知识点分层：</p>
     * <ol>
     *   <li><b>Java语言基础</b>
     *     <ul>
     *       <li>基本数据类型（int, boolean, char）</li>
     *       <li>控制结构（for循环、if条件判断）</li>
     *       <li>字符串操作（charAt方法）</li>
     *       <li>数学函数（Math.max）</li>
     *     </ul>
     *   </li>
     *   <li><b>数据结构知识</b>
     *     <ul>
     *       <li>字符串（String）作为字符序列的表示</li>
     *       <li>计数器变量的使用</li>
     *     </ul>
     *   </li>
     *   <li><b>算法设计</b>
     *     <ul>
     *       <li>滑动窗口技术（Sliding Window）
     *         <ul>
     *           <li>窗口初始化：计算第一个窗口内的元音数量</li>
     *           <li>窗口滑动：移除左边元素，添加右边新元素</li>
     *           <li>实时维护窗口内元音计数</li>
     *         </ul>
     *       </li>
     *       <li>优化策略：避免重复计算，将时间复杂度从O(n*k)降低到O(n)</li>
     *     </ul>
     *   </li>
     *   <li><b>问题求解思路</b>
     *     <ul>
     *       <li>识别元音字母（a, e, i, o, u）</li>
     *       <li>在固定长度的子串中统计特定字符的数量</li>
     *       <li>维护所有可能子串中统计值的最大值</li>
     *     </ul>
     *   </li>
     * </ol>
     * 
     * <p>算法流程：</p>
     * <ol>
     *   <li>初始化窗口：计算字符串前k个字符中元音的数量</li>
     *   <li>滑动窗口：从第k个字符开始遍历到字符串末尾
     *     <ul>
     *       <li>移除窗口左侧即将离开的字符（如果是元音则计数减1）</li>
     *       <li>加入窗口右侧新进入的字符（如果是元音则计数加1）</li>
     *       <li>更新记录的最大元音数量</li>
     *     </ul>
     *   </li>
     *   <li>返回记录的最大元音数量</li>
     * </ol>
     * 
     * @param s 输入字符串
     * @param k 子串长度
     * @return 最大元音字母数量
     */
    public int maxVowels(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }

        int maxCount = 0;
        int currentCount = 0;

        // 计算第一个窗口中的元音数量
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currentCount++;
            }
        }
        maxCount = currentCount;

        // 使用滑动窗口更新最大元音数量
        for (int i = k; i < s.length(); i++) {
            // 移除窗口左边的字符（如果它是元音）
            // 为什么是 i-k ?
            // 窗口长度为k，当右边界索引为i时，左边界索引为 i-k
            // 例如：k=3, 当前窗口右端在索引5(i=5)，那么左端在索引2(i-k = 5-3 = 2)
            //      字符串: a b c i i i d e f
            //      索引:   0 1 2 3 4 5 6 7 8
            //                        ^   ^
            //                        |   |
            //                     左端  右端
            //                     i-k    i
            if (isVowel(s.charAt(i - k))) {
                currentCount--;
            }
            // 添加窗口右边的新字符（如果它是元音）
            if (isVowel(s.charAt(i))) {
                currentCount++;
            }
            // 更新最大值
            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }

    /**
     * 判断字符是否为元音字母
     * 
     * @param c 待判断字符
     * @return 如果是元音字母返回true，否则返回false
     */
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // 测试用例
    public static void main(String[] args) {
        MaxVowelsTDD solution = new MaxVowelsTDD();
        
        // 测试用例1: s = "abciiidef", k = 3
        // 预期输出: 3 (子字符串"iii"包含3个元音字母)
        System.out.println("Test 1: " + solution.maxVowels("abciiidef", 3)); // 应输出 3
        
        // 测试用例2: s = "aeiou", k = 2
        // 预期输出: 2 (任意长度为2的子串都包含2个元音字母)
        System.out.println("Test 2: " + solution.maxVowels("aeiou", 2)); // 应输出 2
        
        // 测试用例3: s = "leetcode", k = 3
        // 预期输出: 2 (子字符串"lee"、"eet"和"ode"都包含2个元音字母)
        System.out.println("Test 3: " + solution.maxVowels("leetcode", 3)); // 应输出 2
        
        // 测试用例4: s = "rhythms", k = 4
        // 预期输出: 0 (字符串中不含有元音字母)
        System.out.println("Test 4: " + solution.maxVowels("rhythms", 4)); // 应输出 0
        
        // 测试用例5: s = "tryhard", k = 4
        // 预期输出: 1
        System.out.println("Test 5: " + solution.maxVowels("tryhard", 4)); // 应输出 1
    }

    /**
     * 用例 "abciiidef", 3 的算法执行过程演示:
     *
     * 字符串: a b c i i i d e f
     * 索引:  0 1 2 3 4 5 6 7 8
     *
     * 初始窗口 (i=0..2):
     *        +-----+
     *        |a b c| i i i d e f    => 窗口内元音: a(是) b(否) c(否) => currentCount = 1
     *        +-----+
     *        maxCount = 1
     *
     * 第1步 (i=3):
     *          +-----+
     *        a |b c i| i i d e f    => 移除b(否) 添加i(是) => currentCount = 1-0+1 = 2
     *          +-----+
     *        maxCount = 2
     *
     * 第2步 (i=4):
     *            +-----+
     *        a b |c i i| i d e f    => 移除c(否) 添加i(是) => currentCount = 2-0+1 = 3
     *            +-----+
     *        maxCount = 3
     *
     * 第3步 (i=5):
     *              +-----+
     *        a b c |i i i| d e f    => 移除i(是) 添加i(是) => currentCount = 3-1+1 = 3
     *              +-----+
     *        maxCount = 3
     *
     * 第4步 (i=6):
     *                +-----+
     *        a b c i |i i d| e f    => 移除i(是) 添加d(否) => currentCount = 3-1+0 = 2
     *                +-----+
     *        maxCount = 3 (不变)
     *
     * 第5步 (i=7):
     *                  +-----+
     *        a b c i i |i d e| f    => 移除i(是) 添加e(是) => currentCount = 2-1+1 = 2
     *                  +-----+
     *        maxCount = 3 (不变)
     *
     * 第6步 (i=8):
     *                    +-----+
     *        a b c i i i |d e f|    => 移除d(否) 添加f(否) => currentCount = 2-0+0 = 2
     *                    +-----+
     *        maxCount = 3 (不变)
     *
     * 算法结束，返回 maxCount = 3
     *
     * 关键数据结构状态变化:
     * 1. 窗口边界: 通过循环索引 i 和窗口大小 k 动态维护
     * 2. 元音计数: currentCount 实时维护窗口内元音数量
     * 3. 最大值记录: maxCount 记录过程中遇到的最大元音数
     */
}