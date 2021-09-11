package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] array1 = InputUtil.inputIntArray();
            int[] array2 = InputUtil.inputIntArray();
            int len1 = InputUtil.inputInt();
            int len2 = InputUtil.inputInt();
            solution.merge(array1, len1, array2, len2);
        }
    }
}

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int p = m + n; n > 0 && m > 0; p--) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[p - 1] = nums1[m - 1];
                m--;
            } else {
                nums1[p - 1] = nums2[n - 1];
                n--;
            }
        }
        for (; n > 0; n--) {
            nums1[n - 1] = nums2[n - 1];
        }

    }
}