# week1 array, link list, queue

https://u.geekbang.org/lesson/186?article=419876&utm_source=time_web&utm_medium=menu&utm_term=timewebmenu&utm_identify=geektime&utm_content=menu&utm_campaign=timewebmenu&gk_cus_user_wechat=university

第一周作业表单： https://jinshuju.net/f/pIVpTH

## 26. 删除有序数组中的重复项

```java
// 26. 删除有序数组中的重复项
class Solution {
    public int removeDuplicates(int[] nums) {
        int n =0;
        for(int i = 0; i < nums.length; i++){
            if(i==0 || nums[i] != nums[i-1]) { //保留：第一个 OR 不重复
                nums[n]=nums[i]; //原地操作
                n++;
            }
        }
        return n;
    }
}
```

## 283. 移动零
```java
// 283. 移动零
class Solution {
    public void moveZeroes(int[] nums) {
        int n = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){ //过滤器
                nums[n] = nums[i];
                n++;
            }            
        }
        while(n < nums.length){
            nums[n] = 0;
            n++;
        }
    }
}
```

## 66. 加一
```java
//66. 加一
class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;//除了9 加1进位(且原位为0），其他都不进位
            if (digits[i] != 0) return digits;
        }
        digits = new int[len + 1];//处理99,999..等特殊情况，进位1，且其余都是0
        digits[0] = 1;
        return digits;
    }
}
```

## 21. 合并两个有序链表
```java
//21. 合并两个有序链表
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode();//with head node
        ListNode last = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                last.next = l2;
                l2 = l2.next;
            } else {
                last.next = l1;
                l1 = l1.next;
            }
            last = last.next;
        }
        if(l1!=null){
            last.next = l1;
        }
        if(l2!=null) {
            last.next = l2;
        }

        return newHead.next;
    }
}
```

## 20. 有效的括号

```java
//20. 有效的括号
class Solution {
    Stack<Character> stack = new Stack<>();
    public boolean isValid(String s) {
        char [] chars = s.toCharArray();
        for(char ch : chars){
            if(ch == '(' ||ch == '[' ||ch == '{' ){
                stack.push(ch);
            } else{
                if(stack.empty()) {
                    return false;
                }
                char topChar = stack.pop();
                if(ch ==')'  && topChar !='('){
                    return false;
                }
                if(ch ==']'  && topChar !='['){
                    return false;
                }
                if(ch =='}'  && topChar !='{'){
                    return false;
                }

            }
        }
        return stack.empty();
    }
}
```

## 150. 逆波兰表达式求值
```java
//150. 逆波兰表达式求值
class Solution {
    private Stack<Integer> s = new Stack<>();
    public int evalRPN(String[] tokens) {
      for(String token : tokens) {
          if(token.equals("+") ||token.equals("-") ||token.equals("*") ||token.equals("/")){ //字符串比较用euquals不能用==
             Integer op1 = s.peek();
             s.pop();
             Integer op2 = s.peek();
             s.pop();
             int ans = calc(op2, op1, token);// 运算是有顺序的，-,/
             s.push(ans);
          } else {
              s.push(Integer.valueOf(token));
          }
      }
      return s.peek();
    }

    private Integer calc(Integer x, Integer y, String op) {
       if(op.equals("+")) {return x + y;}
       if(op.equals("-")) {return x - y;}
       if(op.equals("*")) {return x * y;}
       if(op.equals("/")) {return x / y;}
       return 0;// illegal
    }
}
```
# 227. 基本计算器 II
```java
//227. 基本计算器 II 
class Solution {

    private Stack<Character> ops = new Stack<>();//运算符栈，用以处理优先级

    public int calculate(String s) {
        s+=" "; //保证末尾数字能被从number中推出来
        List<String> tokens = new ArrayList<>();//存储转化的后缀表达式
        StringBuilder number = new StringBuilder();
        for(char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
              number.append(ch);//多个数字组成一个整数
              continue;
            } else {
                if(number.length() !=0){ //前一位是数字
                    tokens.add(number.toString());
                    number.setLength(0);//clean
                }//至此完成了一个整数的处理
            }
            if(ch == ' ') {
                continue;
            }
            int currRank = getRank(ch);
            while(!ops.empty() && getRank(ops.peek()) >= currRank){
              tokens.add(Character.toString(ops.peek()));
              ops.pop();
            }
            ops.push(ch);
        }
        while(!ops.empty()){
            tokens.add(Character.toString(ops.peek()));
            ops.pop();
        }//至此得到了后缀表达式 tokens

        return evalRPN(tokens.toArray(new String[0]));
    }
 
      /**
     * 返回优先级
    */
    private int getRank(char op){
        if(op =='*' || op =='/') {return 2;}
        if(op == '+' || op == '-') {return 1;}
        return 0;
    }


     Stack<Integer> s = new Stack<>();
    public int evalRPN(String[] tokens) {
      for(String token : tokens) {
          if(token.equals("+") ||
          token.equals("-") ||
          token.equals("*") ||
          token.equals("/")){ //字符串比较用euquals不能用==
             Integer op1 = s.peek();
             s.pop();
             Integer op2 = s.peek();
             s.pop();
             int ans = calc(op2, op1, token);// 运算是有顺序的，-,/
             s.push(ans);
          } else {
              s.push(Integer.valueOf(token));
          }
      }
      return s.peek();
    }

    Integer calc(Integer x, Integer y, String op) {
       if(op.equals("+")) {return x + y;}
       if(op.equals("-")) {return x - y;}
       if(op.equals("*")) {return x * y;}
       if(op.equals("/")) {return x / y;}
       return 0;// illegal
    }
}
```