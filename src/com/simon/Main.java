package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
//            int[] nums = InputUtil.inputIntArray();
            int[] nums = {0,1,0,3,12};
//            int[] nums = new int[]{0,1,0,3,12};
            solution.moveZeroes(nums);
            DisplayArrayUtil.disp(nums);
        }
    }
}

class Solution {
    public void moveZeroes(int[] nums) {
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r] != 0) {
                swap(nums, l, r);
                l++;
            }
            r++;
        }
    }

    private void swap(int[] nums, int l, int r) {
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }
}
