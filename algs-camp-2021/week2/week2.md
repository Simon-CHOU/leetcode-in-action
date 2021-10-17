# week2 
本周作业提交：https://jinshuju.net/f/I8BtXf

## 作业
### 811. 子域名访问计数
```java
// 811. 子域名访问计数
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<>();
        for (String domain: cpdomains) {
            String[] cpinfo = domain.split("\\s+");// 分离数值，域名
            // for(String c : cpinfo) {
            //     System.out.print(c + ",");
            // }
            // System.out.printf("%s", "\n");
            String[] frags = cpinfo[1].split("\\.");// 分离各级域名（每个域名地址包含一个或两个"."符号）
            // for(String f : frags) {
            //     System.out.print(f + "; ");
            // }
            // System.out.printf("%s", "\n");
            int count = Integer.valueOf(cpinfo[0]);
            String cur = "";
            for( int i = 0; i <= frags.length -1; i++) {
                StringBuilder str = new StringBuilder();
                for(int j = i; j <= frags.length-1 ; j++) {
                    str.append(frags[j]);
                    if(j != frags.length -1) {
                        str.append(".");
                    }
                }
                cur = str.toString();// a.b.c ; b.c; b.c
                // System.out.println("#cur:"+ str.toString());        
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
                // System.out.println("# i=" + i +", cur=" + cur + ", put count[" +cur+"," + counts.getOrDefault(cur, 0) + ", "+count +"]");   
            }

        }

        List<String> ans = new ArrayList<>();
        for (String dom: counts.keySet())
            ans.add(counts.get(dom) + " " + dom);
        return ans;
    }
}
```
### 697. 数组的度
```java
// 697. 数组的度
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        int n = nums.length;
        for( int i = 0; i < n; i++) {
            if(map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
                System.out.println("-update key="+nums[i]+ ",  [" +  map.get(nums[i])[0] + "," +  map.get(nums[i])[1] + "," +  map.get(nums[i])[2] + "]");
            } else {
                // count, begin, end
                System.out.println("*put nums[i]=" + nums[i] + ",  [" + 1 + "," + i + "," + i + "]");
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        for(Map.Entry<Integer, int[]> e : map.entrySet()) {
            System.out.print("#map:key=" + e.getKey()+", ");
            int[] aa = e.getValue();
            for(int k : aa) {
                System.out.print(k + " ");
            }
            System.out.printf("%s", "\n");
        }
        int maxNum = 0; //任一元素出现频数的最大值
        int minLen = 0; //最短连续子数组的长度
        for(Map.Entry<Integer, int[]> e : map.entrySet()) {
            int[] arr = e.getValue();
            if(maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if(maxNum == arr[0]) {
                if(minLen > arr[2] - arr[1] + 1){
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }
        return minLen;

    }
}
/* [1,2,2,3,1,4,2]
*put nums[i]=1,  [1,0,0]
*put nums[i]=2,  [1,1,1]
-update key=2,  [2,1,2]
*put nums[i]=3,  [1,3,3]
-update key=1,  [2,0,4]
*put nums[i]=4,  [1,5,5]
-update key=2,  [3,1,6]
#map:key=1, 2 0 4 
#map:key=2, 3 1 6 
#map:key=3, 1 3 3 
#map:key=4, 1 5 5 
 */
```
### 811. 和为 K 的子数组
```java
// 811. 和为 K 的子数组
class Solution {
    public int subarraySum(int[] nums, int k) {
        int prefixSum = 0;
        HashMap<Integer, Integer> memo = new HashMap<>();
        memo.put(0,1);
        int count = 0;
        for(int i = 0 ; i < nums.length; i++) {
            prefixSum += nums[i];
            System.out.println("prefixSum = " + prefixSum +" ");
            if(memo.containsKey(prefixSum - k)) {
                //使用递推公式检验
                System.out.println("#prefixSum-k=" +(prefixSum - k) +"  memo.getOrDefault(prefixSum - k, 0)=" + memo.getOrDefault(prefixSum - k, 0));
                count += memo.getOrDefault(prefixSum - k, 0);
            }// 计数，在更新序列和之前!!!
            if(!memo.containsKey(prefixSum)) {
                // System.out.println("# put(" + prefixSum  +"," +1+")");
                memo.put(prefixSum, 1);
            } else {
                // System.out.println("memo.getOrDefault(prefixSum, 0)="+ memo.getOrDefault(prefixSum, 0));
                // System.out.println("# put(" + prefixSum+"," +( memo.getOrDefault(prefixSum, 0) + 1)+")");
                memo.put(prefixSum, memo.getOrDefault(prefixSum, 0) + 1);
            }// 至此，前缀和维护完毕            
        }
        //display hashmap
        for(Map.Entry<Integer, Integer> e : memo.entrySet()) {
            System.out.print("{" + e.getKey() +"," + e.getValue()+"}");
        }
        return count;
    }
}
/*
case 1
[3,4,7,2,-3,1,4,2]
7

prefixSum = 3 
prefixSum = 7 
#prefixSum-k=0  memo.getOrDefault(prefixSum - k, 0)=1
prefixSum = 14 
#prefixSum-k=7  memo.getOrDefault(prefixSum - k, 0)=1
prefixSum = 16 
prefixSum = 13 
prefixSum = 14 
#prefixSum-k=7  memo.getOrDefault(prefixSum - k, 0)=1
prefixSum = 18 
prefixSum = 20 
#prefixSum-k=13  memo.getOrDefault(prefixSum - k, 0)=1
{0,1}{16,1}{18,1}{3,1}{20,1}{7,1}{13,1}{14,2}


case  2
[1]
0

prefixSum = 1 {0,1}{1,1}


 */
```
### 1074. 元素和为目标值的子矩阵数量


---
以下是**例题**by李煜东

## lesson 3: hashtable, set, map

### 1. 两数之和
```java
// 1. 两数之和
class Solution {
    HashMap<Integer,Integer> map = new HashMap<>();
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i< nums.length; i++){
            if(map.containsKey(target - nums[i])){
               return new int[]{(int) map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
```
### 874. 模拟行走机器人
```java
// 874. 模拟行走机器人
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        HashSet<Long> set = new HashSet<Long>();
        for(int[]obs : obstacles){
            set.add(calcHash(obs));
        }
        int x = 0, y = 0;//初始
        int direction = 0;// 方向 N=0, E=1, S=2, W=3
        //转方向，通过mod运算实现分支逻辑，比if-else更简洁
        //向右转 (dir+1)%4
        //向左转 (dir-1 +4)%4， +4是为了避免为负数， 避免(0-1)%4=-1。即(dir+3)%4
        // 网格中行走题目，技巧：方向数组
        final int []dx = {0, 1, 0, -1};
        final int []dy = {1, 0, -1, 0};
        int ans = 0;
        for(int command: commands){
            if(command == -1){
                direction = (direction+1)%4;
            }else if(command == -2){
                direction = (direction+3)%4;
            }else{
                for( int i=0; i< command; i++){
                   int nx = x + dx[direction];
                   int ny = y+ dy[direction];
                   if(set.contains(calcHash(new int[]{nx, ny}))){
                      break;//如果是障碍物，则不动
                   }
                   x = nx;
                   y =ny;
                   ans = Math.max(ans, x*x + y*y);//保存最大欧氏距离
                }

            }
        }
        return ans;
    }

    /*private String calcHash(int [] obstacle){
        return obstacle[0] + "," + obstacle[1];//坐标，只有x,y两元
    }*/
    private Long calcHash(int [] obstacle){
        //二维坐标转为整数，当前行*列数+当前列号。平移去掉符号
        return (obstacle[0] +30000L) * 60001L + (obstacle[1]+30000L);
    }//-3 * 104 <= xi, yi <= 3 * 104
}
```
### 49. 字母异位词分组
```java
//49. 字母异位词分组
class Solution {
    private Map<String, List<String>> groups = new HashMap<>();
    public List<List<String>> groupAnagrams(String[] strs) {
        for(String str : strs){
            String copy = sortStr(str); //把排好序的字符串为键
            if(!groups.containsKey(copy)){
                groups.put(copy, new ArrayList<String>());//还没有词组，新建List
            }
            groups.get(copy).add(str);//已有字母异位词组，则收录
        }
        return new ArrayList<>(groups.values());
    }
    private String sortStr(String str){
        char [] ori = str.toCharArray();
        Arrays.sort(ori);
        return new String(ori);
    }
}
/*
 https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
 https://mkyong.com/java8/java-8-convert-map-to-list/
 https://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/
 */
```

### 30. 串联所有单词的子串 todo
```java
// 30. 串联所有单词的子串
class Solution {
    
    HashMap<String, Integer> wordsMap = new HashMap<>();
    
    public List<Integer> findSubstring(String s, String[] words) {
       int tot = 0;//total lenght
       for( String word : words) {
           tot += word.length();
           if(wordsMap.size()>0 && wordsMap.containsKey(word)) {
               wordsMap.put(word, wordsMap.get(word)+1);
           } else {
               wordsMap.put(word, 1);
           }
       }

       System.out.println("tot=" + tot);
       List<Integer> ans = new ArrayList<>();
       for (int i =0; i + tot <= s.length(); i++) {
           System.out.println("--- i="+i+", i+t="+(i+tot)+", s.substring(i, tot)=" +s.substring(i, i + tot));
           if(valid(s.substring(i, i + tot), words)){
                ans.add(i);
           }
       }
       return ans;
    }

    private boolean valid(String str, String[] words){
        System.out.print("#begin valid str=" + str+", words=");
        for(String w: words ){ System.out.print(w+",");}
        int k = words[0].length();
        HashMap<String, Integer> splitWordsMap = new HashMap<>();
        System.out.println("   k:"+k);
        for( int i = 0; i< str.length(); i += k) {
            System.out.println("#validfor i="+i+", k="+k +"  str.length()=" + str.length());
            String key = str.substring(i, k);
            if(key.equals("")){System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");}
            System.out.println("** substring:" + key);
            if(splitWordsMap.size()>0 && splitWordsMap.containsKey(key)) {
               splitWordsMap.put(key, splitWordsMap.get(key)+1);
           } else {
               splitWordsMap.put(key, 1);
           }
        }
        System.out.print("#end valid: splitWordsMap:");
       for(Map.Entry e : splitWordsMap.entrySet()) {System.out.print("["+e.getKey()+","+e.getValue()+"],");}
       System.out.print(" / wordsMap:");
       for(Map.Entry e : wordsMap.entrySet()) {System.out.print("["+e.getKey()+","+e.getValue()+"],");}
        System.out.println("");
        return equalsMap(splitWordsMap, wordsMap);
    }

    boolean equalsMap(HashMap<String, Integer> a ,HashMap<String, Integer> b) {
        for(Map.Entry e: a.entrySet()){
            String key = (String) e.getKey();
            int value = (int) e.getValue();
            if(!b.containsKey(key) || b.get(key) != value) { 
                return false;
            }
        }
        for(Map.Entry e: b.entrySet()){
            String key = (String) e.getKey();
            int value = (int) e.getValue();
            if(!a.containsKey(key) || a.get(key) != value) { 
                return false;
            }
        }
        return true;
    }
}
```

## lesson 4: Prefix sum, difference, double pointer

### 1248. 统计「优美子数组」
```java
//1248. 统计「优美子数组」
class Solution {
    
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        List<Integer> s = new ArrayList<>(n+1);
        for(int i = 0 ;i<=n; i++) {s.add(0);}
        s.set(0, 0);
        for(int i = 1; i <= n; i++) {
            s.set(i, s.get(i-1) + nums[i-1]%2);
        }
        List<Integer> count = new ArrayList<>(n+1);
        for(int i = 0 ;i<=n; i++) {count.add(0);}
        int ans = 0;
        // System.out.println(s.toString());
        // System.out.println("s.get(0) = " + s.get(0));
        count.set(s.get(0), count.get(s.get(0)) +1);
        for(int i = 1; i <= n; i++) {
            if(s.get(i) - k >= 0) {
                ans += count.get(s.get(i) - k);
            }
            count.set(s.get(i), count.get(s.get(i)) + 1);
        }
        return ans;
    }
}
```

### 304. 二维区域和检索 - 矩阵不可变

```java
//304. 二维区域和检索 - 矩阵不可变
class NumMatrix {

    int[][] sum;

    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        sum = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++; col1++; row2++; col2++;//题目从0数，sum从1数
        return sum[row2][col2] - sum[row2][col1 - 1] - sum[row1 - 1][col2] + sum[row1 - 1][col1 - 1];
    }
}
```

### 1109. 航班预订统计

```java
//1109. 航班预订统计
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
      List<Integer> delta = new ArrayList<>(Collections.nCopies(n+2, 0));// n+1 有用，故再挪一个。
      for(int[] booking : bookings) {
          int first = booking[0];
          int last = booking[1];
          int seats = booking[2];
          delta.set(first, delta.get(first) + seats);
          delta.set(last + 1, delta.get(last + 1) - seats);
      }
      List<Integer> sum = new ArrayList<>(Collections.nCopies(n+1, 0)); //0~n
      for (int i = 1; i <= n; i++) {
          sum.set(i, sum.get(i - 1) + delta.get(i));
      }
       int[] answer = new int[n]; //0~n-1
       for(int i = 1; i <= n; i++) {
           answer[i-1] =  sum.get(i);
       }
       return answer;
    }
}   
```