package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] nums = InputUtil.inputIntArray();
            solution.containsDuplicate(nums);
        }
    }
}

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i])
                return true;
        }
        return false;
//        Set<Integer> filter = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            if(!filter.add(nums[i]))
//                return true;
//        }
//        return false;
    }
}