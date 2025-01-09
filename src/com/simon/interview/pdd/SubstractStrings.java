package com.simon.interview.pdd;

import java.math.BigDecimal;

public class SubstractStrings {
    //请实现一个Java代码方法，  String substring (String num1, String num2) 两个大数字符串相减并返回差的字符串结果。要求：数据范围：两个数字的长度都满足 1<=num1,num2 <=10^5, ，数字中仅包含 0<=val<=9 ，第一位不可能是0 。 输入示例："429018691214", "3278729808879"  返回 "-2849711117665"
    public static void main(String[] args) {
        SubstractStrings so = new SubstractStrings();
        String re = so.substring("100", "1");
        System.out.println(re);
        assert re.equals("99");

        String re2 = so.substring("429018691214", "3278729808879");
        System.out.println(re2);
        assert re2.equals("-2849711117665");
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param num1 string字符串
     * @param num2 string字符串
     * @return string字符串
     */
    public static String substring(String num1, String num2) {
        boolean isNegative = false;
        if (compare(num1, num2) < 0) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
            isNegative = true;
        }
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry!= 0) {
            int digit1 = i >= 0? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0? num2.charAt(j) - '0' : 0;
            int sub = digit1 - digit2 - carry;
            carry = 0;
            if (sub < 0) {
                sub += 10;
                carry = 1;
            }
            result.insert(0, sub);
            i--;
            j--;
        }
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }
        if (isNegative) {
            result.insert(0, '-');
        }
        return result.toString();
    }

    public static int compare(String num1, String num2) {
        if (num1.length()!= num2.length()) {
            return num1.length() - num2.length();
        }
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i)!= num2.charAt(i)) {
                return num1.charAt(i) - num2.charAt(i);
            }
        }
        return 0;
    }
}

