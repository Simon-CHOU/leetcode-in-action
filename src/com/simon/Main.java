package com.simon;

import com.simon.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] nums = InputUtil.inputIntArray();
            int i = solution.pivotIndex(nums);
            System.out.println(i);
//            int i = InputUtil.inputIntMatrix();
//            System.out.println(solution.checkStraightLine(ints, i));
        }
    }


}


class Solution {
    public int pivotIndex(int[] nums) {
        int pivot = -1;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum(nums, i) == rightSum(nums, i)) {
                pivot = i;
                break;
            }
        }
        return pivot;
    }

    int leftSum(int[] nums, int index) {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += nums[i];
        }
        return sum;
    }

    int rightSum(int[] nums, int index) {
        int sum = 0;
        for (int i = index + 1; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
}