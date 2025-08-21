package com.simon.interview.pdd;

public class PDDO009 {
    
    /**
     * 大数字符串相减
     * 
     * 算法思路：
     * 1. 模拟手工减法运算过程
     * 2. 从个位开始逐位相减
     * 3. 处理借位情况
     * 4. 去除前导零
     * 
     * 时间复杂度：O(max(m,n))，其中m和n分别是两个字符串的长度
     * 空间复杂度：O(max(m,n))，用于存储结果
     * 
     * @param num1 被减数字符串
     * @param num2 减数字符串
     * @return 相减结果字符串
     */
    public static String subtract(String num1, String num2) {
        // 处理符号，确保num1 >= num2
        boolean negative = false;
        if (compare(num1, num2) < 0) {
            negative = true;
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        
        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int borrow = 0;
        
        // 从个位开始逐位相减
        while (i >= 0 || j >= 0) {
            int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            
            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            
            result.append(diff);
            i--;
            j--;
        }
        
        // 反转结果
        String res = result.reverse().toString();
        
        // 去除前导零
        res = removeLeadingZeros(res);
        
        // 如果原来结果为负数，添加负号
        return negative ? "-" + res : res;
    }
    
    /**
     * 比较两个大数字符串的大小
     * 
     * @param num1 第一个数
     * @param num2 第二个数
     * @return 如果num1 > num2返回1，如果num1 < num2返回-1，相等返回0
     */
    private static int compare(String num1, String num2) {
        if (num1.length() > num2.length()) return 1;
        if (num1.length() < num2.length()) return -1;
        
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) > num2.charAt(i)) return 1;
            if (num1.charAt(i) < num2.charAt(i)) return -1;
        }
        
        return 0;
    }
    
    /**
     * 去除字符串前导零
     * 
     * @param num 输入数字字符串
     * @return 去除前导零后的字符串
     */
    private static String removeLeadingZeros(String num) {
        int i = 0;
        while (i < num.length() - 1 && num.charAt(i) == '0') {
            i++;
        }
        return num.substring(i);
    }
    
    public static void main(String[] args) {
        // 测试用例
        System.out.println("测试用例:");
        
        // 基本测试
        System.out.println("123 - 45 = " + subtract("123", "45")); // 期望: 78
        System.out.println("100 - 50 = " + subtract("100", "50")); // 期望: 50
        System.out.println("45 - 123 = " + subtract("45", "123")); // 期望: -78
        
        // 边界测试
        System.out.println("1000 - 1 = " + subtract("1000", "1")); // 期望: 999
        System.out.println("0 - 0 = " + subtract("0", "0")); // 期望: 0
        System.out.println("12345 - 0 = " + subtract("12345", "0")); // 期望: 12345
        
        // 相等数字测试
        System.out.println("123 - 123 = " + subtract("123", "123")); // 期望: 0
        
        // 大数测试
        System.out.println("123456789 - 987654321 = " + subtract("123456789", "987654321")); // 期望: -864197532
    }
}