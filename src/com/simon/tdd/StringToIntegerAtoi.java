package com.simon.tdd;

public class StringToIntegerAtoi {

    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;

        int index = 0;
        int sign = 1;
        long result = 0;

        // 1. Skip whitespace
        index = skipWhitespace(s, index);

        // 2. Check sign
        if (index < s.length() && isSignCharacter(s.charAt(index))) {
            sign = getSign(s, index);
            index++;
        }

        // 3. Convert digits
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            // Overflow check
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            index++;
        }

        return sign * (int) result;
    }

    private int skipWhitespace(String s, int index) {
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        return index;
    }

    /**
     * 检查字符是否为符号字符（+ 或 -）。
     * 
     * @param c 需要检查的字符
     * @return 如果是符号字符则返回true，否则返回false
     */
    private boolean isSignCharacter(char c) {
        return c == '+' || c == '-';
    }

    /**
     * 获取字符串中指定位置的符号值。
     * 
     * @param s     包含符号的字符串
     * @param index 符号在字符串中的位置索引
     * @return 如果符号是'-'则返回-1，否则返回1
     */
    private int getSign(String s, int index) {
        return s.charAt(index) == '-' ? -1 : 1;
    }

    // ========== TDD 测试代码 ==========
    public static void main(String[] args) {
        StringToIntegerAtoi solution = new StringToIntegerAtoi();

        runTest(solution, "42", 42);
        runTest(solution, "   -42", -42);
        runTest(solution, "4193 with words", 4193);
        runTest(solution, "words and 987", 0);
        runTest(solution, "-91283472332", Integer.MIN_VALUE); // Overflow
        runTest(solution, "", 0);
        runTest(solution, "+1", 1);
        runTest(solution, "  +0a123", 0);
        runTest(solution, "   +0 123", 0);
        runTest(solution, "2147483648", Integer.MAX_VALUE); // Overflow to MAX
        runTest(solution, "-2147483649", Integer.MIN_VALUE); // Overflow to MIN
        runTest(solution, "  0000000000012345", 12345);
        runTest(solution, "+-12", 0);
        runTest(solution, "  +abc123", 0);
        runTest(solution, "-5-", -5);
        runTest(solution, "  2  ", 2);
    }

    private static void runTest(StringToIntegerAtoi solution, String input, int expected) {
        int actual = solution.myAtoi(input);
        if (actual != expected) {
            System.out.println("❌ Failed: Input = \"" + input + "\", Expected = " + expected + ", Got = " + actual);
        } else {
            System.out.println("✅ Passed: \"" + input + "\" ➝ " + expected);
        }
    }
}