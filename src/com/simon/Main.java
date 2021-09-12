package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] arr = InputUtil.inputIntArray();// [-2,1,-3,4,-1,2,1,-5,4]
            int i = solution.maxSubArray(arr);
            System.out.println(i);

        }
    }
}

class Solution {
    public int maxSubArray(int[] nums) { // Dynamic Programming
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x); // f(i)=max{f(i−1)+nums[i],nums[i]}
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}