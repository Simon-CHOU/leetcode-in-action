package com.simon.interview.pdd;

import java.math.BigDecimal;

public class SubstractStringsBigDecimal {
    //请实现一个Java代码方法，  String substring (String num1, String num2) 两个大数字符串相减并返回差的字符串结果。要求：数据范围：两个数字的长度都满足 1<=num1,num2 <=10^5, ，数字中仅包含 0<=val<=9 ，第一位不可能是0 。 输入示例："429018691214", "3278729808879"  返回 "-2849711117665" 
    public static void main(String[] args) {
        SubstractStringsBigDecimal so = new SubstractStringsBigDecimal();
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
    public String substring(String num1, String num2) {
        BigDecimal bigDecimal1 = new BigDecimal(num1);
        BigDecimal bigDecimal2 = new BigDecimal(num2);
        BigDecimal result = bigDecimal1.subtract(bigDecimal2);
        return result.toString();
    }


}

