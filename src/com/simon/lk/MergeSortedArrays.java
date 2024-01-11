package com.simon.lk;

// 88. 合并两个有序数组
// https://leetcode.cn/problems/merge-sorted-array/submissions/494669591/?envType=study-plan-v2&envId=top-interview-150
public class MergeSortedArrays {
        public static void  merge(int[] nums1, int m, int[] nums2, int n) {

            int[] res = new int[m + n];
            int i = 0, j = 0, k = 0;

            while( i < m && j < n) {
                if(nums1[i] <= nums2[j]) {
                    res[k++] = nums1[i++];
                } else {
                    res[k++] = nums2[j++];
                }
            }
            System.out.println(j);
            // 这里往下，一定有其中一个数组被遍历完了，故不续约考虑哪个更长
            // 或者两个数组必须断的在前
            while(i < m) {
                res[k++] = nums1[i++];
            }
            while(j < n)  {
                res[k++] = nums2[j++];
            }
            nums1 = res;
        }
    public static int[] merge(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] result = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < n1) {
            result[k++] = arr1[i++];
        }

        while (j < n2) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 4, 6, 8, 10};
        int[] arr2 = {1, 3, 5, 7};
        merge(arr1, 5, arr2, 3);
        for (int num : arr1) {
            System.out.print(num + " ");
        }
//        int[] merged = merge(arr1, arr2);
//        for (int num : merged) {
//            System.out.print(num + " ");
//        }
    }
}
