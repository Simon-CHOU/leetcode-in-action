package com.simon.hot100;

/**
 * 394. 字符串解码 - TDD实现
 * <p>
 * 使用栈数据结构处理嵌套的编码字符串
 * <p>
 * 时间复杂度：O(n)，空间复杂度：O(n)
 *
 * @author Simon
 * @version 1.0
 */
public class DecodeStringTDD {

    /**
     * 解码字符串
     * <p>
     * 处理形如 k[encoded_string] 的编码格式，支持嵌套
     *
     * @param s 编码字符串
     * @return 解码后的字符串
     */
    public String decodeString(String s) {
        // 使用双栈：数字栈和字符串栈
        java.util.Stack<Integer> countStack = new java.util.Stack<>();
        java.util.Stack<StringBuilder> stringStack = new java.util.Stack<>();
        
        StringBuilder currentString = new StringBuilder();
        int currentCount = 0;
        
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // 构建多位数
                currentCount = currentCount * 10 + (ch - '0');
            } else if (ch == '[') {
                // 遇到左括号，将当前计数和字符串入栈
                countStack.push(currentCount);
                stringStack.push(currentString);
                
                // 重置当前状态
                currentCount = 0;
                currentString = new StringBuilder();
            } else if (ch == ']') {
                // 遇到右括号，出栈并重复拼接
                StringBuilder temp = currentString;
                int repeatTimes = countStack.pop();
                currentString = stringStack.pop();
                
                // 重复拼接字符串 - 优化版本
                String repeated = temp.toString().repeat(repeatTimes);
                currentString.append(repeated);
            } else {
                // 普通字符，直接添加到当前字符串
                currentString.append(ch);
            }
        }
        
        return currentString.toString();
    }
    
    /**
     * 测试用例运行器
     * <p>
     * 使用TDD模式验证算法正确性
     */
    public static void runTests() {
        DecodeStringTDD decoder = new DecodeStringTDD();
        
        // 测试用例1：基础情况
        testCase(decoder, "3[a]2[bc]", "aaabcbc", "Test 1");
        
        // 测试用例2：嵌套情况
        testCase(decoder, "3[a2[c]]", "accaccacc", "Test 2");
        
        // 测试用例3：多重嵌套
        testCase(decoder, "2[abc]3[cd]ef", "abcabccdcdcdef", "Test 3");
        
        // 测试用例4：单字符
        testCase(decoder, "abc3[de]", "abcdedede", "Test 4");
        
        // 测试用例5：复杂嵌套
        testCase(decoder, "3[z]2[2[y]pq4[2[jk]e1[f]]]ef", 
                 "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef", "Test 5");
    }
    
    /**
     * 单个测试用例验证
     *
     * @param decoder 解码器实例
     * @param input 输入字符串
     * @param expected 期望输出
     * @param testName 测试名称
     */
    private static void testCase(DecodeStringTDD decoder, String input, String expected, String testName) {
        String result = decoder.decodeString(input);
        boolean passed = result.equals(expected);
        
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Input: " + input);
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual: " + result);
        }
    }
    
    /**
     * 可视化算法执行过程
     * <p>
     * 使用ASCII字符画展示栈状态变化
     */
    public static void visualizeAlgorithm() {
        System.out.println("=== 算法可视化演示：输入 '3[a2[c]]' ===");
        
        String input = "3[a2[c]]";
        System.out.println("输入字符串: " + input);
        System.out.println("执行过程:");
        
        // 初始状态
        System.out.println("初始状态:");
        printState("", "", "", "", "开始");
        
        // 模拟执行过程
        String[] steps = {
            "读取 '3' (数字)",
            "读取 '[' (入栈)", 
            "读取 'a' (字符)",
            "读取 '2' (数字)",
            "读取 '[' (入栈)",
            "读取 'c' (字符)", 
            "读取 ']' (出栈: 2 * 'c' = 'cc')",
            "读取 ']' (出栈: 3 * 'acc' = 'accaccacc')"
        };
        
        String[] countStacks = {
            "3", "3", "3", "3,2", "3,2", "3,2", "3", ""
        };
        
        String[] stringStacks = {
            "", "", "a", "a", "a,\"\"", "a,\"\"", "a", ""
        };
        
        String[] currentStrings = {
            "", "", "a", "a", "", "c", "cc", "accaccacc"
        };
        
        for (int i = 0; i < steps.length; i++) {
            printState(countStacks[i], stringStacks[i], currentStrings[i], input.charAt(Math.min(i, input.length()-1)) + "", steps[i]);
        }
    }
    
    /**
     * 打印当前状态
     */
    private static void printState(String countStack, String stringStack, String currentString, String currentChar, String step) {
        System.out.println("步骤: " + step);
        System.out.println("当前字符: '" + currentChar + "'");
        System.out.println("数字栈: [" + countStack + "]");
        System.out.println("字符串栈: [" + stringStack + "]");
        System.out.println("当前字符串: \"" + currentString + "\"");
        System.out.println("-".repeat(40));
    }
    
    /**
     * 时间复杂度分析
     */
    public static void analyzeTimeComplexity() {
        System.out.println("=== 时间复杂度分析 ===");
        System.out.println("1. 遍历字符串: O(n)");
        System.out.println("2. 栈操作(入栈/出栈): 每个字符最多入栈出栈一次 O(n)");
        System.out.println("3. 字符串拼接: 使用String.repeat()方法 O(n)");
        System.out.println("总时间复杂度: O(n) + O(n) + O(n) = O(n)");
        System.out.println("空间复杂度: O(n) - 栈的深度最多为嵌套层数");
    }
    
    /**
     * 主方法 - 运行所有测试和演示
     */
    public static void main(String[] args) {
        System.out.println("=== 394. 字符串解码 TDD测试 ===");
        
        // 运行测试用例
        runTests();
        
        // 可视化算法过程
        visualizeAlgorithm();
        
        // 时间复杂度分析
        analyzeTimeComplexity();
        
        System.out.println("=== 测试完成 ===");
    }
}
