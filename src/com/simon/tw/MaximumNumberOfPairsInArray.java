package com.simon.tw;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.cn/problems/maximum-number-of-pairs-in-array/
class Solution2341 {
    public int[] numberOfPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }


        int numLeft = 0;
        int numOfPair = 0;

        for (int v : map.values()) {
            System.out.println(v);
            numOfPair += v / 2;
            numLeft += v % 2;
        }
        return new int[]{numOfPair, numLeft};
    }

    //https://leetcode.cn/problems/maximum-number-of-pairs-in-array/solutions/2112731/shu-zu-neng-xing-cheng-duo-shao-shu-dui-jq01j/
    public int[] numberOfPairs1(int[] nums) {
        Map<Integer, Boolean> cnt = new HashMap<Integer, Boolean>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, !cnt.getOrDefault(num, false));
            if (!cnt.get(num)) {
                res++;
            }
        }
        return new int[]{res, nums.length - 2 * res};
    }


    public int[] numberOfPairs2(int[] nums) { // 最快的解法
        int[] visited = new int[101];
        int count = 0;
        for (int num : nums) {
            visited[num]++;
            if (visited[num] % 2 == 0) { //检查数组 visited 中索引为 num 的元素值是否为偶数 （0 等效于上面的 false）
                count++;
            }
        }

        return new int[]{count, nums.length - (count * 2)};
    }
}

public class MaximumNumberOfPairsInArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 1, 3, 2, 2};

// 打印数组 nums
        System.out.println("nums 数组：");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        Solution2341 solution = new Solution2341();
        int[] result = solution.numberOfPairs2(nums);
        // 校验 result[0] == 3 和 result[1] == 1
        assert result[0] == 3 : "数目对数错误，期望3，实际" + result[0];
        assert result[1] == 1 : "剩余数错误，期望1，实际" + result[1];
        System.out.println("校验通过。");
        System.out.println("数对数：" + result[0]);
        System.out.println("剩余数：" + result[1]);


    }
}
