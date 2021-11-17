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
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][]  f= new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i< n; ++i) {
            f[i][0] = f[i -1][0] + triangle.get(i).get(0);
            for( int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; i++) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }
}
```

### 673. 最长递增子序列的个数
```java
// 673. 最长递增子序列的个数
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n  = nums.length, maxLen = 0, ans = 0;
        int[] dp = new int[n];
        int[] count = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            dispArr(dp, "#dp=");
            dispArr(count, "#count=");
            for(int j = 0; j <n; j++) {
                if(nums[i] > nums[j]) {
                    System.out.println("  nums[i]="+nums[i]+", nums[j]="+ nums[j]);
                    System.out.println("  dp[i] ="+dp[i] +", dp[j]"+ dp[j]);
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                        dispArr(dp, "    *dp=");
                        dispArr(count, "    *count=");
                    } else if(dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                        dispArr(dp, "    ^dp=");
                        dispArr(count, "    ^count=");
                    }
                }
            }
            int maxLenTemp =  maxLen;
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = count[i];
                System.out.print("MaxLen is set from " + maxLenTemp +" to "+ maxLen +". ans=count["+i+"]=" +count[i]);
            } else if (dp[i] == maxLen) {
                ans += count[i];
                System.out.print("ans=count["+i+"]=" +count[i]);
            }
        }
        return ans;
    }
    private void dispArr(int [] nums, String preffix) {
        System.out.print(preffix+"[");
        for(int i = 0; i< nums.length-1; i++) {
            System.out.print(nums[i]+",");
        }

        System.out.println(nums[nums.length-1] + "]");
    }
}
/*
[1,3,5,4,7]

dp count status log:

#dp=[1,0,0,0,0]
#count=[1,0,0,0,0]
MaxLen is set from 0 to 1. ans=count[0]=1#dp=[1,1,0,0,0]
#count=[1,1,0,0,0]
  nums[i]=3, nums[j]=1
  dp[i] =1, dp[j]1
    *dp=[1,2,0,0,0]
    *count=[1,1,0,0,0]
MaxLen is set from 1 to 2. ans=count[1]=1#dp=[1,2,1,0,0]
#count=[1,1,1,0,0]
  nums[i]=5, nums[j]=1
  dp[i] =1, dp[j]1
    *dp=[1,2,2,0,0]
    *count=[1,1,1,0,0]
  nums[i]=5, nums[j]=3
  dp[i] =2, dp[j]2
    *dp=[1,2,3,0,0]
    *count=[1,1,1,0,0]
  nums[i]=5, nums[j]=4
  dp[i] =3, dp[j]0
MaxLen is set from 2 to 3. ans=count[2]=1#dp=[1,2,3,1,0]
#count=[1,1,1,1,0]
  nums[i]=4, nums[j]=1
  dp[i] =1, dp[j]1
    *dp=[1,2,3,2,0]
    *count=[1,1,1,1,0]
  nums[i]=4, nums[j]=3
  dp[i] =2, dp[j]2
    *dp=[1,2,3,3,0]
    *count=[1,1,1,1,0]
ans=count[3]=1#dp=[1,2,3,3,1]
#count=[1,1,1,1,1]
  nums[i]=7, nums[j]=1
  dp[i] =1, dp[j]1
    *dp=[1,2,3,3,2]
    *count=[1,1,1,1,1]
  nums[i]=7, nums[j]=3
  dp[i] =2, dp[j]2
    *dp=[1,2,3,3,3]
    *count=[1,1,1,1,1]
  nums[i]=7, nums[j]=5
  dp[i] =3, dp[j]3
    *dp=[1,2,3,3,4]
    *count=[1,1,1,1,1]
  nums[i]=7, nums[j]=4
  dp[i] =4, dp[j]3
    ^dp=[1,2,3,3,4]
    ^count=[1,1,1,1,2]
MaxLen is set from 3 to 4. ans=count[4]=2
 */
```

## exmaple


### 300. 最长递增子序列
```java
// 300. 最长递增子序列
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxAns = 1;
        for ( int i = 1; i < nums.length; i++) {
            dispArr(dp, "##b");
            dp[i] = 1;
            dispArr(dp, "##a");
            for (int j = 0; j < i ; j++) {
                if (nums[i] > nums[j]) {
                    dispArr(dp, "  --b");
                    System.out.println("  ~~dp[i]"+ dp[i]+ ", dp[j]="+ dp[j] );
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    dispArr(dp, " --a");
                }
            }
            System.out.print("MaxAns is change from " + maxAns);
            maxAns = Math.max(maxAns, dp[i]);
            System.out.println(" to " +maxAns);
        }
        return maxAns;
    }
    private void dispArr(int [] nums, String preffix) {
        System.out.print(preffix+"[");
        for(int i = 0; i< nums.length-1; i++) {
            System.out.print(nums[i]+",");
        }

        System.out.println(nums[nums.length-1] + "]");
    }
}

/*
[10,9,2,5,3,7,101,18]
num[] is constant

dp status log

##b[1,0,0,0,0,0,0,0]
##a[1,1,0,0,0,0,0,0]
MaxAns is change from 1 to 1
##b[1,1,0,0,0,0,0,0]
##a[1,1,1,0,0,0,0,0]
MaxAns is change from 1 to 1
##b[1,1,1,0,0,0,0,0]
##a[1,1,1,1,0,0,0,0]
  --b[1,1,1,1,0,0,0,0]
  ~~dp[i]1, dp[j]=1
 --a[1,1,1,2,0,0,0,0]
MaxAns is change from 1 to 2
##b[1,1,1,2,0,0,0,0]
##a[1,1,1,2,1,0,0,0]
  --b[1,1,1,2,1,0,0,0]
  ~~dp[i]1, dp[j]=1
 --a[1,1,1,2,2,0,0,0]
MaxAns is change from 2 to 2
##b[1,1,1,2,2,0,0,0]
##a[1,1,1,2,2,1,0,0]
  --b[1,1,1,2,2,1,0,0]
  ~~dp[i]1, dp[j]=1
 --a[1,1,1,2,2,2,0,0]
  --b[1,1,1,2,2,2,0,0]
  ~~dp[i]2, dp[j]=2
 --a[1,1,1,2,2,3,0,0]
  --b[1,1,1,2,2,3,0,0]
  ~~dp[i]3, dp[j]=2
 --a[1,1,1,2,2,3,0,0]
MaxAns is change from 2 to 3
##b[1,1,1,2,2,3,0,0]
##a[1,1,1,2,2,3,1,0]
  --b[1,1,1,2,2,3,1,0]
  ~~dp[i]1, dp[j]=1
 --a[1,1,1,2,2,3,2,0]
  --b[1,1,1,2,2,3,2,0]
  ~~dp[i]2, dp[j]=1
 --a[1,1,1,2,2,3,2,0]
  --b[1,1,1,2,2,3,2,0]
  ~~dp[i]2, dp[j]=1
 --a[1,1,1,2,2,3,2,0]
  --b[1,1,1,2,2,3,2,0]
  ~~dp[i]2, dp[j]=2
 --a[1,1,1,2,2,3,3,0]
  --b[1,1,1,2,2,3,3,0]
  ~~dp[i]3, dp[j]=2
 --a[1,1,1,2,2,3,3,0]
  --b[1,1,1,2,2,3,3,0]
  ~~dp[i]3, dp[j]=3
 --a[1,1,1,2,2,3,4,0]
MaxAns is change from 3 to 4
##b[1,1,1,2,2,3,4,0]
##a[1,1,1,2,2,3,4,1]
  --b[1,1,1,2,2,3,4,1]
  ~~dp[i]1, dp[j]=1
 --a[1,1,1,2,2,3,4,2]
  --b[1,1,1,2,2,3,4,2]
  ~~dp[i]2, dp[j]=1
 --a[1,1,1,2,2,3,4,2]
  --b[1,1,1,2,2,3,4,2]
  ~~dp[i]2, dp[j]=1
 --a[1,1,1,2,2,3,4,2]
  --b[1,1,1,2,2,3,4,2]
  ~~dp[i]2, dp[j]=2
 --a[1,1,1,2,2,3,4,3]
  --b[1,1,1,2,2,3,4,3]
  ~~dp[i]3, dp[j]=2
 --a[1,1,1,2,2,3,4,3]
  --b[1,1,1,2,2,3,4,3]
  ~~dp[i]3, dp[j]=3
 --a[1,1,1,2,2,3,4,4]
MaxAns is change from 4 to 4
 */
```