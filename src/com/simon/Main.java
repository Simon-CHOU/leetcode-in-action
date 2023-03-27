package com.simon;

import com.simon.util.DisplayArrayUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    private static Solution solution = new Solution();

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1};
        int k = 2;
        int[] res = solution.getLeastNumbers(arr, k);
        DisplayArrayUtil.disp(res);
    }
}

class Solution {
    //  剑指 Offer 40. 最小的k个数
    //https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] vec = new int[k];
        if (k == 0) {
            return vec;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (num1, num2) -> num2 - num1); //大根堆
        // 堆顶最大，出队出来的是最大值

        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; ++i) {
            if (queue.peek() > arr[i]) { // 堆顶更大，说明堆顶元素不可能是最小的"k"个数之一
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        // 至此，堆维护完成
        for (int i = 0; i < k; ++i) {
            vec[i] = queue.poll();
        }
        return vec;
    }
}