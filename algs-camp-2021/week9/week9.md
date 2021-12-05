# String

## homework

### 709. 转换成小写字母
```java
// 709. 转换成小写字母
class Solution {
    public String toLowerCase(String s) {
        return s.toLowerCase();
    }
}
```

### 58. 最后一个单词的长度
```java
//58. 最后一个单词的长度
class Solution {
    public int lengthOfLastWord(String s) {
        String[] sa = s.split(" ");
        String lstr = sa[sa.length - 1]; //数组 .length
        return lstr.length(); //字符串 .lenght()
    }
}
```
### 771. 宝石与石头
```java
//771. 宝石与石头
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        for(int i = 0; i < stones.length(); i++) {
            char ch = stones.charAt(i);
            // System.out.println(ch);
            for(int j = 0; j < jewels.length(); j++) {
                if(ch == jewels.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }
}
```