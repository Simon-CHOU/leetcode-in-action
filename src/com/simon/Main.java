package com.simon;

import com.simon.util.*;

import java.util.*;
import java.util.LinkedList;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        while (true) {
            int[] nums = InputUtil.inputIntArray();
            int k = InputUtil.inputInt();
            solution.rotate(nums, k);
        }
    }
}

class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        reverseArr(nums, 0, len-1);
        reverseArr(nums, 0, k - 1);
        reverseArr(nums, k, len - 1);
    }

    /**
     * reverse arr[start]~arr[end], includ  arr[start] & arr[end]
     *
     * @param arr   array
     * @param start begin
     * @param end   end
     */
    private void reverseArr(int[] arr, int start, int end) {
        while(start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
//        int len = end - start + 1;
//        int mid = start + (len / 2) -1;
//        for (int i = start; i < mid; i++) {
//            int temp = arr[i];
//            arr[i] = arr[len - 1 - i];
//            arr[len - 1 - i] = temp;
//        }
    }
    /** arrycopy 简化
     *     public void rotate(int[] nums, int k) {
     *         int len = nums.length;
     *         int[] ans = new int[len];
     *         for (int i = 0; i < len; i++) {
     *             ans[(i + k) % len] = nums[i];
     *         }
     *         System.arraycopy(ans,0,nums,0, len);
     * //        for (int i = 0; i < len; i++) {
     * //            nums[i] = ans[i];
     * //        }
     *
     *     }
     */
    /** 自解
     *     public void rotate(int[] nums, int k) {
     *         int len = nums.length;
     *         int[] ans = new int[len];
     *         for (int i = 0; i < len; i++) {
     *             ans[(i + k) % len] = nums[i];
     *         }
     *         for (int i = 0; i < len; i++) {
     *             nums[i] = ans[i];
     *         }
     *     }
     */
}