package com.simon.hot100;

import java.util.Arrays;
import java.util.Objects;

/**
 * 43. 字符串相乘
 * <a href="https://leetcode.cn/problems/multiply-strings/description/">...</a>
 */
public class MultiplyStringsTDD {
    public String multiply(String num1, String num2) {

        int n1 = num1.length() ;
        int n2 = num2.length() ;
//        int carry = 0 ;
//        int sum = 0 ;
        int res[] = new int[num1.length()+num2.length()];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                // 1. 纯粹的单步乘法，不要乘 10^k
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

// 2. 算上低位格子（右边格子）里原有的累加值
                int sum = mul + res[i + j + 1];

// 3. 低位格子只留个位数
                res[i + j + 1] = sum % 10;

// 4. 高位格子（左边格子）直接「累加」进位
// 注意：这里是 +=，因为高位格子可能在之前的循环里已经有数了
                res[i + j] += sum / 10;
            }
        }
        System.out.println(Arrays.toString(res));
        // 消除前导零 res

        StringBuilder clr = new StringBuilder();
        boolean flag= true;
        for(int i : res) {
            //高位到低位
            if(flag && i == 0 ) {
                flag =false;
            }else {
                clr.append(i);
            }
        }
        return clr.toString();
    }

    static void test(String exp, String act) {
        if(Objects.equals(exp, act)) {
            System.out.println("PASS exp="+exp+" , act="+act);
        } else {
            System.out.println("FAIL exp="+exp+" , act="+act);
        }
    }
    public static void main(String[] args) {
        //输入: num1 = "2", num2 = "3"
        //输出: "6"

        MultiplyStringsTDD solution = new MultiplyStringsTDD();
        test("6" , solution.multiply("2", "3")) ;

        // 输入: num1 = "123", num2 = "456"
        //输出: "56088"
        test("56088" , solution.multiply("123", "456"));
    }
}
