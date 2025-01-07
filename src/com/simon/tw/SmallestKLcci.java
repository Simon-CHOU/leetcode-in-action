package com.simon.tw;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution111 {
//    public int[] smallestK(int[] arr, int k) {
//        int[] vec = new int[k];
//        Arrays.sort(arr);
//        for(int i = 0; i < k; ++i) {
//            vec[i] = arr[i];
//        }
//        return vec;
//    }

    public int[] smallestK(int[] arr, int k) {
        int[] vec = new int[k];
        if(k == 0) {
            return vec;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (num1, num2) -> num2 - num1);
        // 堆顶最大，出队出来的是最大值
        // ！！！大跟堆，就是一个会自动排序的队列。
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
public class SmallestKLcci {
    public static void main(String[] args) {
        Solution111 s = new Solution111();
        int[] res = s.smallestK(new int[]{1,3,5,7,2,4,6,8}, 4);
        System.out.println(String.join(",", Arrays.stream(res).mapToObj(String::valueOf).toArray(String[]::new)));
    }
}
