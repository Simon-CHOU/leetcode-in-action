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
            int[] nums = InputUtil.inputIntArray();
            int[] nums1 = InputUtil.inputIntArray();
            DisplayArrayUtil.disp(solution.intersect(nums, nums1));
        }
    }
}

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] intersection = new int[Math.min(len1, len2)];
        int p1 = 0, p2 = 0, p = 0;
        while (p1 < len1 && p2 < len2) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                //nums1[p1]==nums2[p2]
                intersection[p++] = nums1[p1];
                p1++;
                p2++;
            }
        }


        return Arrays.copyOfRange(intersection, 0, p);
    }
}
