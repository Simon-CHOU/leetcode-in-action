# week5 dichotomy, sorting

## 1011. 在 D 天内送达包裹的能力

### 1011. 在 D 天内送达包裹的能力
```java
// 1011. 在 D 天内送达包裹的能力
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        //最小边界，一船至少装下最大的单个货物
        int left = Arrays.stream(weights).max().getAsInt();
        //最大边界，一船装下所有
        int right = Arrays.stream(weights).sum();
        while(left < right) {
            int mid = (left + right) / 2;
            System.out.println("left=" + left +", right=" + right +
                    ", mid=" + mid);
            int need = 1; //需要运送的天数
            int cur = 0; //截至当天已经运送的货物重量
            for(int weight : weights) { //给定船的运载能力 x，我们是否可以在 days 天内运送完所有包裹呢？
                System.out.println(" - cur="+cur+", weight=" +weight
                        + ", cur+weight=" +(cur+weight)+", mid=" + mid);
                // 将连续的包裹都安排在同一天进行运送。当这批包裹的重量大于运载能力 x 时，我们就需要将最后一个包裹拿出来，安排在新的一天，并继续往下遍历
                if (cur + weight > mid) { //mid 就是运载能力 x
                    System.out.println("   - cur + weight > mid =" + (cur + weight > mid)
                            + ", need=need++="+need+"+1="+ (need+1)+", cur=0 超出当天（第"+need+"天）运载能力上界mid=" + mid);
                    need++;
                    cur = 0; //need++, cur 归零，开始数下一天的运量
                }
                cur += weight;
            }
            if (need <= days) {
                right = mid;
                System.out.println(" #收敛右界：need="+need+", days=" + days+
                        ", update RIGHT=" + right);
            } else {
                left = mid + 1;
                System.out.println(" #收敛左界：need="+need+", days=" + days+
                        ", update LEFT=" + left);
            }
        }
        System.out.println("左右界相撞，二分完成，left=" + left + ", right="
                + right + "解为 left=" + left);
        return left;

    }
}
/*
测试用例：
[1,2,3,4,5,6,7,8,9,10]
5

left=10, right=55, mid=32
 - cur=0, weight=1, cur+weight=1, mid=32
 - cur=1, weight=2, cur+weight=3, mid=32
 - cur=3, weight=3, cur+weight=6, mid=32
 - cur=6, weight=4, cur+weight=10, mid=32
 - cur=10, weight=5, cur+weight=15, mid=32
 - cur=15, weight=6, cur+weight=21, mid=32
 - cur=21, weight=7, cur+weight=28, mid=32
 - cur=28, weight=8, cur+weight=36, mid=32
   - cur + weight > mid =true, need=need++=1+1=2, cur=0 超出当天（第1天）运载能力上界mid=32
 - cur=8, weight=9, cur+weight=17, mid=32
 - cur=17, weight=10, cur+weight=27, mid=32
 #收敛右界：need=2, days=5, update RIGHT=32
left=10, right=32, mid=21
 - cur=0, weight=1, cur+weight=1, mid=21
 - cur=1, weight=2, cur+weight=3, mid=21
 - cur=3, weight=3, cur+weight=6, mid=21
 - cur=6, weight=4, cur+weight=10, mid=21
 - cur=10, weight=5, cur+weight=15, mid=21
 - cur=15, weight=6, cur+weight=21, mid=21
 - cur=21, weight=7, cur+weight=28, mid=21
   - cur + weight > mid =true, need=need++=1+1=2, cur=0 超出当天（第1天）运载能力上界mid=21
 - cur=7, weight=8, cur+weight=15, mid=21
 - cur=15, weight=9, cur+weight=24, mid=21
   - cur + weight > mid =true, need=need++=2+1=3, cur=0 超出当天（第2天）运载能力上界mid=21
 - cur=9, weight=10, cur+weight=19, mid=21
 #收敛右界：need=3, days=5, update RIGHT=21
left=10, right=21, mid=15
 - cur=0, weight=1, cur+weight=1, mid=15
 - cur=1, weight=2, cur+weight=3, mid=15
 - cur=3, weight=3, cur+weight=6, mid=15
 - cur=6, weight=4, cur+weight=10, mid=15
 - cur=10, weight=5, cur+weight=15, mid=15
 - cur=15, weight=6, cur+weight=21, mid=15
   - cur + weight > mid =true, need=need++=1+1=2, cur=0 超出当天（第1天）运载能力上界mid=15
 - cur=6, weight=7, cur+weight=13, mid=15
 - cur=13, weight=8, cur+weight=21, mid=15
   - cur + weight > mid =true, need=need++=2+1=3, cur=0 超出当天（第2天）运载能力上界mid=15
 - cur=8, weight=9, cur+weight=17, mid=15
   - cur + weight > mid =true, need=need++=3+1=4, cur=0 超出当天（第3天）运载能力上界mid=15
 - cur=9, weight=10, cur+weight=19, mid=15
   - cur + weight > mid =true, need=need++=4+1=5, cur=0 超出当天（第4天）运载能力上界mid=15
 #收敛右界：need=5, days=5, update RIGHT=15
left=10, right=15, mid=12
 - cur=0, weight=1, cur+weight=1, mid=12
 - cur=1, weight=2, cur+weight=3, mid=12
 - cur=3, weight=3, cur+weight=6, mid=12
 - cur=6, weight=4, cur+weight=10, mid=12
 - cur=10, weight=5, cur+weight=15, mid=12
   - cur + weight > mid =true, need=need++=1+1=2, cur=0 超出当天（第1天）运载能力上界mid=12
 - cur=5, weight=6, cur+weight=11, mid=12
 - cur=11, weight=7, cur+weight=18, mid=12
   - cur + weight > mid =true, need=need++=2+1=3, cur=0 超出当天（第2天）运载能力上界mid=12
 - cur=7, weight=8, cur+weight=15, mid=12
   - cur + weight > mid =true, need=need++=3+1=4, cur=0 超出当天（第3天）运载能力上界mid=12
 - cur=8, weight=9, cur+weight=17, mid=12
   - cur + weight > mid =true, need=need++=4+1=5, cur=0 超出当天（第4天）运载能力上界mid=12
 - cur=9, weight=10, cur+weight=19, mid=12
   - cur + weight > mid =true, need=need++=5+1=6, cur=0 超出当天（第5天）运载能力上界mid=12
 #收敛左界：need=6, days=5, update LEFT=13
left=13, right=15, mid=14
 - cur=0, weight=1, cur+weight=1, mid=14
 - cur=1, weight=2, cur+weight=3, mid=14
 - cur=3, weight=3, cur+weight=6, mid=14
 - cur=6, weight=4, cur+weight=10, mid=14
 - cur=10, weight=5, cur+weight=15, mid=14
   - cur + weight > mid =true, need=need++=1+1=2, cur=0 超出当天（第1天）运载能力上界mid=14
 - cur=5, weight=6, cur+weight=11, mid=14
 - cur=11, weight=7, cur+weight=18, mid=14
   - cur + weight > mid =true, need=need++=2+1=3, cur=0 超出当天（第2天）运载能力上界mid=14
 - cur=7, weight=8, cur+weight=15, mid=14
   - cur + weight > mid =true, need=need++=3+1=4, cur=0 超出当天（第3天）运载能力上界mid=14
 - cur=8, weight=9, cur+weight=17, mid=14
   - cur + weight > mid =true, need=need++=4+1=5, cur=0 超出当天（第4天）运载能力上界mid=14
 - cur=9, weight=10, cur+weight=19, mid=14
   - cur + weight > mid =true, need=need++=5+1=6, cur=0 超出当天（第5天）运载能力上界mid=14
 #收敛左界：need=6, days=5, update LEFT=15
左右界相撞，二分完成，left=15, right=15解为 left=15     
 */
```


### 911. 在线选举
```java
// 911. 在线选举

```