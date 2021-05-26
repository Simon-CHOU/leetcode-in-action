package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] nums = InputUtil.inputIntArray();
            System.out.println(solution.singleNumber(nums));
        }
    }
}

class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
    /**
     * FAIL 不支持负数 [1,3,1,-1,3]
     *     public int singleNumber(int[] nums) {
     *         Map<Integer, Integer> map = new HashMap<>();
     *         for (int i = 0; i < nums.length; i++) {
     *             int sum = sumSkip(nums, i);
     *             if (sum % 2 == 0) { //去除nums[i]后，nums和被2整除，说明nums[i]就是单独的数字
     *                 return nums[i];
     *             }
     *         }
     *         return -1;
     *     }
     *
     *     int sumSkip(int[] num, int indexToSkip) {
     *         int sum = 0;
     *         for (int i = 0; i < num.length; i++) {
     *             if (i == indexToSkip) continue;
     *             sum += num[i];
     *         }
     *         return sum;
     *     }
     */


    /**
     * HashMap
     *     public int singleNumber(int[] nums) {
     *         Map<Integer, Integer> map = new HashMap<>();
     *         for (int num : nums) {
     *             if (map.get(num) != null) {
     *                 int count = map.get(num);
     *                 count++;
     *                 map.put(num, count);
     *             } else {
     *                 map.put(num, 1);
     *             }
     *         }
     *         for (Map.Entry<Integer, Integer> next : map.entrySet()) {
     *             if (next.getValue() == 1) {
     *                 return next.getKey();
     *             }
     *         }
     *         return -1;
     *     }
     *     public int singleNumber(int[] nums) {
     *         Map<Integer, Integer> map = new HashMap<>();
     *         for (int i = 0; i < nums.length; i++) {
     *             if (map.get(nums[i]) != null) {
     *                 int count = map.get(nums[i]);
     *                 ;
     *                 count++;
     *                 map.put(nums[i], count);
     *             } else {
     *                 map.put(nums[i], 1);
     *             }
     *         }
     *         Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
     *         while (iterator.hasNext()) {
     *             Map.Entry<Integer, Integer> next = iterator.next();
     *             if (next.getValue() == 1) {
     *                 return next.getKey();
     *             }
     *         }
     *         return -1;
     *     }
     */

    /** HashSet
     *     public int singleNumber(int[] nums) {
     *         Set<Integer> set = new HashSet<>();
     *         for (int i = 0; i < nums.length; i++) {
     *             if (set.contains(nums[i])) {
     *                 set.remove(nums[i]);
     *             } else {
     *                 set.add(nums[i]);
     *             }
     *         }
     *         Iterator<Integer> iterator = set.iterator();
     *         return iterator.next();
     *     }
     */
}
