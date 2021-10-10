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