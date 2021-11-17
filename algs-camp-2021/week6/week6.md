# week6 Greedy algorithm & Dynamic planning 

## homework

### 70. 爬楼梯
```java
// 70. 爬楼梯
class Solution {
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            System.out.println("【i="+i+"】p="+ p + ", q="+q+", r="+r);
            p = q;
            q = r;
            r= p + q;
        }
        return r;
    }
}

/*
分析：
f(n) = f(n-1) + f(n-2)

f(0) = 0
f(1) = 1
f(2) = 2
f(3) = 3
....

巧合：斐波那契数列


10
滚动数组：
【i=1】p=0, q=0, r=1
【i=2】p=0, q=1, r=1
【i=3】p=1, q=1, r=2
【i=4】p=1, q=2, r=3
【i=5】p=2, q=3, r=5
【i=6】p=3, q=5, r=8
【i=7】p=5, q=8, r=13
【i=8】p=8, q=13, r=21
【i=9】p=13, q=21, r=34
【i=10】p=21, q=34, r=55


*/
```

### 120. 三角形最小路径和
```java
// 120. 三角形最小路径和

```

### 673. 最长递增子序列的个数
```java
// 673. 最长递增子序列的个数

```

## exmaple
