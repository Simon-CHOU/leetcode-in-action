package com.simon;

import com.simon.util.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * sum + nums[i] == total) {//sum=total−numsi−sum   left == right
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}