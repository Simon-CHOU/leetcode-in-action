package com.simon.interview.hwdp;
import java.util.Scanner;

/**
 * 进制转换
 * 对于给定的十六进制数，输出其对应的十进制表示。
 * 输入例子：0xFA93
 * 输出例子：64147
 */
public class BaseConversion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String hex = in.nextLine();
            System.out.println(hexToDecimal(hex));
        }
    }
    public static int hexToDecimal(String hex) {
        // 去掉前缀 "0x" 或 "0X"
        if (hex.startsWith("0x") || hex.startsWith("0X")) {
            hex = hex.substring(2);
        }
        // 将 16 进制字符串转换为 10 进制整数
        return Integer.parseInt(hex, 16);
    }
}
