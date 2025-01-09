package com.simon.tw;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Solution384 {
    int[] nums;
    int[] original;
    public Solution384(int[] nums) {
        this.nums= nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return nums;
    }

    public int[] shuffle() {
        int[] shuffled = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i< nums.length; i++) {
            list.add(nums[i]);
        }
        Random random = new Random();
        for(int i = 0; i< nums.length; i++) {
            int j = random.nextInt(list.size()); // 随机选择一个索引
            shuffled[i] = list.remove(j);
        }
        System.arraycopy(shuffled, 0, nums, 0, nums.length);
        return nums;
    }

}

class Solution384FisherYates {
    int[] nums;
    int[] original;
    public Solution384FisherYates(int[] nums) {
        this.nums= nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return nums;
    }

    public int[] shuffle() {
        Random random = new Random();
        for(int i = 0; i< nums.length; i++) {
            int j = i + random.nextInt(nums.length - i); //只会从当前索引 i 及其之后的元素中选择一个随机元素进行交换
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }// 原地交换
        return nums;
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
public class ShuffleAnArray {
    public static void main(String[] args) {
        Solution384 solution = new Solution384(new int[]{1, 2, 3});
        // 调用shuffle方法，打乱数组
        System.out.println("Shuffle: " + java.util.Arrays.toString(solution.shuffle()));

        // 调用reset方法，恢复数组到初始状态
        System.out.println("Reset: " + java.util.Arrays.toString(solution.reset()));

        // 再次调用shuffle方法，打乱数组
        System.out.println("Shuffle: " + java.util.Arrays.toString(solution.shuffle()));
        //输入
        //["Solution", "shuffle", "reset", "shuffle"]
        //[[[1, 2, 3]], [], [], []]
        //输出
        //[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

        Solution384FisherYates so = new Solution384FisherYates(new int[]{1, 2, 3});
        // 调用shuffle方法，打乱数组
        System.out.println("Shuffle: " + java.util.Arrays.toString(so.shuffle()));

        // 调用reset方法，恢复数组到初始状态
        System.out.println("Reset: " + java.util.Arrays.toString(so.reset()));

        // 再次调用shuffle方法，打乱数组
        System.out.println("Shuffle: " + java.util.Arrays.toString(so.shuffle()));
    }
}
