package com.simon.interview.hwdp;

import java.util.Scanner;

// 汽水瓶
// 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
// 小张手上有 n 个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水
public class SodaBottle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if (a == 0) return;
            System.out.println(MaxSodas(a));
        }
    }

    private static int MaxSodas(int n) {
        int drinkedSodas = 0;
        int emptyBottles = n;
        while (emptyBottles >= 2) {
            if (emptyBottles == 2) { // 隐含条件只能借1个，如果不是实现已经有2个，借了也白借
                drinkedSodas++;
                break;
            }
            int exchanged = emptyBottles / 3;
            drinkedSodas += exchanged;
            // renew empty
            emptyBottles = emptyBottles % 3 + exchanged; // 不是 emptyBottles/3
        }

        return drinkedSodas;
    }
}
/**
 * 输入例子：
 *
 * 3
 * 10
 * 81
 * 0
 *
 * 输出例子：
 *
 * 1
 * 5
 * 40
 */
