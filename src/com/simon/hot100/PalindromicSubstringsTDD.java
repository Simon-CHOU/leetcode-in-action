package com.simon.hot100;
/**
 * LeetCode 647: 回文子串
 * <p>
 * 我们使用“中心扩展”算法来计算一个字符串中所有回文子串的数量。
 * <a href="https://gemini.google.com/share/44de506d9eea">...</a>
 * @author CodeGuru
 */
public class PalindromicSubstringsTDD {
    /**
     * 计算给定字符串s中回文子串的数量。
     * <p>
     * 算法思想：
     * 1. 遍历字符串中的每一个字符（索引 i）。
     * 2. 对于每一个索引 i，我们考虑两种情况的回文中心：
     * a. 奇数长度的回文串，中心点为 [i] 本身。
     * b. 偶数长度的回文串，中心点为 [i] 和 [i+1] 之间。
     * 3. 我们使用一个辅助函数 `expandAroundCenter` 来从这两个中心向两边扩展，
     * 并计算能找到的回文串数量。
     *
     * @param s 输入字符串，非 null
     * @return 回文子串的总数
     */
    public int countSubstrings(String s) {
        // 边界情况处理
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int totalCount = 0;
        // 遍历所有可能的中心点
        for (int i = 0; i < s.length(); i++) {
            // 1. 奇数长度的回文串 (例如: "aba", 中心是 'b' at index i)
            totalCount += expandAroundCenter(s, i, i);

            // 2. 偶数长度的回文串 (例如: "abba", 中心是 'b'和'b'之间, at index i, i+1)
            totalCount += expandAroundCenter(s, i, i + 1);
        }

        return totalCount;
    }

    /**
     * 辅助函数：从指定的中心 (left, right) 向两边扩展，计算以此为中心的回文串数量。
     *
     * @param s     原始字符串
     * @param left  中心的左边界 (对于奇数情况, left == right)
     * @param right 中心的右边界
     * @return 从此中心扩展出的回文串数量
     */
    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;

        // 持续向两边扩展，同时满足以下条件：
        // 1. left 指针没有越过左边界 (>= 0)
        // 2. right 指针没有越过右边界 (< s.length())
        // 3. 左右指针指向的字符必须相等
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // 每当满足条件，就意味着我们找到了一个以此为中心的回文串
            // (例如, "a", "bab", "cbabc" 都是从 'b' 中心扩展出来的)
            count++;

            // 向外扩展
            left--;
            right++;
        }
        return count;
    }

    /**
     * TDD (Test-Driven Development) 测试入口。
     * <p>
     * 使用 main 方法代替 JUnit 进行简单的单元测试。
     * 打印 PASS / FAIL 来验证算法的正确性。
     *
     * @param args 命令行参数 (未使用)
     */
    public static void main(String[] args) {
        PalindromicSubstringsTDD solution = new PalindromicSubstringsTDD();

        // 定义测试用例和预期结果
        // {输入字符串, 预期回文串数量}
        Object[][] testCases = {
                {"aaa", 6},       // "a", "a", "a", "aa", "aa", "aaa"
                {"abc", 3},       // "a", "b", "c"
                // --- BUG FIX ---
                // 原: {"abaab", 7}
                // 订正: {"abaab", 8} (修复了错误的预期值，正确的个数是8)
                // 8个: "a", "b", "a", "a", "b", "aba", "aa", "baab"
                {"abaab", 8},
                {"racecar", 10},  // "r", "a", "c", "e", "c", "a", "r", "cec", "ece", "racecar"
                {"a", 1},         // "a"
                {"", 0}          // 空字符串
        };

        System.out.println("Running PalindromicSubstrings Tests...");
        System.out.println("---------------------------------------");

        int failedCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            String s = (String) testCases[i][0];
            int expected = (int) testCases[i][1];
            int actual = solution.countSubstrings(s);

            if (actual == expected) {
                System.out.printf("[Test %d] Input: \"%s\" -> PASS (Expected: %d, Got: %d)%n",
                        i + 1, s, expected, actual);
            } else {
                System.out.printf("[Test %d] Input: \"%s\" -> FAIL (Expected: %d, Got: %d)%n",
                        i + 1, s, expected, actual);
                failedCount++;
            }
        }

        System.out.println("---------------------------------------");
        if (failedCount == 0) {
            System.out.println("All tests passed! (SUCCESS)");
        } else {
            System.out.printf("%d tests failed. (FAILURE)%n", failedCount);
        }
    }
}
