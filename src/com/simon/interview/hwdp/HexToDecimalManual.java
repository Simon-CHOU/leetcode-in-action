package com.simon.interview.hwdp;
// 进制转换 手工实现，不使用 Integer#parseInt
public class HexToDecimalManual {
    public static int hexToDecimal(String hex) {
        // 去掉前缀 "0x" 或 "0X"
        if (hex.startsWith("0x") || hex.startsWith("0X")) {
            hex = hex.substring(2);
        }

        int decimal = 0;
        int length = hex.length();

        // 从最高位开始逐位计算
        for (int i = 0; i < length; i++) {
            char ch = hex.charAt(i);
            int digit = hexCharToDigit(ch); // 将字符转换为对应的数值
            decimal = decimal * 16 + digit; // 累加计算
        }

        return decimal;
    }

    // 将 16 进制字符转换为对应的数值
    private static int hexCharToDigit(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0'; // 0-9 直接转换为数值
        } else if (ch >= 'A' && ch <= 'F') {
            return 10 + (ch - 'A'); // A-F 转换为 10-15
        } else if (ch >= 'a' && ch <= 'f') {
            return 10 + (ch - 'a'); // a-f 转换为 10-15
        } else {
            throw new IllegalArgumentException("Invalid hex character: " + ch);
        }
    }

    public static void main(String[] args) {
        String hex = "0xFA93"; // 输入例子
        int decimal = hexToDecimal(hex); // 转换为 10 进制
        System.out.println(decimal); // 输出结果
    }
}

