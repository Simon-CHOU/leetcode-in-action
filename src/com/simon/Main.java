package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] nums = InputUtil.inputIntArray();
            int[] nums1 = InputUtil.inputIntArray();
            DisplayArrayUtil.disp(solution.intersect(nums, nums1));
        }
    }
}

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        //nums1 short, nums2 long
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);// bravo~
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums1) {
            int count = map.getOrDefault(n, 0) + 1;
            map.put(n, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);//refresh count vlaue
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

}