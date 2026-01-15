package com.simon.hot100;

public class MinimumAddToMakeParenthesesValidTDD {
    /**
     * “从左到右扫描字符串时，我们可以用一个变量来跟踪当前的平衡度（即未匹配的左括号数）。
     * 当遇到一个右括号时，
     * 如果没有左括号与之匹配（平衡度变为负数），就必须添加一个左括号来匹配它。
     * 这一步是确保任何前缀中右括号不会多于左括号。”
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        // 最少添加的括号，遍历可能性，在字符串任意位置插入 左 或者右，然后统计。
        // https://www.qianwen.com/chat/8c51181896204c1ebdbf359f11ffdea8

        int countNotMatch= 0 ;//当前未匹配的括号数
        int countAdd = 0 ; //需要添加的括号数

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                countNotMatch++;
            } else if(s.charAt(i) ==')') {
               if( countNotMatch >0) { // 还有未匹配的左括号
                countNotMatch--;
                } else { // 已经没有未匹配的左括号
                  // 需要补一个左括号
                  countAdd++;
                }
            }
        }
        // 遍历结束，可能有残存没匹配的左括号。
        // 为什么会残存没匹配的左括号？设想我们在遍历字符串时，每遇到 '(' 就把它放进“待匹配池”。如果后面没有足够的 ')' 来抵消这些 '('，那么遍历结束时，池子里就会残留一些未匹配的左括号。
      // 为什么不会残存没匹配的右括号呢？- 左括号可以“拖欠”，等到最后才知道是否匹配。- 右括号不能“拖欠”，它要么匹配成功，要么立刻被判定为孤儿。
       // 未匹配的左括号  countNotMatch，等于要补充的 右括号数量
        return countAdd + countNotMatch;
    }

    public static void main(String[] args) {
        MinimumAddToMakeParenthesesValidTDD tdd = new MinimumAddToMakeParenthesesValidTDD();
        int result = tdd.minAddToMakeValid("())"); //1
        System.out.println(result);
        int result1 = tdd.minAddToMakeValid("))))"); //4
        System.out.println(result1);
        int result2 = tdd.minAddToMakeValid("("); //4
        System.out.println(result2);
    }
}
