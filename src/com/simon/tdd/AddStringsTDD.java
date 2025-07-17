package com.simon.tdd;

public class AddStringsTDD {
    /**
     * 字符串相加函数
     * 
     * Java知识点分解：
     * 1. 方法定义：public String addStrings(String num1, String num2) - 公共方法，接收两个字符串参数，返回字符串结果
     * 2. 变量声明：StringBuilder result, int i, j, carry - 不同类型的变量声明
     * 3. 对象创建：new StringBuilder() - 创建StringBuilder对象
     * 4. 表达式与运算符：i >= 0 ? num1.charAt(i) - '0' : 0 - 三元运算符使用
     * 5. 类型转换：char值通过减法转换为int数值
     * 6. 方法调用：num1.length(), num1.charAt(i), result.append(), result.reverse()
     * 7. 控制流：while循环，if条件判断
     * 8. 字符串不可变性：字符串对象的不可变特性
     * 9. 异常处理：隐含处理不同进制和格式的数字字符串
     * 
     * 数据结构知识点分解：
     * 1. 线性结构：字符串作为线性数据结构的遍历
     * 2. 动态数组：StringBuilder内部实现的动态数组扩展
     * 3. 栈特性：数字相加从低位到高位的处理顺序（后进先出）
     * 4. 进位处理：模拟大数加法的典型算法结构
     * 5. 双指针技术：i和j两个指针同时遍历两个输入字符串
     * 6. 分治思想：将大问题（大数相加）分解为小问题（单个数字相加）
     * 7. 时间复杂度分析：O(max(len(num1), len(num2))) - 线性时间复杂度
     * 8. 空间复杂度分析：O(max(len(num1), len(num2))) - 结果存储所需空间
     * 
     * 算法细节分解：
     * 1. 初始化：从字符串末尾开始处理（个位）
     * 2. 进位机制：sum = digit1 + digit2 + carry
     * 3. 结果拼接：使用StringBuilder提高字符串拼接效率
     * 4. 结果反转：reverse()方法将结果从低位到高位调整为高位到低位
     * 5. 循环终止条件：所有位数处理完毕且无进位
     * 
     * @param num1 第一个非负整数字符串
     * @param num2 第二个非负整数字符串
     * @return 两个数相加的字符串结果
     */
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        // 低位开始逐位相加两个字符串表示的非负整数，并处理进位
        int carry = 0; // carry保存进位值，初始为0；

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.append(sum % 10);

            i--;
            j--;
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        AddStringsTDD solution = new AddStringsTDD();

        // Test case 1
        String num1_1 = "11";
        String num2_1 = "123";
        String expected1 = "134";
        String result1 = solution.addStrings(num1_1, num2_1);
        System.out.println("Test case 1: " + (expected1.equals(result1) ? "Passed" : "Failed"));

        // Test case 2
        String num1_2 = "999";
        String num2_2 = "999";
        String expected2 = "1998";
        String result2 = solution.addStrings(num1_2, num2_2);
        System.out.println("Test case 2: " + (expected2.equals(result2) ? "Passed" : "Failed"));
    }
}