# week2 

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

## lesson 4: Prefix sum, difference, double pointer

