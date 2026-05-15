package com.simon.hot100;

import java.util.Objects;

/**
 * 43. 字符串相乘
 * <a href="https://leetcode.cn/problems/multiply-strings/description/">...</a>
 */
public class MultiplyStringsTDD {
    public String multiply(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0 ;
        int sum = 0 ;
        StringBuilder res = new StringBuilder();
        while( i>=0 || j>=0 || carry >0) {
            if(i>0) {sum * (res.charAt(i)--'0') * }
            if(j>0) {res.charAt(j)--'0'}
        }
        return "0";
    }

    static void test(String exp, String act) {
        if(Objects.equals(exp, act)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
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
