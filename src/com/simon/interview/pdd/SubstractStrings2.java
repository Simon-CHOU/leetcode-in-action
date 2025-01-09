package com.simon.interview.pdd;

public class SubstractStrings2 { // SubstractStrings2 法2 108mm 比 SubstractStrings 法1 563mm 更快
    //请实现一个Java代码方法，  String substring (String num1, String num2) 两个大数字符串相减并返回差的字符串结果。要求：数据范围：两个数字的长度都满足 1<=num1,num2 <=10^5, ，数字中仅包含 0<=val<=9 ，第一位不可能是0 。 输入示例："429018691214", "3278729808879"  返回 "-2849711117665"
    public static void main(String[] args) {
        SubstractStrings2 so = new SubstractStrings2();
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
    // 大数字符串减法
    public String substring(String num1, String num2) {
        // 确定被减数和减数
        int cmp = compare(num1, num2);
        boolean negative = false;
        String minuend, subtrahend;
        if (cmp < 0) {
            minuend = num2;
            subtrahend = num1;
            negative = true;
        } else {
            minuend = num1;
            subtrahend = num2;
            negative = false;
        }

        // 转换成字符数组，从右到左
        char[] m = minuend.toCharArray();
        char[] s = subtrahend.toCharArray();
        int lenM = m.length;
        int lenS = s.length;
        int diff = lenM - lenS;
        int[] result = new int[lenM];
        int borrow = 0;
        // 从右到左进行减法
        for (int i = lenM - 1; i >= 0; i--) {
            int digitM = m[i] - '0';
            int digitS = (i - diff >= 0) ? s[i - diff] - '0' : 0;
            int temp = digitM - borrow - digitS;
            if (temp < 0) {
                temp += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result[i] = temp;
        }
        // 去掉前导零
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (int i = 0; i < lenM; i++) {
            if (result[i] != 0) {
                leadingZero = false;
            }
            if (!leadingZero) {
                sb.append(result[i]);
            }
        }
        String res = sb.toString();
        if (res.isEmpty()) {
            res = "0";
        }
        if (negative) {
            res = "-" + res;
        }
        return res;
    }


    // 比较两个大数字符串的大小
    public static int compare(String num1, String num2) {
        if (num1.length() > num2.length()) {
            return 1;
        } else if (num1.length() < num2.length()) {
            return -1;
        } else {
            for (int i = 0; i < num1.length(); i++) {
                if (num1.charAt(i) > num2.charAt(i)) {
                    return 1;
                } else if (num1.charAt(i) < num2.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }
}

