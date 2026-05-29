package com.simon.pat;

public class ThousandSeparator {
    public static void printWithCommas(String numStr) {
        int len = numStr.length();
        for (int i = 0; i < len; i++) {
            System.out.print(numStr.charAt(i));

            // 核心逻辑：判断是否需要输出逗号
            if ((i + 1) % 3 == len % 3 && (i + 1) < len) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printWithCommas("1");         // 1
        printWithCommas("12");        // 12
        printWithCommas("123");       // 123
        printWithCommas("1234");      // 1,234
        printWithCommas("12345");     // 12,345
        printWithCommas("123456");    // 123,456
        printWithCommas("1234567");   // 1,234,567
    }
}
