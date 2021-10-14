# week2 
本周作业提交：https://jinshuju.net/f/I8BtXf

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